/**
 * MST (Minimum Spanning Tree, 최소 스패닝 트리) 템플릿
 *
 * 사용 사례:
 * - 모든 노드를 연결하는 최소 비용 찾기
 * - 네트워크 설계 문제
 *
 * Kruskal 알고리즘:
 * - 간선을 비용 순으로 정렬
 * - Union-Find로 사이클 검사하며 간선 추가
 * - 시간복잡도: O(E log E)
 *
 * Prim 알고리즘:
 * - 시작 정점에서 인접한 간선 중 최소 비용 선택
 * - PriorityQueue 사용
 * - 시간복잡도: O(E log V)
 *
 * 참고 문제:
 * - BOJ 1647 도시 분할 계획
 * - BOJ 1774 우주신과의 교감
 * - BOJ 4386 별자리 만들기
 */

import java.io.*;
import java.util.*;

public class MST {

    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점 개수
        int m = Integer.parseInt(st.nextToken()); // 간선 개수

        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges.add(new Edge(from, to, cost));
        }

        // Kruskal 알고리즘
        long result = kruskal(n, edges);
        System.out.println("MST 비용: " + result);
    }

    /**
     * Kruskal 알고리즘 (Union-Find 사용)
     */
    static long kruskal(int n, ArrayList<Edge> edges) {
        // 간선을 비용 순으로 정렬
        Collections.sort(edges);

        // Union-Find 초기화
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        long totalCost = 0;
        int edgeCount = 0;

        for (Edge edge : edges) {
            // 사이클이 생기지 않으면 간선 추가
            if (union(edge.from, edge.to)) {
                totalCost += edge.cost;
                edgeCount++;

                // n-1개의 간선을 선택하면 종료
                if (edgeCount == n - 1) {
                    break;
                }
            }
        }

        return totalCost;
    }

    /**
     * Kruskal 변형: 최대 비용 간선 제외
     * 예: BOJ 1647 도시 분할 계획
     * (두 개의 그룹으로 나누기 = 가장 비용이 큰 간선 제거)
     */
    static long kruskalExcludeMax(int n, ArrayList<Edge> edges) {
        Collections.sort(edges);

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        long totalCost = 0;
        int maxCost = 0;
        int edgeCount = 0;

        for (Edge edge : edges) {
            if (union(edge.from, edge.to)) {
                totalCost += edge.cost;
                maxCost = edge.cost;  // 마지막에 추가된 간선이 최대 비용
                edgeCount++;

                if (edgeCount == n - 1) {
                    break;
                }
            }
        }

        return totalCost - maxCost;  // 최대 비용 간선 제외
    }

    /**
     * Prim 알고리즘 (PriorityQueue 사용)
     */
    static long prim(int n, ArrayList<ArrayList<Edge>> graph) {
        boolean[] visited = new boolean[n + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // 1번 정점에서 시작
        visited[1] = true;
        for (Edge edge : graph.get(1)) {
            pq.offer(edge);
        }

        long totalCost = 0;
        int edgeCount = 0;

        while (!pq.isEmpty() && edgeCount < n - 1) {
            Edge edge = pq.poll();

            // 이미 방문한 정점이면 스킵
            if (visited[edge.to]) {
                continue;
            }

            visited[edge.to] = true;
            totalCost += edge.cost;
            edgeCount++;

            // 새로운 정점의 인접 간선 추가
            for (Edge nextEdge : graph.get(edge.to)) {
                if (!visited[nextEdge.to]) {
                    pq.offer(nextEdge);
                }
            }
        }

        return totalCost;
    }

    /**
     * 좌표 평면에서 MST
     * 예: BOJ 4386 별자리 만들기
     */
    static double mstWithCoordinates(Point[] points) {
        int n = points.length;
        ArrayList<EdgeDouble> edges = new ArrayList<>();

        // 모든 점 쌍 사이의 거리 계산
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = distance(points[i], points[j]);
                edges.add(new EdgeDouble(i, j, dist));
            }
        }

        // Kruskal 알고리즘
        Collections.sort(edges);

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        double totalCost = 0;
        int edgeCount = 0;

        for (EdgeDouble edge : edges) {
            if (union(edge.from, edge.to)) {
                totalCost += edge.cost;
                edgeCount++;

                if (edgeCount == n - 1) {
                    break;
                }
            }
        }

        return totalCost;
    }

    static double distance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * 일부 간선이 이미 연결된 경우
     * 예: BOJ 1774 우주신과의 교감
     */
    static double mstWithPreconnected(Point[] points, int[][] connected) {
        int n = points.length;

        // Union-Find 초기화
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // 이미 연결된 간선 처리
        for (int[] conn : connected) {
            union(conn[0], conn[1]);
        }

        // 모든 간선 생성
        ArrayList<EdgeDouble> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double dist = distance(points[i], points[j]);
                edges.add(new EdgeDouble(i, j, dist));
            }
        }

        Collections.sort(edges);

        double totalCost = 0;

        for (EdgeDouble edge : edges) {
            if (union(edge.from, edge.to)) {
                totalCost += edge.cost;
            }
        }

        return totalCost;
    }

    // ==================== Union-Find ====================

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false;  // 이미 같은 집합
        }

        parent[rootY] = rootX;
        return true;
    }

    // ==================== 클래스 ====================

    static class Edge implements Comparable<Edge> {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static class EdgeDouble implements Comparable<EdgeDouble> {
        int from, to;
        double cost;

        EdgeDouble(int from, int to, double cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(EdgeDouble o) {
            return Double.compare(this.cost, o.cost);
        }
    }

    static class Point {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
