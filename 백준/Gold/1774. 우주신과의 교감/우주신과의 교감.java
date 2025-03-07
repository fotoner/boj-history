import java.io.*;
import java.util.*;

class Main {
    static int[] parent;

    static class Edge{
        int src, dest;
        double weight;

        Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    static int find(int a){
        if(parent[a] != a) return parent[a] = find(parent[a]);
        else return parent[a];
    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA < rootB){
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        ArrayList<double[]> gods = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            gods.add(new double[]{x, y});

            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            String curPath = br.readLine();
            int[] pos = Arrays.stream(curPath.split(" ")).mapToInt(Integer::parseInt).toArray();

            union(pos[0], pos[1]);
        }

        for (int i = 1; i  <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                double[] pos1 = gods.get(i - 1);
                double[] pos2 = gods.get(j - 1);

                double length = Math.sqrt(Math.pow(pos1[0] - pos2[0], 2) + Math.pow(pos1[1] - pos2[1], 2));
                edges.add(new Edge(i, j, length));
            }
        }

        edges.sort((a, b) -> {
            if(a.weight >= b.weight){
                return 1;
            } else {
                return -1;
            }
        });

        double sum = 0;

        for (Edge edge: edges) {
            if(find(edge.src) != find(edge.dest)){
                union(edge.src, edge.dest);
                sum += edge.weight;
            }
        }

        System.out.printf("%.2f", sum);
    }
}