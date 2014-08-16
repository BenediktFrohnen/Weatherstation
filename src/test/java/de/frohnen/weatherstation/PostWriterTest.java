package de.frohnen.weatherstation;

import org.junit.Test;

/**
 * Created by Benedikt on 16.08.2014.
 */
public class PostWriterTest {

    @Test
    public void testPost(){
        Writer wr = new PostWriter("http://www.addresse.net/temperatur.php");
        SensorReading sr = new SensorReading(2.5d,14.1d);
        wr.writeList(sr);
    }

}
