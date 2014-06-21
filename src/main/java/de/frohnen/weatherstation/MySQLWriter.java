package de.frohnen.weatherstation;

import java.sql.*;

/**
 * Writer zum Speichern der Daten in die Datenbank.
 * <p/>
 * Created by Benedikt Frohnen on 20.06.2014.
 */
public class MySQLWriter implements Writer {

    public static final String URL = "jdbc:mysql://benediktfrohnen.bplaced.net:3306/benediktfrohnen";
    public static final String USER = "benediktfrohnen";
    public static final String PASSWORD = "";
    private static String SQL = "INSERT INTO weather (time,temperature,humidity) VALUES (?,?,?)";
    private Connection con;
    private PreparedStatement st;

    public MySQLWriter() {
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            st = con.prepareStatement(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    public void writeList(SensorReading values) {
        try {
            st.setTimestamp(1, new Timestamp(values.getTime().getTime()));
            st.setDouble(2, values.getTemperature());
            st.setDouble(3, values.getHumidity());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
