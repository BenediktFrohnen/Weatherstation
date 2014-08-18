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
 * Created by blaukool on 29/07/2014.
 */
public class PostWriter implements Writer {

    private String url;

    public PostWriter(String url) {
        super();
        this.url = url;
    }

    @Override
    public void writeList(SensorReading values){

        CloseableHttpClient httpClient = HttpClients.createDefault();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            HttpPost request = new HttpPost(url);
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("time", sdf.format(values.getTime())));
            nvps.add(new BasicNameValuePair("temperatur", values.getTemperature().toString()));
            nvps.add(new BasicNameValuePair("humidity", values.getHumidity().toString()));

            request.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
            HttpResponse response = httpClient.execute(request);
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
