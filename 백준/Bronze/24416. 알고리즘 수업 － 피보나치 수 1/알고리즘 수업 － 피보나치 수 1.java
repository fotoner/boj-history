import java.io.*;

class Main {
    static int[] dp = new int[41];
    static int fibCount = 0;
    static int dpCount = 0;

    static int fib(int n) {
        if(n == 1 || n == 2){
            fibCount++;
            return 1;
        }
        return fib(n - 1) + fib(n  - 2);
    }

    static int fibDp(int n) {
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dpCount++;
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        fib(n);
        fibDp(n);

        System.out.println(fibCount + " " + dpCount);
    }
}