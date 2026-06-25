package Utility;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import Exceptions.BudgetExceededException;
import Service.Project;
import Service.Task;

public class ProjectManager {

    private ArrayList<Task> tasks;
    private ArrayList<Project> projects;

    public ProjectManager() {

        tasks = new ArrayList<>();
        projects = new ArrayList<>();

        loadSampleData();
    }

    private void loadSampleData() {

        tasks.add(new Task(1,"Road Repair",1,3));
        tasks.add(new Task(2,"Signal Maintenance",2,5));
        tasks.add(new Task(3,"Bridge Inspection",4,6));
        tasks.add(new Task(4,"Street Light Repair",6,8));

        projects.add(new Project(
                101,
                "Smart Signals",
                40,
                120));

        projects.add(new Project(
                102,
                "Metro Extension",
                60,
                150));

        projects.add(new Project(
                103,
                "Road Expansion",
                30,
                90));
    }

    public void displayProjectMenu() {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println(
                    "\n===== PROJECT MANAGEMENT =====");

            System.out.println(
                    "1. Activity Selection");

            System.out.println(
                    "2. Fractional Knapsack");

            System.out.println(
                    "3. 0/1 Knapsack");

            System.out.println(
                    "4. Back");

            choice = sc.nextInt();

            switch(choice) {

                case 1:
                    activitySelection();
                    break;

                case 2:
                    fractionalKnapsack();
                    break;

                case 3:
                    zeroOneKnapsack();
                    break;

                case 4:
                    break;

                default:
                    System.out.println(
                            "Invalid Choice");
            }

        } while(choice != 4);
    }

    private void activitySelection() {

        System.out.println(
                "\nSelected Tasks:");

        tasks.sort(
                Comparator.comparingInt(
                        Task::getEndTime));

        int lastFinish = -1;

        for(Task task : tasks) {

            if(task.getStartTime()
                    >= lastFinish) {

                System.out.println(task);

                lastFinish =
                        task.getEndTime();
            }
        }
    }
    private void fractionalKnapsack() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Available Budget : ");

        int capacity = sc.nextInt();

        try {

            // Find the minimum project cost
            int minimumCost = Integer.MAX_VALUE;

            for(Project p : projects) {

                minimumCost = Math.min(minimumCost,
                                       p.getCost());
            }

            // Check if budget is enough
            if(capacity < minimumCost) {

                throw new BudgetExceededException(
                        "Budget is insufficient to execute any project.");
            }

        }
        catch(BudgetExceededException e) {

            System.out.println(e.getMessage());

            return;    // Stop execution
        }

        // ===== Existing Fractional Knapsack Code =====

        double totalBenefit = 0;

        projects.sort((a,b) -> {

            double r1 = (double)a.getBenefit() / a.getCost();
            double r2 = (double)b.getBenefit() / b.getCost();

            return Double.compare(r2, r1);
        });

        for(Project p : projects) {

            if(capacity >= p.getCost()) {

                capacity -= p.getCost();
                totalBenefit += p.getBenefit();

            }
            else {

                totalBenefit +=
                        ((double)capacity / p.getCost())
                        * p.getBenefit();

                break;
            }
        }

        System.out.println("\nMaximum Benefit = " + totalBenefit);
    }
    private void zeroOneKnapsack() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Available Budget : ");

        int capacity = sc.nextInt();

        try {

            if(capacity <= 0) {

                throw new BudgetExceededException(
                        "Budget should be greater than 0.");
            }

            // Find the minimum project cost
            int minimumCost = Integer.MAX_VALUE;

            for(Project p : projects) {

                minimumCost = Math.min(minimumCost,
                                       p.getCost());
            }

            // Check whether budget is sufficient
            if(capacity < minimumCost) {

                throw new BudgetExceededException(
                        "Budget is insufficient to execute any project.");
            }

        }
        catch(BudgetExceededException e) {

            System.out.println(e.getMessage());

            return;
        }

        int n = projects.size();

        int[][] dp = new int[n + 1][capacity + 1];

        for(int i = 1; i <= n; i++) {

            int cost = projects.get(i - 1).getCost();

            int benefit = projects.get(i - 1).getBenefit();

            for(int w = 1; w <= capacity; w++) {

                if(cost <= w) {

                    dp[i][w] = Math.max(
                            benefit + dp[i - 1][w - cost],
                            dp[i - 1][w]);

                }
                else {

                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("\nSelected Budget : " + capacity);

        System.out.println("Maximum Benefit : " + dp[n][capacity]);
    }
       
}