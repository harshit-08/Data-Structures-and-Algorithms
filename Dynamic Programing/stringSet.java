import java.util.Arrays;

public class stringSet {

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

    // Leetcode 647

    public static int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        countSubstrings(s, dp);
        int count = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (dp[i][j])
                    count++;
            }
        }
        return count;

    }

    public static void countSubstrings(String s, boolean[][] dp) {
        int n = s.length();
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (gap == 0)
                    dp[i][j] = true;
                else if (gap == 1)
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                else
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
            }
        }
    }

    // Leetcode 005

    public static String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        countSubstrings(s, dp);
        int pt1 = 0;
        int pt2 = 0;
        for (int gap = 0; gap < n; gap++) {
            for (int i = 0, j = gap; j < n; i++, j++) {
                if (dp[i][j]) {
                    pt1 = i;
                    pt2 = j;
                }
            }
        }
        return s.substring(pt1, pt2 + 1);

    }

    // Leetcode 516

    public static int longestPallindroeSubseq(String s, int i, int j, int[][] dp) {

        if (i > j || i == j) {
            if (i == j)
                return dp[i][j] = 1;
            return dp[i][j] = 0;
        }

        if (dp[i][j] != -1)
            return dp[i][j];

        if (s.charAt(i) == s.charAt(j))
            dp[i][j] = longestPallindroeSubseq(s, i + 1, j - 1, dp) + 2;
        else
            dp[i][j] = Math.max(longestPallindroeSubseq(s, i + 1, j, dp), longestPallindroeSubseq(s, i, j - 1, dp));
        return dp[i][j];

    }

    // Leetcode 115 (HARD - IMPORTANT)

    public static int numDistinct(String s, String t, int n, int m, int[][] dp) {

        if (m == 0)
            return dp[n][m] = 1;

        if (n < m)
            return dp[n][m] = 0;

        if (dp[n][m] != -1)
            return dp[n][m];

        int count = 0;
        if (s.charAt(n - 1) == t.charAt(m - 1))
            count += numDistinct(s, t, n - 1, m - 1, dp) + numDistinct(s, t, n - 1, m, dp);
        else
            count += numDistinct(s, t, n - 1, m, dp);

        return dp[n][m] = count;

    }

    public static void numDistinct() {
        String s = "rabbbit";
        String t = "rabbit";
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp) {
            Arrays.fill(d, -1);
        }
        System.out.println(numDistinct(s, t, n, m, dp));

    }

    // GFG - Count pallindromic subsequences (IMPORTANT)

    public static int countPS(String str, int i, int j, int[][] dp) {
        if (i >= j)
            dp[i][j] = (i == j) ? 1 : 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        int x = countPS(str, i + 1, j - 1, dp);
        int y = countPS(str, i, j - 1, dp);
        int z = countPS(str, i + 1, j, dp);

        if (str.charAt(i) == str.charAt(j))
            dp[i][j] = x + 1 + (y + z - x);
        else
            dp[i][j] = y + z - x;

        return dp[i][j];

    }

    public static int countPS(String str) {
        int n = str.length();
        int[][] dp = new int[n][n];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return countPS(str, 0, n - 1, dp);
    }

    // Leetcode 1143

    public static void longestCommonSubsequence() {
        String s1 = "abcde";
        String s2 = "ace";
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        System.out.println(longestCommonSubsequence(s1, s2, n, m, dp));

    }

    public static int longestCommonSubsequence(String s1, String s2, int n, int m, int[][] dp) {

        if (n == 0 || m == 0)
            return dp[n][m] = 0;

        if (dp[n][m] != -1)
            return dp[n][m];

        if (s1.charAt(n - 1) == s2.charAt(m - 1))
            dp[n][m] = longestCommonSubsequence(s1, s2, n - 1, m - 1, dp) + 1;
        else
            dp[n][m] = Math.max(longestCommonSubsequence(s1, s2, n, m - 1, dp),
                    longestCommonSubsequence(s1, s2, n - 1, m, dp));

        return dp[n][m];

    }

    // Leetcode 1035

    // Leetcode 72

    public static int minDistance(String s1, String s2, int n, int m, int[][] dp) {

        if (n == 0 || m == 0) {
            if (n == 0)
                return dp[n][m] = m;
            return dp[n][m] = n;

        }

        if (dp[n][m] != -1)
            return dp[n][m];

        if (s1.charAt(n - 1) == s2.charAt(m - 1))
            dp[n][m] = minDistance(s1, s2, n - 1, m - 1, dp);
        else
            dp[n][m] = Math.min(Math.min(minDistance(s1, s2, n, m - 1, dp), minDistance(s1, s2, n - 1, m, dp)),
                    minDistance(s1, s2, n - 1, m - 1, dp)) + 1;

        return dp[n][m];

    }

    public static int minDistance(String s1, String s2) {

        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        return minDistance(s1, s2, n, m, dp);

    }

    public static void main(String[] args) {
        // numDistinct();
        // longestCommonSubsequence();
    }

}
