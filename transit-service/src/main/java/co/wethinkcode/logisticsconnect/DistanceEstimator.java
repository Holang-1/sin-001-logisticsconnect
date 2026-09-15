package co.wethinkcode.logisticsconnect;

import java.time.Duration;
import java.util.Map;

public class DistanceEstimator {

    private static final double EARTH_RADIUS_KM = 6371.0;

    public double estimateDistance(Hub origin, Hub destination,
            Map<String, Coordinates> locations) {

        Coordinates originCoordinates =
                locations.get(origin.getProvince().toLowerCase());

        Coordinates destinationCoordinates =
                locations.get(destination.getProvince().toLowerCase());

        if (originCoordinates == null) {
            throw new IllegalArgumentException(
                    "No location found for origin province: "
                            + origin.getProvince());
        }

        if (destinationCoordinates == null) {
            throw new IllegalArgumentException(
                "No location found for destination province: "
                    + destination.getProvince());
        }

        return calculateDistance(
            originCoordinates.getLatitude(),
            originCoordinates.getLongitude(),
            destinationCoordinates.getLatitude(),
            destinationCoordinates.getLongitude());
    }

    private double calculateDistance(double latitude1, double longitude1,
        double latitude2, double longitude2) {

        double latitudeDistance = Math.toRadians(latitude2 - latitude1);
        double longitudeDistance = Math.toRadians(longitude2 - longitude1);

        double a = Math.sin(latitudeDistance / 2)
                    * Math.sin(latitudeDistance / 2)
                    + Math.cos(Math.toRadians(latitude1))
                    * Math.cos(Math.toRadians(latitude2))
                    * Math.sin(longitudeDistance / 2)
                    * Math.sin(longitudeDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
    public Duration estimateTime(double distance){
        double hours = distance / 80.0;

        return Duration.ofMinutes((long) (hours * 60));
    }
}