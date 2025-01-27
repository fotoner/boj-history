import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    public static int[] dijkstra(int start) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];
            if (d > dist[u]) continue;

            for (int v = 0; v < n; v++) {
                if (graph[u][v] > 0 && dist[v] > dist[u] + graph[u][v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }

        return dist;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        graph = new int[n][n];

        int[] items = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            graph[a][b] = w;
            graph[b][a] = w;
        }
        int max = 0;
        for(int i = 0; i < n; i++) {
            int[] dist = dijkstra(i);
            int curMax = 0;
            for (int j = 0; j < dist.length; j++) {
                int val = dist[j];
                if (val <= m) {
                    curMax += items[j];
                }
            }
            max = Math.max(curMax, max);
        }
        System.out.println(max);
    }
}