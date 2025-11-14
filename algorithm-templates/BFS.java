/**
 * BFS (Breadth-First Search, 너비 우선 탐색) 템플릿
 *
 * 사용 사례:
 * - 최단 거리 찾기 (가중치가 모두 1일 때)
 * - 레벨별 탐색
 * - 미로 찾기
 * - 연결 요소 찾기
 *
 * 시간복잡도: O(V + E)
 *
 * 참고 문제:
 * - BOJ 16236 아기 상어
 * - BOJ 16928 뱀과 사다리 게임
 * - BOJ 2252 줄 세우기 (위상정렬)
 */

import java.io.*;
import java.util.*;

public class BFS {

    // 4방향 이동 (상, 좌, 우, 하)
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    // 8방향 이동 (선택적)
    static int[] dy8 = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx8 = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 예제: (0,0)에서 (n-1, m-1)까지의 최단 거리
        int result = bfsGrid(map, 0, 0, n - 1, m - 1);
        System.out.println(result);
    }

    /**
     * 2D 그리드에서 BFS (최단 거리)
     * @param map 2D 배열 (0: 이동 가능, 1: 벽)
     * @param startY 시작 y좌표
     * @param startX 시작 x좌표
     * @param endY 도착 y좌표
     * @param endX 도착 x좌표
     * @return 최단 거리 (도달 불가능하면 -1)
     */
    static int bfsGrid(int[][] map, int startY, int startX, int endY, int endX) {
        int n = map.length;
        int m = map[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        // 시작점 추가 (y, x, 거리)
        queue.offer(new int[]{startY, startX, 0});
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int dist = cur[2];

            // 목적지 도착
            if (y == endY && x == endX) {
                return dist;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                // 범위 체크
                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;

                // 방문 체크 및 벽 체크
                if (visited[ny][nx] || map[ny][nx] == 1) continue;

                visited[ny][nx] = true;
                queue.offer(new int[]{ny, nx, dist + 1});
            }
        }

        return -1; // 도달 불가능
    }

    /**
     * 그래프에서 BFS (인접 리스트)
     * @param graph 인접 리스트
     * @param start 시작 노드
     * @return 각 노드까지의 최단 거리 배열
     */
    static int[] bfsGraph(ArrayList<ArrayList<Integer>> graph, int start) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                if (dist[next] == -1) {  // 미방문
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }

        return dist;
    }

    /**
     * 멀티 소스 BFS
     * 여러 시작점에서 동시에 탐색 시작
     * 예: 불 번지기, 토마토 등
     */
    static int[][] bfsMultiSource(int[][] map, ArrayList<int[]> sources) {
        int n = map.length;
        int m = map[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int[][] dist = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], -1);
        }

        // 모든 시작점 추가
        for (int[] source : sources) {
            queue.offer(new int[]{source[0], source[1], 0});
            dist[source[0]][source[1]] = 0;
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int d = cur[2];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
                if (dist[ny][nx] != -1 || map[ny][nx] == 1) continue;

                dist[ny][nx] = d + 1;
                queue.offer(new int[]{ny, nx, d + 1});
            }
        }

        return dist;
    }

    /**
     * 0-1 BFS (가중치가 0 또는 1인 경우)
     * Deque를 사용하여 가중치 0인 간선은 앞에, 1인 간선은 뒤에 추가
     */
    static int[] bfs01(ArrayList<ArrayList<Edge>> graph, int start) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(start);
        dist[start] = 0;

        while (!deque.isEmpty()) {
            int cur = deque.poll();

            for (Edge edge : graph.get(cur)) {
                int next = edge.to;
                int cost = edge.cost;
                int newDist = dist[cur] + cost;

                if (newDist < dist[next]) {
                    dist[next] = newDist;

                    if (cost == 0) {
                        deque.offerFirst(next);  // 가중치 0이면 앞에
                    } else {
                        deque.offerLast(next);   // 가중치 1이면 뒤에
                    }
                }
            }
        }

        return dist;
    }

    static class Edge {
        int to, cost;
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}
