/**
 * Topological Sort (위상 정렬) 템플릿
 *
 * 사용 사례:
 * - 선수 과목 순서 정하기
 * - 작업 순서 스케줄링
 * - 빌드 시스템의 의존성 해결
 *
 * 시간복잡도: O(V + E)
 *
 * 참고 문제:
 * - BOJ 2252 줄 세우기
 * - BOJ 1766 문제집
 * - BOJ 1005 ACM Craft
 * - BOJ 2623 음악프로그램
 */

import java.io.*;
import java.util.*;

public class TopologicalSort {

    static ArrayList<ArrayList<Integer>> graph;
    static int[] indegree;  // 진입 차수

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
        indegree = new int[n + 1];

        // 간선 입력 (A -> B: A가 B보다 먼저)
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            indegree[b]++;
        }

        // 위상 정렬 실행
        ArrayList<Integer> result = topologicalSort(n);

        if (result.size() != n) {
            System.out.println("사이클 존재");
        } else {
            for (int node : result) {
                System.out.print(node + " ");
            }
        }
    }

    /**
     * Kahn's Algorithm을 이용한 위상 정렬 (BFS 방식)
     * @param n 정점의 개수
     * @return 위상 정렬 결과 (사이클이 있으면 크기가 n보다 작음)
     */
    static ArrayList<Integer> topologicalSort(int n) {
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        // 진입 차수가 0인 노드를 큐에 추가
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);

            // 인접 노드의 진입 차수 감소
            for (int next : graph.get(cur)) {
                indegree[next]--;

                // 진입 차수가 0이 되면 큐에 추가
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return result;
    }

    /**
     * 우선순위를 고려한 위상 정렬
     * 여러 가능한 순서 중 사전순으로 가장 빠른 것을 선택
     *
     * 예: BOJ 1766 문제집
     */
    static ArrayList<Integer> topologicalSortWithPriority(int n) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 일반 큐 대신 우선순위 큐
        ArrayList<Integer> result = new ArrayList<>();

        // 진입 차수가 0인 노드를 우선순위 큐에 추가
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty()) {
            int cur = pq.poll();
            result.add(cur);

            for (int next : graph.get(cur)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }

        return result;
    }

    /**
     * 위상 정렬 + DP
     * 각 노드까지 도달하는 데 필요한 최소/최대 비용을 계산
     *
     * 예: BOJ 1005 ACM Craft
     */
    static int[] topologicalSortWithDP(int n, int[] cost) {
        Queue<Integer> queue = new LinkedList<>();
        int[] dp = new int[n + 1];

        // 초기 비용 설정
        for (int i = 1; i <= n; i++) {
            dp[i] = cost[i];
        }

        // 진입 차수가 0인 노드를 큐에 추가
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph.get(cur)) {
                // DP 갱신 (현재까지의 비용 + 다음 노드 비용)
                dp[next] = Math.max(dp[next], dp[cur] + cost[next]);

                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return dp;
    }

    /**
     * DFS를 이용한 위상 정렬
     * 재귀를 사용하여 뒤에서부터 결과를 채움
     */
    static boolean[] visited;
    static Stack<Integer> stack;

    static ArrayList<Integer> topologicalSortDFS(int n) {
        visited = new boolean[n + 1];
        stack = new Stack<>();

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    static void dfs(int node) {
        visited[node] = true;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(next);
            }
        }

        stack.push(node);  // 모든 자식을 방문한 후 스택에 추가
    }

    /**
     * 사이클 감지
     * 위상 정렬 결과의 크기가 n보다 작으면 사이클 존재
     */
    static boolean hasCycle(int n) {
        Queue<Integer> queue = new LinkedList<>();
        int[] tempIndegree = indegree.clone();
        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (tempIndegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            count++;

            for (int next : graph.get(cur)) {
                tempIndegree[next]--;
                if (tempIndegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return count != n;  // count가 n이 아니면 사이클 존재
    }
}
