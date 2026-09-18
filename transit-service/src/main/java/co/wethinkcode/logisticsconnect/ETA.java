package co.wethinkcode.logisticsconnect;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class ETA {

    private final LocationLoader locationLoader;
    private final DistanceEstimator distanceEstimator;
    private final DelayStageClient delayStageClient;

    public ETA() {
        this.locationLoader = new LocationLoader();
        this.distanceEstimator = new DistanceEstimator();
        this.delayStageClient = new DelayStageClient();
    }

    public LocalDateTime calculateETA(Hub origin, Hub destination) throws Exception {

        Map<String, Coordinates> locations = locationLoader.loadLocations("coordinates.csv");

        double distance = distanceEstimator.estimateDistance(origin, destination, locations);

        Duration travelTime = distanceEstimator.estimateTime(distance);

        int originDelayStage = delayStageClient.getDelayStage(origin.getId());

        int destinationDelayStage = delayStageClient.getDelayStage(destination.getId());

        Duration delay = calculateDelay(originDelayStage, destinationDelayStage);

        return LocalDateTime.now().plus(travelTime).plus(delay);
    }

    private Duration calculateDelay(int originStage, int destinationStage) {

        int totalDelayMinutes = (originStage + destinationStage) * 15;

        return Duration.ofMinutes(totalDelayMinutes);
    }
}