import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    public static int dijkstra(int start, int target) {
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

        return dist[target];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int e = input[1];
        graph = new int[n][n];

        int length1 = 0;
        int length2 = 0;

        for (int i = 0; i < e; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n1 = input[0] - 1;
            int n2 = input[1] - 1;
            int weight = input[2];
            graph[n1][n2] = weight;
            graph[n2][n1] = weight;
        }

        input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int curLength;
        int[] path = {0, input[0] - 1, input[1] - 1, n - 1};

        for (int i = 0; i < 3; i++) {
            curLength = dijkstra(path[i], path[i + 1]);
            if(curLength == Integer.MAX_VALUE){
                length1 = Integer.MAX_VALUE;
                break;
            } else {
                length1 += curLength;
            }
        }

        path = new int[]{0, input[1] - 1, input[0] - 1, n - 1};
        for (int i = 0; i < 3; i++) {
            curLength = dijkstra(path[i], path[i + 1]);
            if(curLength == Integer.MAX_VALUE){
                length2 = Integer.MAX_VALUE;
                break;
            } else {
                length2 += curLength;
            }
        }
        int res = Math.min(length1, length2);
        System.out.println(res == Integer.MAX_VALUE? -1 : res);
    }
}