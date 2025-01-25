import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> q = new LinkedList<>();
        int[] map = new int[101];
        int[] dist = new int[101];
        Arrays.fill(dist, -1);
        dist[0] = 0;
        dist[1] = 0;

        for (int i = 0; i < 101; i++) {
            map[i] = i;
        }

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < s[0] + s[1] ;i++) {
            int[] pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[pos[0]] = map[pos[1]];
        }

        q.add(1);

        while (!q.isEmpty()){
            int curPos = q.poll();
            int curCount = dist[curPos];

            for (int i = 1; i <= 6 && i + curPos <= 100; i++) {
                int next = map[curPos + i];

                if(dist[next] == -1) {
                    dist[next] = curCount + 1;
                    q.add(next);
                }
            }
        }

        System.out.println(dist[100]);
    }
}