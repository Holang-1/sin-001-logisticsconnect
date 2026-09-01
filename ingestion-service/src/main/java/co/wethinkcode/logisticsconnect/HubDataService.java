package co.wethinkcode.logisticsconnect;

import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HubDataService {
    private final HubCsvReader reader;
    private final HubDataCleaner cleaner;
    private final String filePath;

    public HubDataService(HubCsvReader reader,
          HubDataCleaner cleaner, String filePath) {
        this.reader = reader;
        this.cleaner = cleaner;
        this.filePath = filePath;
    }

    public List<Hub> getHubs() throws IOException, CsvValidationException {
        List<Hub> hubs = reader.read(filePath);
        cleaner.clean(hubs);
        return hubs;
    }
    public Hub getHubById(String id) throws IOException, CsvValidationException {
        List<Hub> hubs = getHubs();

        for (Hub hub : hubs) {
            if (hub.getId() == null){
                continue;
            }
            if (hub.getId().equals(id.toUpperCase())) {
                return hub;
            }
        }
        return null;
    }

    public List<Hub> getHubsByProvince(String province) throws IOException, CsvValidationException {
        List<Hub> hubs = getHubs();
        List<Hub> matchingHubs = new ArrayList<>();

        for (Hub hub : hubs) {
            if (hub.getProvince() == null){
                continue;
            }
            if (province.toLowerCase().equals(hub.getProvince())) {
                matchingHubs.add(hub);
            }
        }
        return matchingHubs;
    }

    public List<Hub> getHubsByActivity(boolean active) throws IOException, CsvValidationException {
        List<Hub> hubs = getHubs();
        List<Hub> matchingHubs = new ArrayList<>();

        for (Hub hub : hubs) {
            if (hub.isActive() == null){
                continue;
            }
            if (active == (boolean) hub.isActive()) {
                matchingHubs.add(hub);
            }
        }
        return matchingHubs;
    }
}