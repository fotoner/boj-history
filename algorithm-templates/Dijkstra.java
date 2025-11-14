/**
 * Dijkstra's Algorithm (다익스트라 최단 경로) 템플릿
 *
 * 사용 사례:
 * - 음수 가중치가 없는 그래프에서 최단 경로 찾기
 * - 한 정점에서 다른 모든 정점까지의 최단 거리
 *
 * 시간복잡도: O((V + E) log V) with PriorityQueue
 *
 * 참고 문제:
 * - BOJ 1916 최소비용 구하기
 * - BOJ 1504 특정한 최단 경로
 * - BOJ 14938 서강그라운드
 */

import java.io.*;
import java.util.*;

public class Dijkstra {

    static final int INF = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Edge>> graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 정점 개수
        int m = Integer.parseInt(st.nextToken()); // 간선 개수

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, cost));
            // 무방향 그래프인 경우:
            // graph.get(to).add(new Edge(from, cost));
        }

        int start = 1; // 시작 정점
        dijkstra(start, n);

        // 결과 출력
        for (int i = 1; i <= n; i++) {
            if (dist[i] == INF) {
                System.out.println("INF");
            } else {
                System.out.println(dist[i]);
            }
        }
    }

    /**
     * 다익스트라 알고리즘 (기본)
     * @param start 시작 정점
     * @param n 정점의 개수
     */
    static void dijkstra(int start, int n) {
        dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curNode = cur.to;
            int curDist = cur.cost;

            // 이미 처리된 노드 (더 짧은 경로가 있음)
            if (curDist > dist[curNode]) {
                continue;
            }

            // 인접한 노드 탐색
            for (Edge edge : graph.get(curNode)) {
                int nextNode = edge.to;
                int nextDist = curDist + edge.cost;

                // 더 짧은 경로 발견
                if (nextDist < dist[nextNode]) {
                    dist[nextNode] = nextDist;
                    pq.offer(new Edge(nextNode, nextDist));
                }
            }
        }
    }

    /**
     * 경로 역추적이 필요한 경우
     * @param start 시작 정점
     * @param end 도착 정점
     * @param n 정점의 개수
     * @return 최단 경로
     */
    static ArrayList<Integer> dijkstraWithPath(int start, int end, int n) {
        dist = new int[n + 1];
        int[] prev = new int[n + 1]; // 이전 노드 추적
        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);

        dist[start] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curNode = cur.to;
            int curDist = cur.cost;

            if (curDist > dist[curNode]) continue;

            for (Edge edge : graph.get(curNode)) {
                int nextNode = edge.to;
                int nextDist = curDist + edge.cost;

                if (nextDist < dist[nextNode]) {
                    dist[nextNode] = nextDist;
                    prev[nextNode] = curNode; // 경로 기록
                    pq.offer(new Edge(nextNode, nextDist));
                }
            }
        }

        // 경로 복원
        ArrayList<Integer> path = new ArrayList<>();
        if (dist[end] == INF) {
            return path; // 경로 없음
        }

        int cur = end;
        while (cur != -1) {
            path.add(cur);
            cur = prev[cur];
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * 특정 경유지를 거쳐가는 최단 경로
     * 예: start -> via1 -> via2 -> end
     */
    static int dijkstraVia(int start, int via1, int via2, int end, int n) {
        // start에서 모든 정점까지
        dijkstra(start, n);
        int[] distFromStart = dist.clone();

        // via1에서 모든 정점까지
        dijkstra(via1, n);
        int[] distFromVia1 = dist.clone();

        // via2에서 모든 정점까지
        dijkstra(via2, n);
        int[] distFromVia2 = dist.clone();

        // 두 가지 경로 비교:
        // 1. start -> via1 -> via2 -> end
        // 2. start -> via2 -> via1 -> end
        long path1 = (long)distFromStart[via1] + distFromVia1[via2] + distFromVia2[end];
        long path2 = (long)distFromStart[via2] + distFromVia2[via1] + distFromVia1[end];

        long result = Math.min(path1, path2);
        return result >= INF ? -1 : (int)result;
    }

    /**
     * 2D 그리드에서 다익스트라
     * 각 칸의 가중치가 다른 경우
     */
    static int dijkstraGrid(int[][] map) {
        int n = map.length;
        int m = map[0].length;

        int[][] dist = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], INF);
        }

        int[] dy = {-1, 0, 0, 1};
        int[] dx = {0, -1, 1, 0};

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, map[0][0]));
        dist[0][0] = map[0][0];

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.cost > dist[cur.y][cur.x]) continue;

            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];

                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;

                int nextCost = cur.cost + map[ny][nx];
                if (nextCost < dist[ny][nx]) {
                    dist[ny][nx] = nextCost;
                    pq.offer(new Node(ny, nx, nextCost));
                }
            }
        }

        return dist[n-1][m-1];
    }

    static class Edge implements Comparable<Edge> {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static class Node implements Comparable<Node> {
        int y, x, cost;

        Node(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
}
