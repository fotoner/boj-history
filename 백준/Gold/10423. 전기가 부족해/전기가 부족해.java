import java.io.*;
import java.util.*;

class Main {
    static HashSet<Integer> plant = new HashSet<>();
    static int[] parent;

    static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]); // 경로 압축
        return parent[x];
    }

    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        boolean xPlant = plant.contains(rootX);
        boolean yPlant = plant.contains(rootY);

        if(xPlant && yPlant) {
            return false;
        }
        else if (xPlant) {
            parent[rootY] = rootX;
        } else if (yPlant) {
            parent[rootX] = rootY;
        } else {
            if (rootX != rootY) parent[rootY] = rootX;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int val: Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()){
            plant.add(val - 1);
        }
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            edges.add(new Edge(input[0] - 1, input[1] - 1, input[2]));
        }

        edges.sort(Edge::compareTo);
        int sum = 0;
        for (Edge edge: edges) {
            if(find(edge.src) != find(edge.dest)) {
                boolean res = union(edge.src, edge.dest);
                if (res) {
                    sum += edge.weight;
                }
            }
        }

        System.out.println(sum);
    }
}