package co.wethinkcode.logisticsconnect;

import co.wethinkcode.logisticsconnect.mq.MqSubscriber;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import jakarta.jms.JMSException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Map;

public class TransitServiceApp {

    private static final String HUB_SERVICE_URL = "http://localhost:7051";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws JMSException, javax.jms.JMSException {

        Javalin app = Javalin.create();
        app.start(7053);

        HubClient hubClient = new HubClient();

        app.get("/health", ctx -> ctx.result("OK"));

        app.get("/transit-service/{hubIdFrom}/{hubIdTo}", ctx -> {

            String idFrom = ctx.pathParam("hubIdFrom");
            String idTo = ctx.pathParam("hubIdTo");

            Hub origin = hubClient.getHub(idFrom);
            Hub destination = hubClient.getHub(idTo);

            ETA etaCalculator = new ETA();

            MqSubscriber originSubscriber = new MqSubscriber(origin.getId());
            MqSubscriber destinationSubscriber = new MqSubscriber(destination.getId());

            originSubscriber.start();
            destinationSubscriber.start();

            int originStage = originSubscriber.getStage();
            int destinationStage = destinationSubscriber.getStage();

            LocalDateTime estimatedArrival = etaCalculator.calculateETA(
                    origin, destination, originStage, destinationStage);

            double distance = new DistanceEstimator().estimateDistance(origin, destination,
                            new LocationLoader().loadLocations("coordinates.csv"));

            Map<String, Object> response = Map.of(
                    "from", origin.getId() +" -> "+ origin.getProvince(),
                    "to", destination.getId() +" -> "+ destination.getProvince(),
                    "estimatedDistanceKm", distance,
                    "estimatedArrival", estimatedArrival.toString()
            );

            ctx.json(response);
        });
    }

}
