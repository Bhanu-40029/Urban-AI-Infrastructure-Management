package Util;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import Service.TrafficData;

public class DatasetLoader {

    public static ArrayList<TrafficData> loadDataset() {

        ArrayList<TrafficData> trafficList =
                new ArrayList<>();

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(
                    "SELECT * FROM `dataset 200`");

            while(rs.next()) {

                TrafficData traffic =
                        new TrafficData(

                        rs.getString("Area Name"),

                        rs.getString("Road/Intersection Name"),

                        rs.getInt("Traffic Volume"),

                        rs.getDouble("Average Speed"),

                        rs.getDouble("Congestion Level"),

                        rs.getInt("Incident Reports"),

                        rs.getInt("Pedestrian and Cyclist Count"),

                        rs.getString("Weather Conditions")
                );

                trafficList.add(traffic);
            }

            con.close();

            System.out.println(
                    "Data Loaded From Database");

        }

        catch(Exception e) {

            System.out.println(e);
        }

        return trafficList;
    }
}