package de.frohnen.weatherstation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Writer zum schreiben der CSV Datei.
 * <p/>
 * Created by Benedikt Frohnen on 20.06.2014.
 */
public class SQLWriter implements Writer {

    private FileWriter writer;

    public SQLWriter(String filename) {
        try {
            writer = new FileWriter(new File(filename),true);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void writeList(SensorReading values) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into temperaturmessung values ('");
        sb.append(values.getTime());
        sb.append("','");
        sb.append(values.getTemperature());
        sb.append(" C°','");
        sb.append(values.getHumidity());
        sb.append(" %');\n");
        try {
            writer.append(sb.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
