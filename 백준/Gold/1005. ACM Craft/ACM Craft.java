import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        while(t-- > 0) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] dp = new int[n + 1];
            int[][] graph = new int[n][n];
            int[] values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] indegree  = new int[n];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());

                int a1 = Integer.parseInt(st.nextToken()) - 1;
                int a2 = Integer.parseInt(st.nextToken()) - 1;

                graph[a1][a2] = 1;
                indegree[a2]++;
            }
            st = new StringTokenizer(br.readLine());

            int w = Integer.parseInt(st.nextToken()) - 1;

            Queue<Integer> q = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                if (indegree[i] == 0){
                    q.add(i);
                }
            }

            while (!q.isEmpty()) {
                int curNode = q.poll();

                if(curNode == w)
                    break;

                for (int i = 0; i < n; i++) {
                    if(graph[curNode][i] == 1) {
                        indegree[i]--;
                        dp[i] = Math.max(dp[i], dp[curNode] + values[curNode]);
                        if(indegree[i] == 0){
                            q.add(i);
                        }
                    }
                }
            }

            System.out.println(dp[w] + values[w]);
        }
    }
}