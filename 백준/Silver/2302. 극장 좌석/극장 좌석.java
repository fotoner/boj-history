import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < 41; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int last = 0;
        int sum = 1;
        for (int i = 0; i < m; i++) {
            int value = Integer.parseInt(br.readLine());
            int length = value - last - 1;
            last = value;
            sum *= dp[length];
        }

        sum *= dp[n - last];

        System.out.println(sum);
    }
}