import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp = new int[101][10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb= new StringBuilder();

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
            indegree[b]++;

            map.get(a).add(b);
        }

        PriorityQueue<Integer> q = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            if(indegree[i] == 0)
                q.add(i);

            while(!q.isEmpty()) {
                int curNode = q.poll();
                sb.append(curNode + 1).append(" ");
                indegree[curNode]--;

                ArrayList<Integer> curEdges = map.get(curNode);

                for (int nextNode: curEdges) {
                    indegree[nextNode]--;

                    if(indegree[nextNode] == 0)
                        q.add(nextNode);
                }
            }
        }

        System.out.println(sb.toString().trim());
    }
}

