/**
 * Binary Search (이분 탐색) 템플릿
 *
 * 사용 사례:
 * - 정렬된 배열에서 특정 값 찾기
 * - 최적값 찾기 (Parametric Search)
 * - Upper Bound / Lower Bound
 *
 * 시간복잡도: O(log N)
 *
 * 참고 문제:
 * - BOJ 14786 Ax+Bsin(x)=C ② (파라메트릭 서치)
 * - BOJ 1654 랜선 자르기
 * - BOJ 2805 나무 자르기
 */

import java.io.*;
import java.util.*;

public class BinarySearch {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);  // 이분 탐색은 정렬된 배열 필요

        int target = Integer.parseInt(br.readLine());
        int index = binarySearch(arr, target);

        if (index != -1) {
            System.out.println("찾음: 인덱스 " + index);
        } else {
            System.out.println("없음");
        }
    }

    /**
     * 기본 이분 탐색 (값 찾기)
     * @return 찾으면 인덱스, 없으면 -1
     */
    static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;  // 오버플로우 방지

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    /**
     * Lower Bound
     * target 이상인 첫 번째 원소의 인덱스
     * 모든 원소가 target보다 작으면 arr.length 반환
     */
    static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * Upper Bound
     * target 초과인 첫 번째 원소의 인덱스
     * 모든 원소가 target 이하면 arr.length 반환
     */
    static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    /**
     * 특정 값의 개수 세기 (정렬된 배열)
     */
    static int countOccurrences(int[] arr, int target) {
        int lower = lowerBound(arr, target);
        int upper = upperBound(arr, target);
        return upper - lower;
    }

    /**
     * Parametric Search (최댓값의 최솟값)
     * 예: 랜선 자르기, 나무 자르기
     *
     * 문제: n개의 랜선을 k개 이상으로 만들 수 있는 최대 길이
     */
    static long maxLengthCut(int[] lines, int k) {
        long left = 1;
        long right = 0;

        for (int line : lines) {
            right = Math.max(right, line);
        }

        long result = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canCut(lines, mid, k)) {
                result = mid;
                left = mid + 1;  // 더 큰 값 시도
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    static boolean canCut(int[] lines, long length, int k) {
        long count = 0;
        for (int line : lines) {
            count += line / length;
        }
        return count >= k;
    }

    /**
     * Parametric Search (최솟값의 최댓값)
     * 예: 공유기 설치
     *
     * 문제: n개의 집에 c개의 공유기를 설치하되, 최소 거리를 최대화
     */
    static int maxMinDistance(int[] houses, int c) {
        Arrays.sort(houses);

        int left = 1;
        int right = houses[houses.length - 1] - houses[0];
        int result = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canInstall(houses, mid, c)) {
                result = mid;
                left = mid + 1;  // 더 큰 거리 시도
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    static boolean canInstall(int[] houses, int minDist, int c) {
        int count = 1;
        int lastPos = houses[0];

        for (int i = 1; i < houses.length; i++) {
            if (houses[i] - lastPos >= minDist) {
                count++;
                lastPos = houses[i];
            }
        }

        return count >= c;
    }

    /**
     * 실수 이분 탐색
     * 예: BOJ 14786 Ax+Bsin(x)=C
     */
    static double binarySearchDouble(double a, double b, double c) {
        double left = 0.0;
        double right = 1e9;
        double epsilon = 1e-9;  // 오차 범위

        while (right - left > epsilon) {
            double mid = (left + right) / 2.0;
            double value = a * mid + b * Math.sin(mid);

            if (value < c) {
                left = mid;
            } else {
                right = mid;
            }
        }

        return (left + right) / 2.0;
    }

    /**
     * 회전된 정렬 배열에서 탐색
     * 예: [4, 5, 6, 7, 0, 1, 2]에서 0 찾기
     */
    static int searchRotated(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            }

            // 왼쪽이 정렬되어 있는 경우
            if (arr[left] <= arr[mid]) {
                if (arr[left] <= target && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 오른쪽이 정렬되어 있는 경우
            else {
                if (arr[mid] < target && target <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    /**
     * 2D 배열에서 이분 탐색
     * 각 행과 열이 정렬되어 있음
     */
    static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int n = matrix.length;
        int m = matrix[0].length;

        int left = 0;
        int right = n * m - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int row = mid / m;
            int col = mid % m;
            int value = matrix[row][col];

            if (value == target) {
                return true;
            } else if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    /**
     * 최소값 찾기 (단봉 함수에서)
     * 삼분 탐색 (Ternary Search)
     */
    static double ternarySearch(double left, double right) {
        double epsilon = 1e-9;

        while (right - left > epsilon) {
            double m1 = left + (right - left) / 3;
            double m2 = right - (right - left) / 3;

            if (function(m1) > function(m2)) {
                left = m1;
            } else {
                right = m2;
            }
        }

        return (left + right) / 2.0;
    }

    static double function(double x) {
        // 예: f(x) = x^2 - 4x + 5
        return x * x - 4 * x + 5;
    }
}
