package Main;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DSA.Graph;
import Exceptions.RouteNotFoundException;
import Service.TrafficData;
import Util.DatasetLoader;
import Utility.AnalyticsManager;
import Utility.CitizenServiceManager;
import Utility.EmergencyManager;
import Utility.InfrastructureManager;
import Utility.ProjectManager;
import Utility.ResourceManager;
import Utility.TrafficManager;

public class UrbanAI {
	public static void main(String [] args) throws RouteNotFoundException, SQLException {
		 Scanner sc = new Scanner(System.in);

	        InfrastructureManager infrastructureManager =new InfrastructureManager();
	        TrafficManager trafficManager = new TrafficManager();
	        EmergencyManager emergencyManager = new EmergencyManager();
	        AnalyticsManager analyticsManager = new AnalyticsManager();
	        ProjectManager projectManager = new ProjectManager();
	        ResourceManager resourceManager = new ResourceManager();
	        CitizenServiceManager citizenManager = new CitizenServiceManager();

	        int choice;

	        do {

	            System.out.println("\n======================================");
	            System.out.println("      URBAN AI CITY MANAGEMENT");
	            System.out.println("======================================");

	            System.out.println("1. Infrastructure Management");
	            System.out.println("2. Traffic Management");
	            System.out.println("3. Emergency Management");
	            System.out.println("4. Analytics Management");
	            System.out.println("5. Project Management");
	            System.out.println("6. Resource Management");
	            System.out.println("7. Citizen Service Management");
	            System.out.println("8. Exit");
	            System.out.print("\nEnter Choice : ");

	            choice = sc.nextInt();

	            switch(choice) {

	                case 1:
	                    infrastructureManager.displayInfrastructureMenu();
	                    break;

	                case 2:
	                    trafficManager.displayTrafficMenu();
	                    break;

	                case 3:
	                    emergencyManager.displayEmergencyMenu();
	                    break;

	                case 4:
	                    analyticsManager.displayAnalyticsMenu();
	                    break;

	                case 5:
	                    projectManager.displayProjectMenu();
	                    break;

	                case 6:
	                    resourceManager.displayResourceMenu();
	                    break;

	                case 7:
	                    citizenManager.displayCitizenMenu();
	                    break;

	                case 8:
	                    System.out.println("\nThank You For Using UrbanAI");
	                    break;

	                default:
	                    System.out.println("\nInvalid Choice");
	            }

	        } while(choice != 8);

   }
}
