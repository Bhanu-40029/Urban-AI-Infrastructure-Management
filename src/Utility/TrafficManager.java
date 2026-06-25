package Utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import DSA.Graph;
import Exceptions.InvalidMenuChoiceException;
import Exceptions.InvalidRoadException;
import Exceptions.RouteNotFoundException;
import Service.TrafficData;
import Util.DBConnection;
import Util.DatasetLoader;

public class TrafficManager {

    private Graph graph;
    private ArrayList<TrafficData> trafficList;

    public TrafficManager() {

        trafficList = DatasetLoader.loadDataset();

        graph = new Graph(10);

        loadRoadNetwork();
    }
    private void loadRoadNetwork() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM road_network");

            while(rs.next()) {

                int source =
                        rs.getInt("source");

                int destination =
                        rs.getInt("destination");

                int distance =
                        rs.getInt("distance");

                int time =
                        rs.getInt("travel_time");

                graph.addEdge(
                        source,
                        destination,
                        distance,
                        time);
            }

            rs.close();
            stmt.close();
            con.close();

        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }

    public void displayTrafficMenu() throws RouteNotFoundException {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== TRAFFIC MANAGEMENT =====");

            System.out.println("1. Display Dataset");
            System.out.println("2. Display Graph");
            System.out.println("3. BFS Traversal");
            System.out.println("4. DFS Traversal");
            System.out.println("5. Shortest Distance Route");
            System.out.println("6. Fastest Route");
            System.out.println("7. Back");

            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    displayDataset();
                    break;

                case 2:
                    graph.displayGraph();
                    break;

                case 3:
                    graph.bfs(0);
                    break;

                case 4:
                    graph.dfs(0);
                    break;

                case 5:

                	System.out.println("\nAvailable Cities");

                	for(int i = 0; i < graph.getVertices(); i++) {

                	    System.out.println(i + ". " + graph.getCityName(i));
                	}

                	System.out.print("Enter Source : ");
                	int source = sc.nextInt();

                	System.out.print("Enter Destination : ");
                	int destination = sc.nextInt();

                	try {

                	    if(source < 0 ||
                	       source >= graph.getVertices() ||
                	       destination < 0 ||
                	       destination >= graph.getVertices()) {

                	        throw new InvalidRoadException(
                	                "Invalid Source or Destination City.");
                	    }

                	    graph.dijkstraDistance(source,
                	                           destination);

                	}
                	catch(InvalidRoadException e) {

                	    System.out.println(e.getMessage());
                	}
                    break;

                case 6:
                    System.out.println("\n===== FASTEST ROUTE =====");

                    System.out.println("\nAvailable Cities:");

                    for(int i = 0; i < graph.getVertices(); i++) {

                        System.out.println(i + ". " + graph.getCityName(i));
                    }

                    System.out.print("\nEnter Source : ");
                    int sourceTime = sc.nextInt();

                    System.out.print("Enter Destination : ");
                    int destinationTime = sc.nextInt();

                    try {

                        if(sourceTime < 0 ||
                           sourceTime >= graph.getVertices() ||
                           destinationTime < 0 ||
                           destinationTime >= graph.getVertices()) {

                            throw new InvalidRoadException(
                                    "Invalid Source or Destination City.");
                        }

                        graph.dijkstraTime(sourceTime,
                                           destinationTime);

                    }
                    catch(InvalidRoadException e) {

                        System.out.println(e.getMessage());
                    }
                    break;


                case 7:
                    System.out.println("Returning...");
                    break;

                default:

                    try {

                        throw new InvalidMenuChoiceException(
                                "Invalid Menu Choice.");

                    }

                    catch(InvalidMenuChoiceException e) {

                        System.out.println(e.getMessage());
                    }}

        } while(choice != 7);
    }

    private void displayDataset() {

        System.out.println(
                "\n===== DATASET RECORDS =====");

        for(TrafficData data : trafficList) {

            System.out.println(data);
        }
    }
}