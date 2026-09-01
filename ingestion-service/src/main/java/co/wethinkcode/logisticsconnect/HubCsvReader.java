package co.wethinkcode.logisticsconnect;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HubCsvReader {
        public List<Hub> read(String filePath) throws IOException, CsvValidationException {
            List<Hub> hubs = new ArrayList<>();

            try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
                String[] row;
                reader.readNext();

                while ((row = reader.readNext()) != null) {
                    Hub hub = new Hub(row[0].toUpperCase(), row[1].toLowerCase(), row[2].toLowerCase(), row[3].toLowerCase());
                    hubs.add(hub);
                }
            }
            return hubs;
        }
}
