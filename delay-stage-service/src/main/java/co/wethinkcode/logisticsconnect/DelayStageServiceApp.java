package co.wethinkcode.logisticsconnect;

import io.javalin.Javalin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DelayStageServiceApp {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7052);

        app.get("/health", ctx -> ctx.result("OK"));

        app.get("delay-service/{hubID}", ctx -> {
//                    HttpClient client = HttpClient.newHttpClient(); // uncomment these after figuring out how to get stages
                    String id = ctx.pathParam("hubID");
//                    String url = "http://localhost:7051/hub-service/hubs/" + id;
//                    HttpRequest request = HttpRequest.newBuilder()
//                            .uri(URI.create(url)).GET().build();
//                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                    DelayStageService delayStages = new DelayStageService();
                    ctx.json(delayStages.getDelayStage(id));
                });
        // TODO (Tracks the Transit Delay Stage (0-8, e.g. weather shutdowns).)
        // Add domain endpoints for delay-stage-service here.
    }
}

// MQ TODO: publishes to ActiveMQ topic MqConfig.TOPIC at MqConfig.BROKER_URL (see co.wethinkcode.logisticsconnect.mq.MqConfig)
