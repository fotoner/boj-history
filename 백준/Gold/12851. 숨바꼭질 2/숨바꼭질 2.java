import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] visited = new int[100001];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, start});
        visited[start] = 1;

        int count = 0;
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            if(pos[1] == target) {
                count++;
                continue;
            }

            int nextSec = pos[0] + 1;
            int[] nextPos = {pos[1] + 1, pos[1] - 1, pos[1] * 2};

            for (int val: nextPos) {
                if (0 <= val && val <= 100000 &&(visited[val] == 0 || visited[val] == visited[pos[1]] + 1)){
                    visited[val] = nextSec;
                    q.add(new int[]{nextSec, val});
                }
            }
        }

        System.out.println(start == target? 0 : visited[target]);
        System.out.println(count);
    }
}