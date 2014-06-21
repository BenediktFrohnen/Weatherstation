package de.frohnen.weatherstation;

/**
 * Interface für alle Klasse die Wetterdaten verarbeiten.
 * <p/>
 * Created by Benedikt Frohnen on 20.06.2014.
 */
public interface Writer {

    public void writeList(SensorReading values);

}
