/**
 * DFS (Depth-First Search, 깊이 우선 탐색) 템플릿
 *
 * 사용 사례:
 * - 경로 탐색
 * - 사이클 검사
 * - 연결 요소 찾기
 * - 백트래킹
 *
 * 시간복잡도: O(V + E)
 *
 * 참고 문제:
 * - BOJ 9466 텀 프로젝트 (사이클)
 * - BOJ 16724 피리 부는 사나이
 */

import java.io.*;
import java.util.*;

public class DFS {

    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new boolean[n + 1];
        dfs(1);
    }

    /**
     * 기본 DFS (재귀)
     */
    static void dfs(int node) {
        visited[node] = true;
        System.out.println("방문: " + node);

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    /**
     * 스택을 이용한 DFS (반복문)
     */
    static void dfsIterative(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        visited[start] = true;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            System.out.println("방문: " + node);

            for (int next : graph.get(node)) {
                if (!visited[next]) {
                    visited[next] = true;
                    stack.push(next);
                }
            }
        }
    }

    /**
     * 모든 연결 요소 찾기
     */
    static int countComponents(int n) {
        visited = new boolean[n + 1];
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i);
                count++;
            }
        }

        return count;
    }

    /**
     * 사이클 검사 (무방향 그래프)
     */
    static boolean hasCycle(int node, int parent) {
        visited[node] = true;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                if (hasCycle(next, node)) {
                    return true;
                }
            } else if (next != parent) {
                // 이미 방문했고, 부모가 아니면 사이클
                return true;
            }
        }

        return false;
    }

    /**
     * 사이클 검사 (방향 그래프)
     */
    static int WHITE = 0, GRAY = 1, BLACK = 2;
    static int[] color;

    static boolean hasCycleDirected(int node) {
        color[node] = GRAY;  // 방문 중

        for (int next : graph.get(node)) {
            if (color[next] == GRAY) {
                // 방문 중인 노드를 다시 만남 = 사이클
                return true;
            }
            if (color[next] == WHITE && hasCycleDirected(next)) {
                return true;
            }
        }

        color[node] = BLACK;  // 방문 완료
        return false;
    }

    /**
     * 경로 찾기 (시작점에서 끝점까지)
     */
    static boolean findPath(int node, int target, ArrayList<Integer> path) {
        visited[node] = true;
        path.add(node);

        if (node == target) {
            return true;
        }

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                if (findPath(next, target, path)) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);  // 백트래킹
        return false;
    }

    /**
     * 2D 그리드에서 DFS
     */
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static boolean[][] visitedGrid;

    static void dfsGrid(int[][] map, int y, int x) {
        int n = map.length;
        int m = map[0].length;

        visitedGrid[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= n || nx < 0 || nx >= m) continue;
            if (visitedGrid[ny][nx] || map[ny][nx] == 1) continue;

            dfsGrid(map, ny, nx);
        }
    }

    /**
     * 함수형 그래프에서 사이클 찾기
     * 각 노드가 정확히 하나의 다음 노드를 가리킴
     * 예: BOJ 9466 텀 프로젝트
     */
    static boolean[] finished;
    static int[] next;
    static int cycleCount = 0;

    static void findCycleFunctional(int node) {
        visited[node] = true;
        int nextNode = next[node];

        if (!visited[nextNode]) {
            findCycleFunctional(nextNode);
        } else if (!finished[nextNode]) {
            // 사이클 발견: nextNode부터 node까지
            int cur = nextNode;
            while (cur != node) {
                cycleCount++;
                cur = next[cur];
            }
            cycleCount++;
        }

        finished[node] = true;
    }

    /**
     * 백트래킹 예제: N-Queen
     */
    static int[] queens;  // queens[i] = i번째 행의 퀸의 열 위치
    static int solutionCount = 0;

    static void nQueen(int row, int n) {
        if (row == n) {
            solutionCount++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isValidQueen(row, col, n)) {
                queens[row] = col;
                nQueen(row + 1, n);
                queens[row] = -1;  // 백트래킹
            }
        }
    }

    static boolean isValidQueen(int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            // 같은 열
            if (queens[i] == col) return false;
            // 대각선
            if (Math.abs(row - i) == Math.abs(col - queens[i])) return false;
        }
        return true;
    }

    /**
     * 순열 생성 (백트래킹)
     */
    static void permutation(int[] arr, boolean[] used, ArrayList<Integer> current, int depth) {
        if (depth == arr.length) {
            System.out.println(current);
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(arr[i]);
                permutation(arr, used, current, depth + 1);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    /**
     * 조합 생성 (백트래킹)
     */
    static void combination(int[] arr, ArrayList<Integer> current, int start, int r) {
        if (current.size() == r) {
            System.out.println(current);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            current.add(arr[i]);
            combination(arr, current, i + 1, r);
            current.remove(current.size() - 1);
        }
    }
}
