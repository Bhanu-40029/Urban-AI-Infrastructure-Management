package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import Service.TrafficData;

public class DatasetLoader {

    public static ArrayList<TrafficData> loadDataset() {

        ArrayList<TrafficData> trafficList =
                new ArrayList<>();

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(
                                    "src/data/Dataset 200.csv"));

            String line;

            br.readLine(); // Skip Header

            while((line = br.readLine()) != null) {

                if(line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",");

                if(data.length < 15) {
                    continue;
                }

                try {

                    String areaName = data[1];

                    String roadName = data[2];

                    int trafficVolume =
                            (int) Double.parseDouble(
                                    data[3]);

                    double averageSpeed =
                            Double.parseDouble(
                                    data[4]);

                    double congestionLevel =
                            Double.parseDouble(
                                    data[6]);

                    int incidentReports =
                            (int) Double.parseDouble(
                                    data[8]);

                    int pedestrianCount =
                            (int) Double.parseDouble(
                                    data[13]);

                    String weather =
                            data[14];

                    TrafficData traffic =
                            new TrafficData(
                                    areaName,
                                    roadName,
                                    trafficVolume,
                                    averageSpeed,
                                    congestionLevel,
                                    incidentReports,
                                    pedestrianCount,
                                    weather);

                    trafficList.add(traffic);

                }

                catch(Exception ex) {

                    System.out.println(
                            "Skipping Corrupted Row");
                }
            }

            br.close();

            System.out.println("Dataset Loaded Successfully");

        }

        catch(Exception e) {

            System.out.println(
                    "Error : " + e.getMessage());
        }

        return trafficList;
    }
}