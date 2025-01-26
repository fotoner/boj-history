import java.util.*;

class Solution {

    private static int compare(int[] a, int[] b) {
        return a[1] - b[1];
    }

    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Solution::compare);
        Queue<int[]> q = new LinkedList<>();

        for (int[] job: jobs) {
            q.add(job);
        }
        int sec = 0;
        int sum = 0;
        while (!q.isEmpty() || !pq.isEmpty()){
            while (!q.isEmpty()) {
                int[] job = q.peek();

                if (job[0] <= sec) {
                    pq.add(job);
                    q.poll();
                } else {
                    break;
                }
            }

            if(!pq.isEmpty()){
                int[] job = pq.poll();

                sum += sec - job[0] + job[1];
                sec += job[1];
            } else {
                sec++;
            }
        }

        return sum / jobs.length;
    }
}