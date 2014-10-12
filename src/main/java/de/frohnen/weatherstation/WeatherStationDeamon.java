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

    public static void main(String[] args0) throws Exception{
        Runtime rt = Runtime.getRuntime();
        BufferedReader br;
        Writer csv = new CSVWriter("results.csv"); //Beschreibung der CSV Datei
        Writer post = new PostWriter("http://www.benediktfrohnen.bplaced.net/temperatur.php"); //PHP Skript zum empfangen der Daten
        do { //Schleifebeginn
            try {
                Process pr = rt.exec("sudo ./loldht 7"); // Process Befehl ausführen
                pr.waitFor(); // Warte auf eine Antwort
                br = new BufferedReader(new InputStreamReader(pr.getInputStream())); // Einlesen der Befehlsausgabe
                String line;
                boolean test = true; // Setzte ein Bit "test" auf 1
                while ((line = br.readLine()) != null) { //Lese solange bis es keine neue Zeile mehr gibt
                    if (StringUtils.startsWithIgnoreCase(line, "Humidity")) { //Steht in der Zeile Humidity
                        test = false; //Setze "test" auf 0 und lese anschließend die Werte ein
                        String hum = line.substring(StringUtils.indexOf(line, "=") + 2, StringUtils.indexOf(line, "=") + 7);
                        line = line.substring(StringUtils.indexOf(line, "=") + 2);
                        String tem = line.substring(StringUtils.indexOf(line, "=") + 2, StringUtils.indexOf(line, "=") + 7);
                        SensorReading sr = new SensorReading(Double.valueOf(tem), Double.valueOf(hum)); // Verarveiten der Daten in Sensorreading
                        csv.writeList(sr); //Uebergabe der Werte
                        post.writeList(sr);//Uebergabe der Werte
                        break;
                    }
                }
                if(test){ //Auswertung ob der Befehl läuft
                    throw new Exception("Befehl läuft nicht!");
                }
                br.close();
                pr.destroy();
            } catch (IOException ex) { // Fehlerauswertung I0
                ex.printStackTrace();
                System.exit(-1);
            } catch (InterruptedException ex) { //Fehlerauswertung Interrupted
                ex.printStackTrace();
                System.exit(-1);
            }
            try {
                Thread.sleep(1200000); // Wartezeit bis zum nächsten Auslesen
            } catch (InterruptedException e) { //Fehlerauswertung der Wartezeit
                e.printStackTrace();
                System.exit(-1);
            }
        } while (true);
    }
}
