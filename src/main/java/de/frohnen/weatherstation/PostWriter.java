package de.frohnen.weatherstation;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benedikt Frohnen on 29/07/2014.
 */
public class PostWriter implements Writer {

    private String url;

    public PostWriter(String url) {
        super();
        this.url = url;
    }

    @Override
    public void writeList(SensorReading values){
        CloseableHttpClient httpClient = HttpClients.createDefault(); //Beschreiung des HTTP Client
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //Datumsformat bestimmen
        try {
            HttpPost request = new HttpPost(url); // Einlesen der URL
            request.addHeader("content-type", "application/x-www-form-urlencoded"); //Vorbereitung der URL
            List<NameValuePair> nvps = new ArrayList<NameValuePair>(); // Vorbereitung der Werte
            nvps.add(new BasicNameValuePair("time", sdf.format(values.getTime()))); // Vorbereitung des Datums
            nvps.add(new BasicNameValuePair("temperatur", values.getTemperature().toString())); //Vorbereitung der Temp.
            nvps.add(new BasicNameValuePair("humidity", values.getHumidity().toString())); // Vorbereitung der Luftfeuchtigkeit

            request.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8)); // Werte zusammensetzten zum verschicken
            HttpResponse response = httpClient.execute(request); // Aufbau der Verbindung und Übertragung
        }catch (Exception ex) { // Fehlerauswertung
            ex.printStackTrace();
        } finally {
            try {
                httpClient.close(); // Schliesen der Verbindung
            } catch (IOException e) { //Fehlerauswertung
                e.printStackTrace();
            }
        }
    }
}
