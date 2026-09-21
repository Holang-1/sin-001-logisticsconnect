package co.wethinkcode.logisticsconnect;

//import co.wethinkcode.logisticsconnect.mq.DelayStagePublisher;
import io.javalin.Javalin;

import java.util.concurrent.ThreadLocalRandom;

public class DelayStageServiceApp {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7052);
        HubClient hubClient = new HubClient();

        app.get("/health", ctx -> ctx.result("OK"));

        app.get("delay-service/{hubID}", ctx -> {
            String id = ctx.pathParam("hubID");
            Hub hub = hubClient.getHub(id);
            if (hub == null){
                throw new RuntimeException(
                    "Failed to get Hub: " + id);
            }
            int randomStage = ThreadLocalRandom.current().nextInt(0, 9);
            DelayStage delayStage = new DelayStage(hub.getId(), randomStage);

//            DelayStagePublisher delayStagePublisher = new DelayStagePublisher();
//            delayStagePublisher.publish(delayStage.getHubID(), delayStage.getStage());

            ctx.json(delayStage);
            });

        // TODO (Tracks the Transit Delay Stage (0-8, e.g. weather shutdowns).)
        // Add domain endpoints for delay-stage-service here.
    }
}

// MQ TODO: publishes to ActiveMQ topic MqConfig.TOPIC at MqConfig.BROKER_URL (see co.wethinkcode.logisticsconnect.mq.MqConfig)
