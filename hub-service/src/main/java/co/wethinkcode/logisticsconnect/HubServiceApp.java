package co.wethinkcode.logisticsconnect;

import io.javalin.Javalin;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HubServiceApp {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7051);

        app.get("/health", ctx -> ctx.result("OK"));

        app.get("/hub-service/hubs", ctx -> {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:7050/hubs")).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ctx.result(response.body());
        });
        app.get("hub-service/hubs/{id}", ctx -> {
            HttpClient client = HttpClient.newHttpClient();
            String id = ctx.pathParam("id");
            String url = "http://localhost:7050/hubs/"+id;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ctx.result(response.body());
        });
        app.get("hub-service/hubs/{province}", ctx -> {
            HttpClient client = HttpClient.newHttpClient();
            String province = ctx.pathParam("province");
            String url = "http://localhost:7050/hubs/"+province;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            ctx.result(response.body());
        });
    }
}
