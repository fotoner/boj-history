import java.io.*;
import java.util.*;

public class Main {
    static int[] dp = new int[17];
    static int[][] input = new int[16][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            input[i][0] = t;
            input[i][1] = p;
        }

        for(int i = n; i != 0; --i) {
            int t = input[i][0];
            int p = input[i][1];

            if(t + i > n + 1) {
                dp[i] = dp[i + 1];
                continue;
            }

            dp[i] = Math.max(dp[i + 1], p + dp[i + t]);
        }

        System.out.println(dp[1]);
    }
}