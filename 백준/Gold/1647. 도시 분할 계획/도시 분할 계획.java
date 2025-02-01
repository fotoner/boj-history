import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Edge> edges;
    static int[] parent;

    static class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
        }
    }

    static int find(int x) {
        if(parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a,b,c));
        }
        edges.sort((a, b) -> a.weight - b.weight);
        int sum = 0;
        int last = 0;

        for (Edge edge: edges) {
            if(find(edge.src) != find(edge.dest)) {
                sum += edge.weight;
                last = edge.weight;
                union(edge.src, edge.dest);
            }
        }

        System.out.println(sum - last);
    }
}