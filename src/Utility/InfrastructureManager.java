package Utility;

import java.util.Scanner;

import DSA.AVLTree;
import Exceptions.AssetNotFoundException;
import Exceptions.DuplicateAssetException;
import Exceptions.InvalidMenuChoiceException;
import Service.Asset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Util.DBConnection;
public class InfrastructureManager {

    private AVLTree assetTree;

    public InfrastructureManager() {

        assetTree = new AVLTree();

        loadAssetsFromDatabase();
    }

    private void loadAssetsFromDatabase() {

        try {

            Connection con = DBConnection.getConnection();

            Statement stmt = con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM assets");

            while(rs.next()) {

                Asset asset =
                        new Asset(

                                rs.getInt("asset_id"),

                                rs.getString("asset_name"),

                                rs.getString("asset_type"),

                                rs.getString("location"),

                                rs.getString("status"));

                assetTree.insert(asset);
            }

            rs.close();
            stmt.close();
            con.close();

        }

        catch(Exception e) {

            e.printStackTrace();
        }
    }

	public void displayInfrastructureMenu() throws SQLException {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println("\n===== Infrastructure Management =====");

            System.out.println("1. Add Asset");
            System.out.println("2. Search Asset");
            System.out.println("3. Display Assets");
            System.out.println("4. Back");

            System.out.print("Enter Choice : ");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    addAsset(sc);
                    break;

                case 2:
                    searchAsset(sc);
                    break;

                case 3:
                    displayAssets();
                    break;

                case 4:
                    System.out.println("Returning...");
                    break;

                default:

                    try {

                        throw new InvalidMenuChoiceException(
                                "Invalid Menu Choice.");

                    }

                    catch(InvalidMenuChoiceException e) {

                        System.out.println(
                                e.getMessage());
                    }
                    
            }

        } while(choice != 4);
    }

    private void addAsset(Scanner sc) throws SQLException {

        System.out.print("Asset ID : ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Asset Name : ");
        String name = sc.nextLine();

        System.out.print("Asset Type : ");
        String type = sc.nextLine();

        System.out.print("Location : ");
        String location = sc.nextLine();

        System.out.print("Status : ");
        String status = sc.nextLine();

        Asset asset =
                new Asset(
                        id,
                        name,
                        type,
                        location,
                        status);

        try {

            if(assetTree.search(id) != null) {

                throw new DuplicateAssetException(
                        "Asset ID " + id + " already exists.");
            }

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(

                    "INSERT INTO assets VALUES(?,?,?,?,?)");

            ps.setInt(1,id);

            ps.setString(2,name);

            ps.setString(3,type);

            ps.setString(4,location);

            ps.setString(5,status);

            ps.executeUpdate();

            assetTree.insert(asset);

            System.out.println(
                    "Asset Added Successfully");

            ps.close();
            con.close();  }
        catch(DuplicateAssetException e) {

            System.out.println(e.getMessage());
        }
    }

    private void searchAsset(Scanner sc) {

        System.out.print("Enter Asset ID : ");

        int id = sc.nextInt();

        try {

            Connection con =
                    DBConnection.getConnection();

            PreparedStatement ps =
                    con.prepareStatement(
                            "SELECT * FROM assets WHERE asset_id=?");

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                System.out.println("\n===== ASSET FOUND =====");

                System.out.println(
                        "ID : " + rs.getInt("asset_id"));

                System.out.println(
                        "Name : " + rs.getString("asset_name"));

                System.out.println(
                        "Type : " + rs.getString("asset_type"));

                System.out.println(
                        "Location : " + rs.getString("location"));

                System.out.println(
                        "Status : " + rs.getString("status"));
            }
            else {

                throw new AssetNotFoundException(
                        "Asset ID " + id + " not found.");
            }

            rs.close();
            ps.close();
            con.close();

        }
        catch(AssetNotFoundException e) {

            System.out.println(e.getMessage());
        }
        catch(Exception e) {

            e.printStackTrace();
        }
    }

    private void displayAssets() {

        try {

            Connection con =
                    DBConnection.getConnection();

            Statement stmt =
                    con.createStatement();

            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM assets");

            System.out.println(
                    "\n===== ASSET LIST =====");

            while(rs.next()) {

                System.out.println(

                        "ID : "
                        + rs.getInt("asset_id")

                        + " | Name : "
                        + rs.getString("asset_name")

                        + " | Type : "
                        + rs.getString("asset_type")

                        + " | Location : "
                        + rs.getString("location")

                        + " | Status : "
                        + rs.getString("status"));
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