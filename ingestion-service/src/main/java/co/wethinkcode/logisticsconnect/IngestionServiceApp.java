package co.wethinkcode.logisticsconnect;

import com.opencsv.exceptions.CsvValidationException;
import io.javalin.Javalin;

import java.io.IOException;
import java.util.List;

public class IngestionServiceApp {

    public static void main(String[] args) throws CsvValidationException, IOException {
        String filePath = "ingestion-service/src/main/resources/hubs-global.csv";
        HubCsvReader reader = new HubCsvReader();
        HubDataCleaner cleaner = new HubDataCleaner();

        HubDataService hubDataService = new HubDataService(
                reader, cleaner, filePath);

        List<Hub> hubs = hubDataService.getHubs();

        Javalin app = Javalin.create().start(7050);

        app.get("/health", ctx -> ctx.result("OK"));

        app.get("/hubs", ctx -> {
            ctx.json(hubDataService.getHubs());
        });

        app.get("/hubs/province/{province}", ctx -> {
            String province = ctx.pathParam("province");
            ctx.json(hubDataService.getHubsByProvince(province));
                });

        app.get("/hubs/activity/{isActive}", ctx -> {
            boolean isActive = Boolean.parseBoolean(ctx.pathParam("isActive"));
            ctx.json(hubDataService.getHubsByActivity(isActive));
        });

        app.get("/hubs/{id}", ctx -> {
            String id = ctx.pathParam("id");
            ctx.json(hubDataService.getHubById(id));
        });
    }
}

