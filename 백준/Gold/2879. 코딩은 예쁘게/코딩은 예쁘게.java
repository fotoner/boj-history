import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[n];
        int[] asis = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] tobe = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            diff[i] = tobe[i] - asis[i];
        }
        dp[0] = Math.abs(diff[0]);

        for (int i = 1; i < n; i++) {
            if(diff[i - 1] * diff[i] > 0) {
                dp[i] = dp[i - 1] + Math.max(0, Math.abs(diff[i]) - Math.abs(diff[i - 1]));
            } else {
                dp[i] = dp[i - 1] + Math.abs(diff[i]);
            }
        }

        System.out.println(dp[n - 1]);
    }
}