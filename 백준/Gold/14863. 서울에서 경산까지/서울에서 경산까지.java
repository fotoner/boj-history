import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] bike = new int[2][n];
        int[][] walk = new int[2][n];
        int[][] dp = new int[n][k + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            bike[0][i] = Integer.parseInt(st.nextToken());
            bike[1][i] = Integer.parseInt(st.nextToken());

            walk[0][i] = Integer.parseInt(st.nextToken());
            walk[1][i] = Integer.parseInt(st.nextToken());
        }
        dp[0][walk[0][0]] = walk[1][0];
        dp[0][bike[0][0]] = Math.max(dp[0][bike[0][0]], bike[1][0]);

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < k; j++) {
                if(dp[i - 1][j] > 0) {
                    if (j + walk[0][i] <= k) {
                        dp[i][j + walk[0][i]] = Math.max(dp[i][j + walk[0][i]], dp[i - 1][j] + walk[1][i]);
                    }
                    if (j + bike[0][i] <= k) {
                        dp[i][j + bike[0][i]] = Math.max(dp[i][j + bike[0][i]], dp[i - 1][j] + bike[1][i]);
                    }
                }
            }
        }
        int ans = 0;
        for(int i = 0; i <= k; i++) ans = Math.max(ans, dp[n - 1][i]);
        System.out.println(ans);
    }
}