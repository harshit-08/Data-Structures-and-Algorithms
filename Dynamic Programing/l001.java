import java.util.Arrays;

public class l001 {

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

    // Two Pointer=================================================

    // FIBONACCI

    public static int fib_01(int n, int[] dp) {
        if (n <= 1)
            return dp[n] = n;

        if (dp[n] != 0)
            return dp[n];

        int a = fib_01(n - 1, dp);
        int b = fib_01(n - 2, dp);

        return dp[n] = a + b;
    }

    public static int fib_01_DP(int N, int[] dp) {

        for (int n = 0; n <= N; n++) {
            if (n <= 1) {
                dp[n] = n;
                continue;
            }

            int a = dp[n - 1]; // fib_01(n - 1, dp);
            int b = dp[n - 2]; // fib_01(n - 2, dp);

            dp[n] = a + b;
        }
        return N;
    }

    public static int fib_01_opti(int N) {
        int a = 0, b = 1;

        for (int n = 2; n <= N; n++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static int fibo() {
        int n = 7;
        int[] dp = new int[n + 1];
        // int ans = fib_01(n, dp);
        // System.out.println(ans);
        // print(dp);
        // return ans;
        int ans_DP = fib_01_opti(n);
        System.out.println(ans_DP);
        System.out.println(dp[n]);
        return ans_DP;

    }

    // MAZE_PATH

    public static int mazePath(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        if (sr + 1 <= er) {
            count += mazePath(sr + 1, sc, er, ec, dp);
        }
        if (sc + 1 <= ec) {
            count += mazePath(sr, sc + 1, er, ec, dp);
        }
        if (sr + 1 <= er && sc + 1 <= ec) {
            count += mazePath(sr + 1, sc + 1, er, ec, dp);
        }
        return dp[sr][sc] = count;

    }

    public static int mazePathDP(int SR, int SC, int er, int ec, int[][] dp) {

        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sr] = 1;
                    continue;
                }

                int count = 0;
                if (sr + 1 <= er) {
                    count += dp[sr + 1][sc];// mazePath(sr + 1, sc, er, ec, dp);
                }
                if (sc + 1 <= ec) {
                    count += dp[sr][sc + 1];// mazePath(sr, sc + 1, er, ec, dp);
                }
                if (sr + 1 <= er && sc + 1 <= ec) {
                    count += dp[sr + 1][sc + 1];// mazePath(sr + 1, sc + 1, er, ec, dp);
                }
                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];

    }

    public static int mazePath_multiple(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;

        for (int jump = 1; jump + sr <= er; jump++) {
            count += mazePath_multiple(sr + jump, sc, er, ec, dp);
        }

        for (int jump = 1; jump + sc <= ec; jump++) {
            count += mazePath_multiple(sr, sc + jump, er, ec, dp);
        }
        for (int jump = 1; jump + sr <= er && jump + sc <= ec; jump++) {
            count += mazePath_multiple(sr + jump, sc + jump, er, ec, dp);
        }

        return dp[sr][sc] = count;

    }

    public static int mazePath_multipleDP(int SR, int SC, int er, int ec, int[][] dp) {

        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {

                if (sr == er && sc == ec) {
                    dp[sr][sc] = 1;
                    continue;

                }

                int count = 0;

                for (int jump = 1; jump + sr <= er; jump++) {
                    count += dp[sr + jump][sc];// mazePath_multiple(sr + jump, sc, er, ec, dp);
                }

                for (int jump = 1; jump + sc <= ec; jump++) {
                    count += dp[sr][sc + jump];// mazePath_multiple(sr, sc + jump, er, ec, dp);
                }
                for (int jump = 1; jump + sr <= er && jump + sc <= ec; jump++) {
                    count += dp[sr + jump][sc + jump];// mazePath_multiple(sr + jump, sc + jump, er, ec, dp);
                }

                dp[sr][sc] = count;
            }
        }
        return dp[SR][SC];

    }

    public static void mazePath() {
        int n = 4;
        int m = 4;
        int[][] dp = new int[n][m];
        // int[][] dir = { { 0, 1 }, { 1, 1 }, { 1, 0 } };
        // System.out.println(mazePathDP(0, 0, n - 1, m - 1, dp));
        System.out.println(mazePath_multipleDP(0, 0, n - 1, m - 1, dp));
        print2D(dp);
    }

    // Leetcode 62

    public static int uniquePaths(int sr, int sc, int er, int ec, int[][] dp) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        if (sr + 1 <= er) {
            count += uniquePaths(sr + 1, sc, er, ec, dp);
        }
        if (sc + 1 <= ec) {
            count += uniquePaths(sr, sc + 1, er, ec, dp);
        }

        return dp[sr][sc] = count;

    }

    // Leetcode 63(in main check if grid[0][0] == 1. If yes then return 0);

    public static int uniquePathsWithObstacles(int sr, int sc, int er, int ec, int[][] grid, int[][] dp) {
        if (sr == er && sc == ec)
            return dp[sr][sc] = 1;

        if (dp[sr][sc] != 0)
            return dp[sr][sc];

        int count = 0;
        if (sr + 1 <= er && grid[sr + 1][sc] != 1) {
            count += uniquePathsWithObstacles(sr + 1, sc, er, ec, grid, dp);
        }
        if (sc + 1 <= ec && grid[sr][sc + 1] != 1) {
            count += uniquePathsWithObstacles(sr, sc + 1, er, ec, grid, dp);
        }

        return dp[sr][sc] = count;

    }

    // Leetcode 64

    public int minPathSum(int sr, int sc, int er, int ec, int[][] grid, int[][] dp) {
        if (sr == er && sc == ec) {
            return dp[sr][sc] = grid[sr][sc];
        }

        if (dp[sr][sc] != -1) {
            return dp[sr][sc];
        }

        int minCost = (int) 1e8;

        if (sr + 1 <= er) {
            minCost = Math.min(minCost, minPathSum(sr + 1, sc, er, ec, grid, dp));
        }
        if (sc + 1 <= ec) {
            minCost = Math.min(minCost, minPathSum(sr, sc + 1, er, ec, grid, dp));
        }
        return dp[sr][sc] = grid[sr][sc] + minCost;
    }

    public int minPathSumDP(int SR, int SC, int er, int ec, int[][] grid, int[][] dp) {

        for (int sr = er; sr >= 0; sr--) {
            for (int sc = ec; sc >= 0; sc--) {
                if (sr == er && sc == ec) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }

                int minCost = (int) 1e8;

                if (sr + 1 <= er) {
                    minCost = Math.min(minCost, dp[sr + 1][sc]);
                }
                if (sc + 1 <= ec) {
                    minCost = Math.min(minCost, dp[sr][sc + 1]);
                }
                dp[sr][sc] = grid[sr][sc] + minCost;
            }
        }
        return dp[SR][SC];

    }

    // Geeks GoldMine Problem : https://www.geeksforgeeks.org/gold-mine-problem/

    public static int goldMine(int sr, int sc, int er, int ec, int[][] grid, int[][] dp) {

        if (sc == ec) {
            return dp[sr][sc] = grid[sr][sc];
        }

        if (dp[sr][sc] != -1) {
            return dp[sr][sc];
        }

        int maxGold = -(int) 1e8;
        if (sc + 1 <= ec) {
            maxGold = Math.max(maxGold, goldMine(sr, sc + 1, er, ec, grid, dp));
        }
        if (sc + 1 <= ec && sr - 1 >= 0) {
            maxGold = Math.max(maxGold, goldMine(sr - 1, sc + 1, er, ec, grid, dp));
        }
        if (sc + 1 <= ec && sr + 1 <= er) {
            maxGold = Math.max(maxGold, goldMine(sr + 1, sc + 1, er, ec, grid, dp));
        }
        return dp[sr][sc] = maxGold + grid[sr][sc];

    }

    public static int goldMineDP(int SR, int SC, int er, int ec, int[][] grid, int[][] dp) {

        for (int sc = ec; sc >= 0; sc--) {
            for (int sr = er; sr >= 0; sr--) {

                if (sc == ec) {
                    dp[sr][sc] = grid[sr][sc];
                    continue;
                }

                int maxGold = -(int) 1e8;
                if (sc + 1 <= ec) {
                    maxGold = Math.max(maxGold, dp[sr][sc + 1]);// goldMine(sr, sc + 1, er, ec, grid, dp));
                }
                if (sc + 1 <= ec && sr - 1 >= 0) {
                    maxGold = Math.max(maxGold, dp[sr - 1][sc + 1]);// goldMine(sr - 1, sc + 1, er, ec, grid, dp));
                }
                if (sc + 1 <= ec && sr + 1 <= er) {
                    maxGold = Math.max(maxGold, dp[sr + 1][sc + 1]);// goldMine(sr + 1, sc + 1, er, ec, grid, dp));
                }
                dp[sr][sc] = maxGold + grid[sr][sc];
            }
        }
        return dp[SR][SC];

    }

    public static void goldMine() {
        int[][] grid = { { 10, 33, 13, 15 }, { 22, 21, 04, 1 }, { 5, 0, 2, 3 }, { 0, 6, 14, 2 } };
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            ans = Math.max(ans, goldMineDP(i, 0, m - 1, n - 1, grid, dp));
        }
        System.out.println(ans);
        print2D(dp);

    }

    // Geeks Friends Paring : https://www.geeksforgeeks.org/friends-pairing-problem/

    public static int friendsParing(int n, int[] dp) {

        if (n == 0 || n == 1) {
            return dp[n] = 1;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int pairs = friendsParing(n - 1, dp) + friendsParing(n - 2, dp) * n - 1;

        return dp[n] = pairs;
    }

    public static void friendsParing() {
        int n = 2;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        int ans = friendsParing(n, dp);
        System.out.println(ans);
    }

    // Leetcode 70 Clibing Stairs
    public int climbStairs(int n, int[] dp) {
        if (n <= 0) {
            if (n < 0)
                return 0;
            else
                return dp[n] = 1;
        }
        if (dp[n] != -1) {
            return dp[n];
        }
        int paths = 0;
        paths = climbStairs(n - 1, dp) + climbStairs(n - 2, dp);

        return dp[n] = paths;
    }

    // Leetcode 746

    public int minCostClimbingStairs(int[] cost, int n, int[] dp) {

        if (n <= 0) {
            if (n == 0)
                return dp[n] = cost[n];
            else
                return 0;
        }

        if (dp[n] != -1)
            return dp[n];
        int minCost = (int) 1e8;
        minCost = Math.min(minCost, minCostClimbingStairs(cost, n - 1, dp));
        minCost = Math.min(minCost, minCostClimbingStairs(cost, n - 2, dp));

        return dp[n] = minCost + ((n < cost.length) ? cost[n] : 0);
    }

    public static int boardPath(int n, int[] dp) {

        if (n == 0) {
            return dp[n] = 1;
        }

        if (dp[n] != -1) {
            return dp[n];
        }

        int paths = 0;
        for (int i = 1; i <= 6; i++) {
            if (n - i >= 0) {
                paths += boardPath(n - i, dp);
            }
        }

        return dp[n] = paths;

    }

    public static int boardPathDP(int N, int[] dp) {

        for (int n = 0; n <= N; n++) {

            if (n == 0) {
                dp[n] = 1;
                continue;
            }

            int paths = 0;
            for (int i = 1; i <= 6; i++) {
                if (n - i >= 0) {
                    paths += dp[n - i]; // boardPath(n - i, dp);
                }
            }

            dp[n] = paths;
        }
        return dp[N];
    }

    public static void boardPath() {
        int n = 7;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        System.out.println(boardPathDP(n, dp));
        print(dp);
    }

    // Leetcode 91

    public static int numDecodings(String s, int idx, int[] dp) {

        if (idx == s.length()) {
            return dp[idx] = 1;
        }

        if (dp[idx] != -1)
            return dp[idx];

        char ch = s.charAt(idx);
        if (ch == '0')
            return dp[idx] = 0;

        int count = 0;
        count += numDecodings(s, idx + 1, dp);

        if (idx < s.length() - 1) {
            int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
            if (num <= 26) {
                count += numDecodings(s, idx + 2, dp);
            }
        }

        return dp[idx] = count;

    }

    public static void numDecodings() {

        String s = "12";

        if (s.length() == 0 || s.charAt(0) == 0) {
            System.out.println("0");
            return;
        }
        int[] dp = new int[s.length() + 1];
        Arrays.fill(dp, -1);

        int ans = numDecodings(s, 0, dp);
        System.out.println(ans);

    }

    // Leetcode 639
    static int mod = (int) 1e9 + 7;

    public static int numDecodings2(String s, int idx, int[] dp) {

        if (idx == s.length())
            return dp[idx] = 1;

        if (s.charAt(idx) == '0')
            return dp[idx] = 0;

        if (dp[idx] != -1)
            return dp[idx];

        int count = 0;
        if (s.charAt(idx) == '*') {
            count = (count + 9 * numDecodings2(s, idx + 1, dp) % mod) % mod;
            if (idx < s.length() - 1 && s.charAt(idx + 1) >= '0' && s.charAt(idx + 1) <= '6') {
                count = (count + 2 * numDecodings2(s, idx + 2, dp) % mod) % mod;
            } else if (idx < s.length() - 1 && s.charAt(idx + 1) >= '7') {
                count = (count + numDecodings2(s, idx + 2, dp) % mod) % mod;
            } else if (idx < s.length() - 1 && s.charAt(idx + 1) == '*') {
                count = (count + 15 * numDecodings2(s, idx + 2, dp) % mod) % mod;
            }

        } else {
            count = (count + numDecodings2(s, idx + 1, dp) % mod) % mod;
            if (idx < s.length() - 1 && s.charAt(idx + 1) == '*') {
                if (s.charAt(idx) == '1') {
                    count = (count + 9 * numDecodings2(s, idx + 2, dp) % mod) % mod;
                } else if (s.charAt(idx) == '2') {
                    count = (count + 6 * numDecodings2(s, idx + 2, dp) % mod) % mod;
                }
            } else if (idx < s.length() - 1) {
                int num = (s.charAt(idx) - '0') * 10 + (s.charAt(idx + 1) - '0');
                if (num <= 26) {
                    count = (count + numDecodings2(s, idx + 2, dp) % mod) % mod;
                }
            }

        }
        return dp[idx] = count;

    }

    public static void main(String[] args) {
        // fibo();
        // mazePath();
        // mazePathDP();
        // goldMine();
        // friendsParing();
        // boardPath();
        // numDecodings();

    }
}
