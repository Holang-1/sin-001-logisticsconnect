package co.wethinkcode.logisticsconnect;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// give it id it returns a hub as a jsonNode

public class HubClient {
    private HttpClient client;
    private ObjectMapper objectMapper;
    private static final String HUB_SERVICE_ULR = "http://localhost:7051/hub-service/hubs/";

    public HubClient(){
        objectMapper = new ObjectMapper();

    }
    public Hub getHub(String hubId) throws IOException, InterruptedException {
        client = HttpClient.newHttpClient();

        String url = HUB_SERVICE_ULR + hubId;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200){
            throw new RuntimeException("Hub Service returned status: "+response.statusCode());
        }
        return objectMapper.readValue(response.body(), Hub.class);
    }

}
