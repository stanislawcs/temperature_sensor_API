import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Client {
    public static void main(String[] args) {

        final String sensorName = "Sensor-123";

        registerSensor(sensorName);
        Random random = new Random();

        double maxTemperature = 45.0;
        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            addMeasurement(random.nextDouble() * maxTemperature,
                    random.nextBoolean(), sensorName);
        }
    }

    private static void registerSensor(String sensorName) {
        final String url = "http://localhost:8080/sensor/registration";

        Map<String, Object> jsonToSend = new HashMap<>();
        jsonToSend.put("name", sensorName);

        makePostRequestWithJSONData(url, jsonToSend);
    }

    private static void addMeasurement(double value, boolean raining, String sensorName) {

        final String url = "http://localhost:8080/measurements/add";

        Map<String, Object> jsonToSend = new HashMap<>();
        jsonToSend.put("value", value);
        jsonToSend.put("raining", raining);
        jsonToSend.put("sensor", Map.of("name", sensorName));

        makePostRequestWithJSONData(url, jsonToSend);
    }

    private static void makePostRequestWithJSONData(String url, Map<String, Object> jsonToSend) {
        final RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> request = new HttpEntity<>(jsonToSend, headers);

        try {
            restTemplate.postForObject(url, request, String.class);
            System.out.println("Changing successfully send to server");
        } catch (HttpClientErrorException e) {
            System.out.println("ERROR!");
            System.out.println(e.getMessage());
        }
    }



}
