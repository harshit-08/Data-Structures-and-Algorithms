import java.util.Arrays;
import java.util.Scanner;

public class TargetSet {

    public static void print(int[] arr) {
        for (int e : arr) {

            System.out.print(e + " ");

        }
    }

    public static void print2D(int[][] arr) {

        for (int[] ar : arr) {
            print(ar);
            System.out.println();
        }

    }

    public static int coinChangePermutation(int[] arr, int tar, int[] dp) {

        if (tar == 0)
            return dp[tar] = 1;

        if (dp[tar] != 0)
            return dp[tar];

        for (int ele : arr) {
            if (tar - ele >= 0) {
                dp[tar] += coinChangePermutation(arr, tar - ele, dp);
            }
        }

        return dp[tar];
    }

    public static void coinChangePermutation() {
        int[] arr = { 2, 3, 5, 7 };
        int tar = 10;
        int[] dp = new int[tar + 1];

        System.out.println(coinChangePermutation(arr, tar, dp));
    }

    // public static int coinChangeCombination() {

    // }

    // Leetcode 322

    public static int minCoinReq(int[] coins, int amount, int[] dp) {

        if (amount == 0)
            return 0;

        if (dp[amount] != -1)
            return dp[amount];

        int minVal = (int) 1e8;

        for (int ele : coins) {
            if (amount - ele >= 0) {
                minVal = Math.min(minVal, minCoinReq(coins, amount - ele, dp) + 1);

            }
        }
        return dp[amount] = minVal;
    }

    public static int minCoinReq(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);

        int ans = minCoinReq(coins, amount, dp);
        return ans != (int) 1e8 ? ans : -1;

    }

    // https://www.geeksforgeeks.org/subset-sum-problem-dp-25/

    public static int countSubsetSum(int[] arr, int sum, int[][] dp, int n) {

        if (sum == 0 || n == 0) {
            dp[sum][n] = (sum == 0) ? 1 : 0;
        }

        if (dp[sum][n] != -1)
            return dp[sum][n];

        int count = 0;
        if (sum - arr[n - 1] >= 0) {
            count += countSubsetSum(arr, sum - arr[n - 1], dp, n - 1);
        }
        count += countSubsetSum(arr, sum, dp, n - 1);

        return dp[sum][n] = count;

    }

    public static int countSubsetSum_DP(int[] arr, int SUM, int[][] dp, int N) {

        for (int sum = 0; sum <= SUM; sum++) {
            for (int n = 0; n <= N; n++) {
                if (sum == 0 || n == 0) {
                    dp[sum][n] = (sum == 0) ? 1 : 0;
                    continue;
                }

                int count = 0;
                if (sum - arr[n - 1] >= 0) {
                    count += dp[sum - arr[n - 1]][n - 1]; // subsetSum(arr, sum - arr[n - 1], dp, n - 1);
                }
                count += dp[sum][n - 1]; // subsetSum(arr, sum, dp, n - 1);

                dp[sum][n] = count;
            }
        }
        return dp[SUM][N];

    }

    public static void countSubsetSum() {
        int[] arr = { 2, 3, 5, 7 };
        int sum = 10;
        int n = arr.length;
        int[][] dp = new int[sum + 1][n + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(countSubsetSum(arr, sum, dp, n));

        print2D(dp);
    }

    // Subset sum

    public static boolean subsetSum(int[] arr, int tar, int n, boolean[][] dp) {

        if (n == 0 || tar == 0) {
            return tar == 0 ? true : false;
        }

        if (dp[tar][n] != false) {
            return dp[tar][n];
        }

        boolean resInclude = false, resExclude = false;
        if (tar - arr[n - 1] >= 0) {
            resInclude = subsetSum(arr, tar - arr[n - 1], n - 1, dp);
        }
        resExclude = subsetSum(arr, tar, n - 1, dp);

        return dp[tar][n] = resInclude || resExclude;

    }

    public static void subsetSum() {
        int[] arr = { 7, 4 };
        int tar = 18;
        int n = arr.length;
        boolean[][] dp = new boolean[tar + 1][n + 1];
        for (boolean[] d : dp) {
            Arrays.fill(d, false);
        }
        if (subsetSum(arr, tar, n, dp)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    // Knapsack 0-1
    public static int knapsack01(int[] w, int[] v, int tar, int n, int[][] dp) {

        if (tar == 0 || n == 0) {
            return dp[tar][n] = 0;
        }

        if (dp[tar][n] != -1)
            return dp[tar][n];

        int maxVal = 0;
        if (tar - w[n - 1] >= 0) {
            maxVal = Math.max(maxVal, knapsack01(w, v, tar - w[n - 1], n - 1, dp) + v[n - 1]);
        }
        maxVal = Math.max(maxVal, knapsack01(w, v, tar, n - 1, dp));

        return dp[tar][n] = maxVal;
    }

    public static int knapsack01_DP(int[] w, int[] v, int Tar, int N, int[][] dp) {

        for (int tar = 0; tar <= Tar; tar++) {
            for (int n = 0; n <= N; n++) {
                if (tar == 0 || n == 0) {
                    dp[tar][n] = 0;
                    continue;
                }

                int maxVal = 0;
                if (tar - w[n - 1] >= 0) {
                    maxVal = Math.max(maxVal, dp[tar - w[n - 1]][n - 1] + v[n - 1]);
                }
                maxVal = Math.max(maxVal, dp[tar][n - 1]);

                dp[tar][n] = maxVal;
            }
        }
        return dp[Tar][N];

    }

    public static void knapsack01() {
        int[] w = { 10, 20, 30 };
        int[] v = { 60, 100, 120 };
        int tar = 40;
        int n = w.length;
        int[][] dp = new int[tar + 1][n + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(knapsack01_DP(w, v, tar, n, dp));
    }

    // Leetcode 494

    public static int findTargetSumWays(int[] arr, int tar, int sum, int n, int[][] dp) {

        if (n == 0) {
            return dp[sum][n] = (sum == tar) ? 1 : 0;
        }

        if (dp[sum][n] != -1)
            return dp[sum][n];

        int count = 0;
        count += findTargetSumWays(arr, tar, sum + arr[n - 1], n - 1, dp);
        count += findTargetSumWays(arr, tar, sum - arr[n - 1], n - 1, dp);
        return dp[sum][n] = count;

    }

    public static int findTargetSumWays_DP(int[] arr, int tar, int Sum, int N, int[][] dp) {

        for (int sum = Sum; sum <= Sum; sum++) {
            for (int n = 0; n <= N; n++) {
                if (n == 0) {
                    dp[sum][n] = (sum == tar) ? 1 : 0;
                    continue;
                }

                int count = 0;
                count += dp[sum + arr[n - 1]][n - 1]; // findTargetSumWays(arr, tar, sum + arr[n - 1], n - 1, dp);
                count += dp[sum - arr[n - 1]][n - 1]; // findTargetSumWays(arr, tar, sum - arr[n - 1], n - 1, dp);
                dp[sum][n] = count;

            }
        }
        return dp[Sum][N];

    }

    public static int findTargetSumWays(int[] nums, int S) {

        int sum = 0;
        for (int ele : nums) {
            sum += ele;
        }
        if (S > sum || S < -sum)
            return 0;
        int n = nums.length;
        int[][] dp = new int[2 * sum + 1][n + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return findTargetSumWays(nums, S + sum, sum, n, dp);

    }

    public static void main(String[] args) {

    }

}
