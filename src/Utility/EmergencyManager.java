package Utility;

import java.util.PriorityQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Util.DBConnection;
import Util.PDFGenerator;

import java.util.Scanner;

import Exceptions.EmptyEmergencyQueueException;
import Exceptions.InvalidPriorityException;
import Service.EmergencyRequest;

public class EmergencyManager {

    private PriorityQueue<EmergencyRequest> queue;

    public EmergencyManager() {

        queue = new PriorityQueue<>(
                (a,b) ->
                b.getPriority() - a.getPriority());

        loadEmergencyRequests();
    }
    private void loadEmergencyRequests() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM emergency_requests WHERE status='Pending'");

            while(rs.next()) {

                EmergencyRequest request =
                        new EmergencyRequest(

                                rs.getInt("request_id"),

                                rs.getString("location"),

                                rs.getString("emergency_type"),

                                rs.getInt("priority"));

                queue.add(request);
            }

            rs.close();
            stmt.close();
            con.close();

        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }

    public void displayEmergencyMenu() {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== EMERGENCY MANAGEMENT =====");

            System.out.println("1. Add Emergency Request");
            System.out.println("2. Process Emergency");
            System.out.println("3. Display Emergency Queue");
            System.out.println("4. Generate PDF");
            System.out.println("5. Back");
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

                    PDFGenerator.generateEmergencyReport();

                    break;

                case 5:

                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        } while(choice != 5);
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

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            "INSERT INTO emergency_requests VALUES(?,?,?,?,?,NOW())");
            ps.setInt(1,id);

            ps.setString(2,location);

            ps.setString(3,emergencyType);

            ps.setInt(4,priority);

            ps.setString(5,"Pending");

            ps.executeUpdate();

            queue.add(request);

            System.out.println(
                    "Emergency Request Added Successfully");

            ps.close();

            con.close();

        }

        catch(Exception e) {

            e.printStackTrace();
        }}
    private void processEmergency() {

        try {

            if(queue.isEmpty()) {

                throw new EmptyEmergencyQueueException(
                        "No Emergency Requests Available.");
            }

            EmergencyRequest request =
                    queue.poll();

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(

                            "UPDATE emergency_requests SET status=? WHERE request_id=?");

            ps.setString(1,"Completed");

            ps.setInt(2,
                    request.getRequestId());

            ps.executeUpdate();

            System.out.println(
                    "\nProcessing Emergency\n");

            System.out.println(request);

            ps.close();

            con.close();

        }

        catch(EmptyEmergencyQueueException e) {

            System.out.println(
                    e.getMessage());
        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }
   

    private void displayQueue() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(

                    "SELECT * FROM emergency_requests WHERE status='Pending'");

            System.out.println(
                    "\n===== EMERGENCY QUEUE =====");

            while(rs.next()) {

                System.out.println(

                        "Request ID : "
                        + rs.getInt("request_id")

                        + "\nLocation : "
                        + rs.getString("location")

                        + "\nType : "
                        + rs.getString("emergency_type")

                        + "\nPriority : "
                        + rs.getInt("priority")

                        + "\nStatus : "
                        + rs.getString("status")

                        + "\n");
            }

            rs.close();

            stmt.close();

            con.close();

        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }
}