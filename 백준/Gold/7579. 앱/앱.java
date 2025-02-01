import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp = new int[101][10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memory = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] times = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int sum = Arrays.stream(times).sum();
        int result = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                int curMemory = memory[i - 1];
                int curTime = times[i - 1];

                if(j < curTime) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curTime] + curMemory);
                }
                if(dp[i][j] >= m) {
                    result = Math.min(result, j);
                }
            }
        }

        System.out.println(result);
    }
}