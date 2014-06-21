package de.frohnen.weatherstation;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Main Klasse zum auslesen und verarbeiten von Wetterdaten.
 * <p/>
 * Created by Benedikt Frohnen on 20.06.2014.
 */
public class WeatherStationDeamon {

    public static void main(String[] args0) {
        Runtime rt = Runtime.getRuntime();
        BufferedReader br;
        Writer csv = new CSVWriter("results.csv");
        Writer db = new MySQLWriter();
        do {
            try {
                Process pr = rt.exec("./loldht.bat 7");
                pr.waitFor();
                br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    if (StringUtils.startsWithIgnoreCase(line, "Humidity")) {
                        String hum = line.substring(StringUtils.indexOf(line, "=") + 2, StringUtils.indexOf(line, "=") + 7);
                        line = line.substring(StringUtils.indexOf(line, "=") + 2);
                        String tem = line.substring(StringUtils.indexOf(line, "=") + 2, StringUtils.indexOf(line, "=") + 7);
                        SensorReading sr = new SensorReading(Double.valueOf(tem), Double.valueOf(hum));
                        csv.writeList(sr);
                        db.writeList(sr);
                        break;
                    }
                }
                br.close();
                pr.destroy();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.exit(-1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                System.exit(-1);
            }
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(-1);
            }
        } while (true);
    }

}
