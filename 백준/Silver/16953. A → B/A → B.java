import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        Queue<long[]> q = new LinkedList<>();

        q.add(new long[]{n, 1});

        while (!q.isEmpty()) {
            long[] cur = q.poll();
            if (cur[0] == target) {
                System.out.println(cur[1]);
                return;
            }

            if(cur[0] * 2 <= target){
                q.add(new long[]{cur[0] * 2, cur[1] + 1});
            }

            if(cur[0] * 10 + 1 <= target){
                q.add(new long[]{cur[0] * 10 + 1, cur[1] + 1});
            }
        }
        System.out.println(-1);
    }
}