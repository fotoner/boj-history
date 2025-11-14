/**
 * Dynamic Programming (동적 계획법) 템플릿
 *
 * 핵심 개념:
 * - 큰 문제를 작은 부분 문제로 나눔
 * - 부분 문제의 결과를 저장하여 재사용 (Memoization)
 * - Bottom-up 또는 Top-down 방식
 *
 * 시간복잡도: 문제에 따라 다름 (보통 O(N), O(N²), O(NM) 등)
 *
 * 참고 문제:
 * - BOJ 14501 퇴사 (스케줄링)
 * - BOJ 9084 동전 (조합)
 * - BOJ 15989 1, 2, 3 더하기 4 (분할)
 * - BOJ 7579 앱 (배낭 문제)
 */

import java.io.*;
import java.util.*;

public class DynamicProgramming {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 예제: 피보나치
        int n = Integer.parseInt(br.readLine());
        System.out.println("Fibonacci: " + fibonacci(n));

        // 예제: 0-1 배낭 문제
        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;
        System.out.println("Knapsack: " + knapsack01(weights, values, capacity));
    }

    // ==================== 기본 패턴 ====================

    /**
     * 1. 피보나치 (Bottom-up)
     * dp[i] = i번째 피보나치 수
     */
    static long fibonacci(int n) {
        if (n <= 1) return n;

        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    /**
     * 2. 피보나치 (Top-down with Memoization)
     */
    static long[] memo;

    static long fibonacciMemo(int n) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n];

        return memo[n] = fibonacciMemo(n-1) + fibonacciMemo(n-2);
    }

    // ==================== 1차원 DP ====================

    /**
     * 계단 오르기
     * dp[i] = i번째 계단까지 가는 방법의 수
     * 한 번에 1칸 또는 2칸 오를 수 있음
     */
    static long climbStairs(int n) {
        if (n <= 2) return n;

        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    /**
     * 최대 부분 합 (Kadane's Algorithm)
     * dp[i] = i번째까지의 최대 부분 합
     */
    static int maxSubarraySum(int[] arr) {
        int n = arr.length;
        int maxSum = arr[0];
        int currentSum = arr[0];

        for (int i = 1; i < n; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /**
     * 최장 증가 부분 수열 (LIS) - O(N²)
     * dp[i] = i번째 원소를 마지막으로 하는 LIS 길이
     */
    static int longestIncreasingSubsequence(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxLen = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    // ==================== 2차원 DP ====================

    /**
     * 0-1 배낭 문제 (Knapsack)
     * dp[i][w] = 처음 i개 물건으로 무게 w 이하로 얻을 수 있는 최대 가치
     */
    static int knapsack01(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                // i번째 물건을 넣지 않는 경우
                dp[i][w] = dp[i-1][w];

                // i번째 물건을 넣는 경우
                if (w >= weights[i-1]) {
                    dp[i][w] = Math.max(dp[i][w],
                                       dp[i-1][w - weights[i-1]] + values[i-1]);
                }
            }
        }

        return dp[n][capacity];
    }

    /**
     * 최장 공통 부분 수열 (LCS)
     * dp[i][j] = s1[0..i]와 s2[0..j]의 LCS 길이
     */
    static int longestCommonSubsequence(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[n][m];
    }

    /**
     * 편집 거리 (Edit Distance)
     * dp[i][j] = s1[0..i]를 s2[0..j]로 만드는 최소 연산 횟수
     */
    static int editDistance(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        int[][] dp = new int[n + 1][m + 1];

        // 초기화
        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1],    // 교체
                               Math.min(dp[i-1][j],      // 삭제
                                       dp[i][j-1]))      // 삽입
                               + 1;
                }
            }
        }

        return dp[n][m];
    }

    // ==================== 특수 패턴 ====================

    /**
     * 동전 교환 (무한 개수)
     * dp[i] = 금액 i를 만들 수 있는 최소 동전 개수
     */
    static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    /**
     * 동전 조합의 개수
     * dp[i] = 금액 i를 만드는 방법의 수
     */
    static int coinCombinations(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }

        return dp[amount];
    }

    /**
     * 구간 DP (Interval DP)
     * dp[i][j] = 구간 [i, j]의 최적값
     */
    static int intervalDP(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        // 길이 1인 구간
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        // 길이를 늘려가며 계산
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                // 구간을 나누는 모든 경우 시도
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j],
                                       dp[i][k] + dp[k+1][j] + arr[i] + arr[j]);
                }
            }
        }

        return dp[0][n-1];
    }

    /**
     * 비트마스크 DP
     * dp[mask] = 집합 상태 mask일 때의 최적값
     */
    static int bitmaskDP(int n) {
        int[] dp = new int[1 << n];  // 2^n 크기
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int mask = 0; mask < (1 << n); mask++) {
            if (dp[mask] == Integer.MAX_VALUE) continue;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) == 0) {  // i번째 비트가 0이면
                    int nextMask = mask | (1 << i);
                    dp[nextMask] = Math.min(dp[nextMask], dp[mask] + 1);
                }
            }
        }

        return dp[(1 << n) - 1];
    }

    /**
     * 역방향 DP (BOJ 14501 퇴사 유형)
     * 뒤에서부터 앞으로 계산
     */
    static int backwardDP(int n, int[] time, int[] profit) {
        int[] dp = new int[n + 2];  // dp[i] = i일부터 끝까지 얻을 수 있는 최대 이익

        for (int i = n; i >= 1; i--) {
            int next = i + time[i];

            if (next > n + 1) {
                // 상담이 기간을 넘어감
                dp[i] = dp[i + 1];
            } else {
                // 상담을 하는 경우 vs 하지 않는 경우
                dp[i] = Math.max(dp[i + 1], profit[i] + dp[next]);
            }
        }

        return dp[1];
    }
}
