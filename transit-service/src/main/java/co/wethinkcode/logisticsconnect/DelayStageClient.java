package co.wethinkcode.logisticsconnect;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DelayStageClient {

    private final HttpClient client;
    private final ObjectMapper mapper;

    public DelayStageClient() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public int getDelayStage(String hubId) throws Exception {

        String url = "http://localhost:7052/delay-service/" + hubId;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).GET().build();

        HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Could not get delay stage for hub: " + hubId);
        }

        JsonNode json = mapper.readTree(response.body());

        return json.get("stage").asInt();
    }
}