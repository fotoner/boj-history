import java.io.*;
import java.util.*;

public class Main {
    static int[] candy;
    static int[] friendSet;

    static void union(int a, int b){
        a = find(a);
        b = find(b);
        if (a < b) {
            friendSet[a] = a;
            friendSet[b] = a;
        } else {
            friendSet[a] = b;
            friendSet[b] = b;
        }
    }
    static int find(int a){
        if (friendSet[a] == a) return a;
        return friendSet[a] = find(friendSet[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        friendSet = new int[n];
        for (int i = 0; i < n; i++) {
            friendSet[i] = i;
        }
        candy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            union(a, b);
        }
        for (int i = 0; i < n; i++) {
            find(i);
        }

        HashMap<Integer, Integer> friendCount = new HashMap<>();
        HashMap<Integer, Integer> candyCount = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int setNum = friendSet[i];
            int curCandy = candy[i];
            friendCount.put(setNum, friendCount.getOrDefault(setNum, 0) + 1);
            candyCount.put(setNum, candyCount.getOrDefault(setNum, 0) + curCandy);
        }

        int[][] dp = new int[friendCount.size() + 1][k];

        int[] keys = friendCount.keySet().stream().mapToInt(i -> i).toArray();

        for (int i = 1; i < friendCount.size() + 1; i++) {
            for (int j = 0; j < k; j++) {
                int curKey = keys[i - 1];
                int curFriend = friendCount.get(curKey);
                int curCandy = candyCount.get(curKey);

                if(curFriend > j) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curFriend] + curCandy);
            }
        }

        System.out.println(dp[friendCount.size()][k - 1]);
    }
}