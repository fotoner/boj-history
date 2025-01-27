import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        int[] dp = new int[1000001];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for(int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
        }

        System.out.println(dp[n]);
    }
}