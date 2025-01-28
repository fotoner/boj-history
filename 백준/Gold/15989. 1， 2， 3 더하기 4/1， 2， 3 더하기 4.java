import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp = new int[10001][4];
    static {
        dp[1][1] = dp[2][1] = dp[2][2] = dp[3][1] = dp[3][2] = dp[3][3] = 1;

        for (int i = 4; i <10001 ; i++) {
            dp[i][1] = dp[i - 1][1];
            dp[i][2] = dp[i - 2][2] + dp[i - 2][1];
            dp[i][3] = dp[i - 3][3] + dp[i - 3][2] + dp[i - 3][1];
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            int idx = Integer.parseInt(br.readLine());
            sb.append(dp[idx][1] + dp[idx][2] + dp[idx][3]).append('\n');
        }

        System.out.println(sb);
    }
}