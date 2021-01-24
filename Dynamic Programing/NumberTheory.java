import java.util.Scanner;

public class NumberTheory {

    public static class Pair {
        int x = 0;
        int y = 0;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    public static void EEA() {
        Scanner sc = new Scanner(System.in);
        int t = 2;
        while (t-- > 0) {
            Pair p = new Pair(0, 0);
            int a = sc.nextInt();
            int b = sc.nextInt();
            int gcdAns = gcd(a, b, p);
            System.out.println(p.x + " " + p.y + " " + gcdAns);

        }
        sc.close();
    }

    public static int gcd(int a, int b, Pair p) {
        if (b == 0) {
            p.x = 1;
            p.y = 0;
            return a;
        }

        else {
            int x1 = 0, y1 = 0;
            Pair p1 = new Pair(x1, y1);
            int d = gcd(b, a % b, p1);
            p.x = p1.y;
            p.y = p1.x - (p1.y * (a / b));
            return d;

        }
    }

    public static void main(String args[]) {
        // EEA();
    }
}
