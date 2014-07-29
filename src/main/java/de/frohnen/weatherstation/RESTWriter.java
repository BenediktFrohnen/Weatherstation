package de.frohnen.weatherstation;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by blaukool on 29/07/2014.
 */
public class RESTWriter implements Writer {
    @Override
    public void writeList(SensorReading values){

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            HttpPut request = new HttpPut("http://yoururl");
            StringEntity params =new StringEntity("\"time\":\""+values.getTime()+"\",\"humidity\":\""+values.getHumidity()+"\",\"temperature\":\""+values.getTemperature()+"\"");
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            // handle response here...
        }catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
