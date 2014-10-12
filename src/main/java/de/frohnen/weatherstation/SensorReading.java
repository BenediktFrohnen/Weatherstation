package de.frohnen.weatherstation;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Klasse zum verwalten von Messdaten.
 * <p/>
 * Created by Benedikt Frohnen on 21.06.2014.
 */
public class SensorReading { // Interne Beschreiung

    private Date time;
    private Double temperature;
    private Double humidity;

    /**
     *
     * Öffentliche Beschreibung
     * Zuweisen der Werte und speichern in den variablen
     */
    public SensorReading(Double temperature, Double humidity) {
        this.time = new Date();
        this.temperature = temperature;
        this.humidity = humidity;
    }

    // Bei Anfrage von einer anderen class werden die in den Lokalen Variablen hinterlegten werde raus gegeben
    public Date getTime() {
        return time;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    @Override
    public boolean equals(Object o) { // Java Methode zum verarbeiten von Werten wird in meinem Fall nicht benötigt
        if (this == o) return true;
        if (!(o instanceof SensorReading)) return false;

        SensorReading that = (SensorReading) o;

        return humidity.equals(that.humidity) && temperature.equals(that.temperature) && time.equals(that.time);

    }

    @Override
    public int hashCode() { // Java Methode zum verarbeiten der werte zum Beispiel zum Schreiben einer Liste
        int result = time.hashCode();
        result = 31 * result + temperature.hashCode();
        result = 31 * result + humidity.hashCode();
        return result;
    }

    @Override
    public String toString() { // Erstellen des Stringformat wenn dieses von außen angefragt wird
        return new ToStringBuilder(this)
                .append("time", time)
                .append("temperature", temperature)
                .append("humidity", humidity)
                .toString();
    }
}
