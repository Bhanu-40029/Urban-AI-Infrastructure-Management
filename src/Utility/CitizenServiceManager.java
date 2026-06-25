package Utility;

import java.util.Scanner;

public class CitizenServiceManager {

    public void displayCitizenMenu() {

        Scanner sc = new Scanner(System.in);

        int choice;

        do {

            System.out.println(
                    "\n===== CITIZEN SERVICE MANAGEMENT =====");

            System.out.println(
                    "1. Edit Distance");

            System.out.println(
                    "2. Longest Common Subsequence");

            System.out.println(
                    "3. Back");

            System.out.print(
                    "Enter Choice : ");

            choice = sc.nextInt();

            sc.nextLine();

            switch(choice) {

                case 1:

                    System.out.print(
                            "Enter First String : ");

                    String s1 =
                            sc.nextLine();

                    System.out.print(
                            "Enter Second String : ");

                    String s2 =
                            sc.nextLine();

                    int distance =
                            editDistance(
                                    s1,
                                    s2);

                    System.out.println(
                            "\nEdit Distance = "
                            + distance);

                    break;

                case 2:

                    System.out.print(
                            "Enter First String : ");

                    String str1 =
                            sc.nextLine();

                    System.out.print(
                            "Enter Second String : ");

                    String str2 =
                            sc.nextLine();

                    String lcs =
                            longestCommonSubsequence(
                                    str1,
                                    str2);

                    System.out.println(
                            "\nLCS = "
                            + lcs);

                    System.out.println(
                            "Length = "
                            + lcs.length());

                    break;

                case 3:

                    break;

                default:

                    System.out.println(
                            "Invalid Choice");
            }

        } while(choice != 3);
    }

    private int editDistance(
            String s1,
            String s2) {

        int m = s1.length();
        int n = s2.length();

        int[][] dp =
                new int[m + 1][n + 1];

        for(int i=0;i<=m;i++) {

            for(int j=0;j<=n;j++) {

                if(i == 0) {

                    dp[i][j] = j;
                }

                else if(j == 0) {

                    dp[i][j] = i;
                }

                else if(
                        s1.charAt(i-1)
                        ==
                        s2.charAt(j-1)) {

                    dp[i][j]
                            =
                            dp[i-1][j-1];
                }

                else {

                    dp[i][j]
                            =
                            1
                            +
                            Math.min(
                                    dp[i-1][j-1],

                                    Math.min(
                                            dp[i-1][j],
                                            dp[i][j-1]));
                }
            }
        }

        return dp[m][n];
    }

    private String longestCommonSubsequence(
            String s1,
            String s2) {

        int m = s1.length();
        int n = s2.length();

        int[][] dp =
                new int[m+1][n+1];

        for(int i=1;i<=m;i++) {

            for(int j=1;j<=n;j++) {

                if(s1.charAt(i-1)
                        ==
                        s2.charAt(j-1)) {

                    dp[i][j]
                            =
                            dp[i-1][j-1]
                            + 1;
                }

                else {

                    dp[i][j]
                            =
                            Math.max(
                                    dp[i-1][j],
                                    dp[i][j-1]);
                }
            }
        }

        StringBuilder lcs =
                new StringBuilder();

        int i = m;
        int j = n;

        while(i > 0 &&
              j > 0) {

            if(s1.charAt(i-1)
                    ==
                    s2.charAt(j-1)) {

                lcs.append(
                        s1.charAt(i-1));

                i--;
                j--;
            }

            else if(
                    dp[i-1][j]
                    >
                    dp[i][j-1]) {

                i--;
            }

            else {

                j--;
            }
        }

        return lcs.reverse()
                  .toString();
    }
}