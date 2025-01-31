import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> map = new ArrayList<>(n);
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            map.add(i, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            map.get(a).add(b);
            indegree[b]++;
        }

        Queue<Integer> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if(indegree[i] == 0)
                q.add(i);

            while (!q.isEmpty()) {
                int curNode = q.poll();
                indegree[curNode]--;

                sb.append(curNode + 1).append(" ");
                ArrayList<Integer> curEdges = map.get(curNode);

                for (int edge: curEdges) {
                    indegree[edge]--;
                    if(indegree[edge] == 0) {
                        q.add(edge);
                    }
                }
            }
        }

        System.out.println(sb);
    }
}