package Utility;

import java.util.ArrayList;
import java.util.Scanner;

import DSA.FenwickTree;
import DSA.SegmentTree;
import Service.TrafficData;
import Util.DatasetLoader;

public class ResourceManager {

    private int[] trafficVolumes;

    private SegmentTree segmentTree;

    private FenwickTree fenwickTree;

    public ResourceManager() {

        ArrayList<TrafficData> list = DatasetLoader.loadDataset();

        trafficVolumes = new int[list.size()];

        for(int i=0;
            i<list.size();
            i++) {

            trafficVolumes[i] =
                    list.get(i)
                        .getTrafficVolume();
        }

        segmentTree =
                new SegmentTree(
                        trafficVolumes);

        fenwickTree =
                new FenwickTree(
                        trafficVolumes.length);

        for(int i=0;
            i<trafficVolumes.length;
            i++) {

            fenwickTree.update(
                    i + 1,
                    trafficVolumes[i]);
        }
    }

    public void displayResourceMenu() {

        Scanner sc =
                new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== RESOURCE MANAGEMENT =====");

            System.out.println(
                    "1. Range Traffic Query");

            System.out.println(
                    "2. Cumulative Traffic Query");

            System.out.println(
                    "3. Display Traffic Volume Array");

            System.out.println("4. Back");

            System.out.print(
                    "Enter Choice : ");

            choice =
                    sc.nextInt();

            switch(choice) {

                case 1:

                    System.out.print("Start Index : ");

                    int left =
                            sc.nextInt();

                    System.out.print("End Index : ");

                    int right =
                            sc.nextInt();

                    System.out.println("\nTraffic Volume = "
                            +
                            segmentTree.query(
                                    left,
                                    right));

                    break;

                case 2:

                    System.out.print(
                            "Enter Index : ");

                    int index =
                            sc.nextInt();

                    System.out.println(
                            "\nCumulative Traffic = "
                            +
                            fenwickTree.query(
                                    index));

                    break;

                case 3:

                    displayArray();

                    break;

                case 4:

                    break;

                default:

                    System.out.println(
                            "Invalid Choice");
            }

        } while(choice != 4);
    }

    private void displayArray() {

        System.out.println(
                "\nTraffic Volumes:");

        for(int value :
                trafficVolumes) {

            System.out.print(
                    value + " ");
        }

        System.out.println();
    }
}