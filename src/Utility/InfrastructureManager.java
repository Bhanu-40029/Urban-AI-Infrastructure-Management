package Utility;

import java.util.Scanner;

import DSA.AVLTree;
import Exceptions.AssetNotFoundException;
import Exceptions.DuplicateAssetException;
import Exceptions.InvalidMenuChoiceException;
import Service.Asset;

public class InfrastructureManager {

    private AVLTree assetTree;

    public InfrastructureManager() {

        assetTree = new AVLTree();
    }

    public void displayInfrastructureMenu() {

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

    private void addAsset(Scanner sc) {

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

            assetTree.insert(asset);

            System.out.println(
                    "Asset Added Successfully");

        }
        catch(DuplicateAssetException e) {

            System.out.println(e.getMessage());
        }
    }

    private void searchAsset(Scanner sc) {

        System.out.print(
                "Enter Asset ID : ");

        int id = sc.nextInt();
        try {

            Asset asset =
                    assetTree.search(id);

            if(asset == null) {

                throw new AssetNotFoundException(
                        "Asset ID "
                        + id
                        + " not found.");
            }

            System.out.println(asset);

        }
        catch(AssetNotFoundException e) {

            System.out.println(e.getMessage());
        }
        
    }

    private void displayAssets() {

        System.out.println(
                "\n===== Asset List =====");

        assetTree.inorder();
    }
}