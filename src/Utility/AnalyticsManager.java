package Utility;

import java.util.ArrayList;
import java.util.Scanner;

import DSA.HeapSort;
import DSA.MergeSort;
import DSA.QuickSort;
import Service.TrafficData;
import Util.DatasetLoader;

public class AnalyticsManager {

    private ArrayList<TrafficData> trafficList;

    public AnalyticsManager() {

        trafficList = DatasetLoader.loadDataset();
    }

    public void displayAnalyticsMenu() {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("     ANALYTICS MANAGEMENT");
            System.out.println("=================================");

            System.out.println("1. Sort By Traffic Volume (Quick Sort)");
            System.out.println("2. Sort By Average Speed (Merge Sort)");
            System.out.println("3. Sort By Incident Reports (Heap Sort)");
            System.out.println("4. Display Dataset");
            System.out.println("5. Back");

            System.out.print("\nEnter Choice : ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:

                    QuickSort.sort(trafficList,0,trafficList.size() - 1);

                    System.out.println("\nDataset Sorted By Traffic Volume");

                    displayData(1);

                    break;

                case 2:

                    MergeSort.sort(trafficList,0, trafficList.size() - 1);

                    System.out.println("\nDataset Sorted By Average Speed");

                    displayData(2);

                    break;

                case 3:

                    HeapSort.sort(trafficList);

                    System.out.println("\nDataset Sorted By Incident Reports");

                    displayData(3);

                    break;

                case 4:

                    displayData(4);

                    break;

                case 5:

                    System.out.println("\nReturning to Main Menu...");

                    break;

                default:

                    System.out.println("\nInvalid Choice");
            }

        } while(choice != 5);
    }

    private void displayData(int choice) {

        ArrayList<String> displayedCities =
                new ArrayList<>();

        System.out.println();

        if(choice == 1) {

            System.out.println(
                    "===== Cities Sorted By Traffic Volume =====");
        }

        else if(choice == 2) {

            System.out.println(
                    "===== Cities Sorted By Average Speed =====");
        }

        else {

            System.out.println(
                    "===== Cities Sorted By Incident Reports =====");
        }

        for(TrafficData data : trafficList) {

            if(!displayedCities.contains(
                    data.getAreaName())) {

                displayedCities.add(
                        data.getAreaName());

                if(choice == 1) {

                    System.out.println(
                            data.getAreaName()
                            + " --> Traffic Volume : "
                            + data.getTrafficVolume());
                }

                else if(choice == 2) {

                    System.out.println(
                            data.getAreaName()
                            + " --> Average Speed : "
                            + data.getAverageSpeed()
                            + " km/hr");
                }

                else {

                    System.out.println(
                            data.getAreaName()
                            + " --> Incident Reports : "
                            + data.getIncidentReports());
                }
            }
        }
    }
}