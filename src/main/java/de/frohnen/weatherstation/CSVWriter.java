package de.frohnen.weatherstation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writer zum schreiben der CSV Datei.
 * <p/>
 * Created by Benedikt Frohnen on 20.06.2014.
 */
public class CSVWriter implements Writer {

    private FileWriter writer;

    public CSVWriter(String filename) {
        try {
            writer = new FileWriter(new File(filename),true); // Erstellen der Datei wenn noch keine besteht
            writer.flush();
        } catch (IOException e) { // Fehlererkennung
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void writeList(SensorReading values) {
        StringBuilder sb = new StringBuilder(); // Erstellen eines Strings für die CSV Datei
        sb.append(values.getTime()); // Einlesen des Datum
        sb.append(","); // Einfügen des Komma
        sb.append(values.getTemperature()); // Einlesen der Temperatur
        sb.append(","); // Einfügen des Komma
        sb.append(values.getHumidity()); // Einfügen der Luftfeuchtigkeit
        sb.append("\n"); // In die nächste Zeile springen
        try {
            writer.write(sb.toString()); // String in die CSV schreiben
            writer.flush();
        } catch (IOException e) { // Fehlererkennung
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
