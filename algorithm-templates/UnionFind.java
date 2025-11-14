/**
 * Union-Find (Disjoint Set Union) 템플릿
 *
 * 사용 사례:
 * - 크루스칼 알고리즘 (MST)
 * - 연결 요소 찾기
 * - 사이클 검사
 *
 * 시간복잡도: O(α(N)) ≈ O(1) (경로 압축 + 랭크 최적화)
 *
 * 참고 문제:
 * - BOJ 1647 도시 분할 계획 (MST)
 * - BOJ 10775 공항 (게이트 할당)
 * - BOJ 1774 우주신과의 교감 (MST)
 */

import java.io.*;
import java.util.*;

public class UnionFind {
    static int[] parent;  // 부모 노드
    static int[] rank;    // 트리의 높이 (선택적, 랭크 최적화용)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 초기화
        init(n);

        // 예제: 두 원소 합치기
        union(1, 2);
        union(3, 4);

        // 예제: 같은 집합인지 확인
        System.out.println(find(1) == find(2)); // true
        System.out.println(find(1) == find(3)); // false
    }

    /**
     * Union-Find 초기화
     * 각 노드가 자기 자신을 부모로 가지도록 설정
     */
    static void init(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    /**
     * x의 루트 노드 찾기 (경로 압축 최적화 적용)
     * 재귀적으로 루트를 찾으면서 경로상의 모든 노드가 직접 루트를 가리키도록 함
     */
    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        // 경로 압축: 루트를 찾으면서 parent[x]를 루트로 업데이트
        return parent[x] = find(parent[x]);
    }

    /**
     * x와 y가 속한 집합 합치기
     * @return 합쳐졌으면 true, 이미 같은 집합이면 false
     */
    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        // 이미 같은 집합
        if (rootX == rootY) {
            return false;
        }

        // Union by rank: 작은 트리를 큰 트리 밑에 붙임
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }

    /**
     * 간단한 버전 (랭크 최적화 없이)
     * 경로 압축만 사용해도 충분히 빠름
     */
    static int findSimple(int x) {
        if (parent[x] == x) return x;
        return parent[x] = findSimple(parent[x]);
    }

    static boolean unionSimple(int x, int y) {
        int rootX = findSimple(x);
        int rootY = findSimple(y);

        if (rootX == rootY) return false;

        parent[rootY] = rootX;  // 단순히 한쪽을 다른쪽에 붙임
        return true;
    }
}
