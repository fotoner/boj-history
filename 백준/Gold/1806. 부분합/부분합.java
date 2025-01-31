import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int res = Integer.MAX_VALUE;
        int sum = 0;
        int firstIdx = 0;

        for (int lastIdx = 0; lastIdx < n; lastIdx++) {
            sum += arr[lastIdx];

            while (sum >= s){
                res = Math.min(lastIdx - firstIdx + 1, res);

                if(sum - arr[firstIdx] < s)
                    break;

                sum -= arr[firstIdx];
                firstIdx++;
            }
        }
        System.out.println(res == Integer.MAX_VALUE? 0: res);
    }
}