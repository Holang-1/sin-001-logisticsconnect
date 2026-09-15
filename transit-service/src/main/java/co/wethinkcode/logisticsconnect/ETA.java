package co.wethinkcode.logisticsconnect;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class ETA {

    private final LocationLoader locationLoader;
    private final DistanceEstimator distanceEstimator;

    public ETA() {
        this.locationLoader = new LocationLoader();
        this.distanceEstimator = new DistanceEstimator();
    }

    public LocalDateTime calculateETA(Hub origin, Hub destination) {

        Map<String, Coordinates> locations = locationLoader.loadLocations(
                        "coordinates.csv");

        double distance = distanceEstimator.estimateDistance(
                        origin, destination, locations);

        Duration travelTime = distanceEstimator.estimateTime(distance);

        return LocalDateTime.now().plus(travelTime);
    }
}