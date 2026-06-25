package Utility;

import java.util.PriorityQueue;
import java.util.Scanner;

import Exceptions.EmptyEmergencyQueueException;
import Exceptions.InvalidPriorityException;
import Service.EmergencyRequest;

public class EmergencyManager {

    private PriorityQueue<EmergencyRequest> queue;

    public EmergencyManager() {

        queue = new PriorityQueue<>(
                (a, b) ->
                b.getPriority()
                - a.getPriority());
    }

    public void displayEmergencyMenu() {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== EMERGENCY MANAGEMENT =====");

            System.out.println("1. Add Emergency Request");
            System.out.println("2. Process Emergency");
            System.out.println("3. Display Emergency Queue");
            System.out.println("4. Back");
            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    addEmergency(sc);
                    break;

                case 2:
                    processEmergency();
                    break;

                case 3:
                    displayQueue();
                    break;

                case 4:
                    System.out.println("Returning...");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while(choice != 4);
    }

    private void addEmergency(Scanner sc) {

        System.out.print("Request ID : ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Location : ");
        String location = sc.nextLine();

        System.out.println("\nSelect Emergency Type");

        System.out.println("1. Fire");
        System.out.println("2. Medical");
        System.out.println("3. Accident");
        System.out.println("4. Power Failure");
        System.out.println("5. Water Leakage");

        System.out.print("Enter Choice : ");

        int typeChoice = sc.nextInt();

        String emergencyType;
        int priority;

        switch(typeChoice) {

            case 1:
                emergencyType = "Fire";
                priority = 5;
                break;

            case 2:
                emergencyType = "Medical";
                priority = 4;
                break;

            case 3:
                emergencyType = "Accident";
                priority = 3;
                break;

            case 4:
                emergencyType = "Power Failure";
                priority = 2;
                break;

            case 5:
                emergencyType = "Water Leakage";
                priority = 1;
                break;

            default:

                try {

                    throw new InvalidPriorityException(
                            "Invalid Emergency Type Selected.");

                }

                catch(InvalidPriorityException e) {

                    System.out.println(e.getMessage());
                }

                return;
        }

        EmergencyRequest request =
                new EmergencyRequest(
                        id,
                        location,
                        emergencyType,
                        priority);

        queue.add(request);

        System.out.println(
                "Emergency Request Added Successfully");
    }

    private void processEmergency() {

    	try {

    	    if(queue.isEmpty()) {

    	        throw new EmptyEmergencyQueueException(
    	                "No Emergency Requests Available.");
    	    }

    	    EmergencyRequest request =
    	            queue.poll();

    	    System.out.println(
    	            "\nProcessing Emergency:");

    	    System.out.println(request);

    	}

    	catch(EmptyEmergencyQueueException e) {

    	    System.out.println(e.getMessage());
    	}

        EmergencyRequest request =
                queue.poll();

        System.out.println(
                "\nProcessing Emergency:");

        System.out.println(request);
    }

    private void displayQueue() {

        if(queue.isEmpty()) {

            System.out.println(
                    "Emergency Queue Empty");

            return;
        }

        System.out.println("\n===== Emergency Queue =====");

        for(EmergencyRequest request
                : queue) {

            System.out.println(request);
        }
    }
}