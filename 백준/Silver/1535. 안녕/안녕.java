import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp = new int[21][101];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] l = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] p = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 1; i < 100; i++) {
            for (int j = 0; j < n; j++) {
                int curHealth = l[j];
                int curPoint = p[j];

                if (curHealth > i) {
                    dp[j + 1][i] = dp[j][i];
                    continue;
                }

                dp[j + 1][i] = Math.max(dp[j][i], curPoint + dp[j][i - curHealth]);
            }
        }

        System.out.println(dp[n][99]);
    }
}