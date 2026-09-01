package co.wethinkcode.logisticsconnect;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class HubCsvReader {
        public List<Hub> read(String filePath) throws Exception {
            List<Hub> hubs = new ArrayList<>();

            try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
                String[] row;
                reader.readNext();

                while ((row = reader.readNext()) != null) {
                    Hub hub = new Hub(row[0], row[1], row[2], row[3]);
                    hubs.add(hub);
                }
            }
            return hubs;
        }
}
