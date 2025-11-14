/**
 * Two Pointer (투 포인터) 템플릿
 *
 * 사용 사례:
 * - 구간 합 문제
 * - 특정 조건을 만족하는 부분 배열 찾기
 * - 두 배열의 합 문제
 *
 * 시간복잡도: O(N)
 *
 * 참고 문제:
 * - BOJ 1806 부분합 (최소 길이)
 * - BOJ 30804 과일 탕후루 (최대 길이)
 * - BOJ 2003 수들의 합 2
 */

import java.io.*;
import java.util.*;

public class TwoPointer {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 예제: 합이 target 이상인 최소 길이 부분 배열
        int result = minLengthSubarray(arr, target);
        System.out.println(result);
    }

    /**
     * 합이 target 이상인 최소 길이 부분 배열
     * BOJ 1806 부분합
     */
    static int minLengthSubarray(int[] arr, int target) {
        int n = arr.length;
        int left = 0, right = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        while (right < n) {
            // right 포인터 이동하며 합 증가
            sum += arr[right];
            right++;

            // 조건을 만족하면 left 포인터 이동
            while (sum >= target) {
                minLen = Math.min(minLen, right - left);
                sum -= arr[left];
                left++;
            }
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

    /**
     * 합이 정확히 target인 부분 배열 개수
     * BOJ 2003 수들의 합 2
     */
    static int countSubarraysWithSum(int[] arr, int target) {
        int n = arr.length;
        int left = 0, right = 0;
        int sum = 0;
        int count = 0;

        while (right < n) {
            sum += arr[right];
            right++;

            while (sum > target && left < right) {
                sum -= arr[left];
                left++;
            }

            if (sum == target) {
                count++;
            }
        }

        return count;
    }

    /**
     * 서로 다른 원소가 최대 K개인 최대 길이 부분 배열
     * BOJ 30804 과일 탕후루
     */
    static int maxLengthWithKDistinct(int[] arr, int k) {
        int n = arr.length;
        int left = 0;
        int maxLen = 0;
        HashMap<Integer, Integer> count = new HashMap<>();

        for (int right = 0; right < n; right++) {
            // right 원소 추가
            count.put(arr[right], count.getOrDefault(arr[right], 0) + 1);

            // 서로 다른 원소가 k개 초과하면 left 이동
            while (count.size() > k) {
                count.put(arr[left], count.get(arr[left]) - 1);
                if (count.get(arr[left]) == 0) {
                    count.remove(arr[left]);
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }

    /**
     * 정렬된 두 배열에서 합이 target인 쌍 찾기
     */
    static ArrayList<int[]> twoSumSorted(int[] arr1, int[] arr2, int target) {
        ArrayList<int[]> result = new ArrayList<>();
        int left = 0;
        int right = arr2.length - 1;

        while (left < arr1.length && right >= 0) {
            int sum = arr1[left] + arr2[right];

            if (sum == target) {
                result.add(new int[]{arr1[left], arr2[right]});
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return result;
    }

    /**
     * 정렬된 배열에서 두 수의 합이 target인 쌍 찾기
     */
    static boolean hasTwoSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    /**
     * 세 수의 합이 target에 가장 가까운 값 찾기
     */
    static int threeSumClosest(int[] arr, int target) {
        Arrays.sort(arr);
        int n = arr.length;
        int closest = arr[0] + arr[1] + arr[2];

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }

                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    return target;
                }
            }
        }

        return closest;
    }

    /**
     * 연속된 부분 배열의 곱이 K 미만인 개수
     */
    static int countSubarraysProductLessThanK(int[] arr, int k) {
        if (k <= 1) return 0;

        int n = arr.length;
        int left = 0;
        int product = 1;
        int count = 0;

        for (int right = 0; right < n; right++) {
            product *= arr[right];

            while (product >= k) {
                product /= arr[left];
                left++;
            }

            // [left, right] 구간에서 right를 포함하는 부분 배열 개수
            count += (right - left + 1);
        }

        return count;
    }

    /**
     * 슬라이딩 윈도우 (고정 크기)
     * 크기가 k인 윈도우의 최대 합
     */
    static int maxSumSubarrayOfSizeK(int[] arr, int k) {
        int n = arr.length;
        if (n < k) return -1;

        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }

        int maxSum = windowSum;

        for (int i = k; i < n; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    /**
     * 최소 구간 길이 (모든 문자를 포함)
     */
    static int minWindowContainingAllChars(String s, String t) {
        HashMap<Character, Integer> required = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (char c : t.toCharArray()) {
            required.put(c, required.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int formed = 0;  // 조건을 만족하는 문자 종류 개수
        int minLen = Integer.MAX_VALUE;

        while (right < s.length()) {
            char c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);

            if (required.containsKey(c) &&
                window.get(c).intValue() == required.get(c).intValue()) {
                formed++;
            }

            while (formed == required.size()) {
                minLen = Math.min(minLen, right - left + 1);

                char leftChar = s.charAt(left);
                window.put(leftChar, window.get(leftChar) - 1);

                if (required.containsKey(leftChar) &&
                    window.get(leftChar) < required.get(leftChar)) {
                    formed--;
                }

                left++;
            }

            right++;
        }

        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}
