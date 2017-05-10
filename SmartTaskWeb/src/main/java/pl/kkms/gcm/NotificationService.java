package pl.kkms.gcm;


import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NotificationService {
    private static final String AUTH_KEY_FCM = "AIzaSyAUVe4-HxpOQNBBrYt24Oe-vZxop1tHHWg";
    private static final String API_URL_FCM = "https://gcm.googleapis.com/gcm/send";

    private static final String DEVICE_TOKEN = "c4HUvwW-04A:APA91bEizclhmQyjzO7OSDmMwh-0weLNor-VlOf9P3v1qEtMOAJ8jEEntQe8-mxNXUd8oKLxnDxafxfha3zQlthqAyXY1ARhYw7JzcLXr09fhyPLkO53ZKgF9j29bDh9uYYJoAf6sE-H";

    public static void sendPushNotification(String title, String description) {

        try {
            URL url = new URL(API_URL_FCM);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "key=" + AUTH_KEY_FCM);
            connection.setRequestProperty("Content-Type", "application/json");

            JSONObject json = new JSONObject();

            // TODO przerobic na przesylanie jsonem zadania w body

            JSONObject info = new JSONObject();
            json.put("to", DEVICE_TOKEN);
            info.put("title", title); // Notification title
            info.put("body", description); // Notification
            // body
            json.put("notification", info);

            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(json.toString());
            writer.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = reader.readLine()) != null) {
                System.out.println(output);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("GCM Notification is sent successfully");
    }
}
