package co.wethinkcode.logisticsconnect;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;

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

    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7053);

        app.get("/health", ctx -> ctx.result("OK"));

        app.get("/transit-service/{hubIdFrom}/{hubIdTo}", ctx -> {

            String idFrom = ctx.pathParam("hubIdFrom");
            String idTo = ctx.pathParam("hubIdTo");

            Hub origin = getHub(idFrom);
            Hub destination = getHub(idTo);

            ETA etaCalculator = new ETA();

            LocalDateTime estimatedArrival = etaCalculator.calculateETA(origin, destination);

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

    private static Hub getHub(String hubId) throws Exception {

        String url = HUB_SERVICE_URL + "/hub-service/hubs/" + hubId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).GET().build();

        HttpResponse<String> response = client.send(
                        request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Hub not found: " + hubId);
        }

        return mapper.readValue(response.body(), Hub.class);
    }
}
