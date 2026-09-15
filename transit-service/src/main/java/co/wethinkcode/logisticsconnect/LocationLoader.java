package co.wethinkcode.logisticsconnect;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LocationLoader {

    public Map<String, Coordinates> loadLocations(String fileName) {
        Map<String, Coordinates> locations = new HashMap<>();

        InputStream inputStream =
                getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException(
                    "Could not find CSV file: " + fileName
            );
        }

        try (CSVReader reader = new CSVReader(
                new InputStreamReader(
                        inputStream,
                        StandardCharsets.UTF_8
                ))) {

            reader.readNext(); // Skip header

            String[] row;

            while ((row = reader.readNext()) != null) {

                String province =
                        row[0].trim().toLowerCase();

                double latitude =
                        Double.parseDouble(row[1].trim());

                double longitude =
                        Double.parseDouble(row[2].trim());

                locations.put(
                        province,
                        new Coordinates(latitude, longitude)
                );
            }

        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(
                    "Could not read location CSV",
                    e
            );
        }

        return locations;
    }
}