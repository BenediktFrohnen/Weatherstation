package de.frohnen.weatherstation;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Klasse zum verwalten von Messdaten.
 * <p/>
 * Created by Benedikt Frohnen on 21.06.2014.
 */
public class SensorReading {

    private Date time;
    private Double temperature;
    private Double humidity;

    public SensorReading(Double temperature, Double humidity) {
        this.time = new Date();
        this.temperature = temperature;
        this.humidity = humidity;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SensorReading)) return false;

        SensorReading that = (SensorReading) o;

        return humidity.equals(that.humidity) && temperature.equals(that.temperature) && time.equals(that.time);

    }

    @Override
    public int hashCode() {
        int result = time.hashCode();
        result = 31 * result + temperature.hashCode();
        result = 31 * result + humidity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("time", time)
                .append("temperature", temperature)
                .append("humidity", humidity)
                .toString();
    }
}
