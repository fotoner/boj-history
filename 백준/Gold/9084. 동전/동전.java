import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t  > 0) {
            t--;
            br.readLine();
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int money = Integer.parseInt(br.readLine());
            int[] dp = new int[money + 1];
            dp[0] = 1;

            for (int coin: arr) {
                for (int i = 0; i <= money; i++) {
                    if (i >= coin)
                        dp[i] += dp[i - coin];
                }
            }

            System.out.println(dp[money]);
        }
    }
}