import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t > 0) {
            t--;
            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            int[] indegree = new int[m];
            int[] degree = new int[m];

            ArrayList<ArrayList<Integer>> map = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                map.add(new ArrayList<>());
            }

            for (int i = 0; i < p; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                map.get(a).add(b);
                indegree[b]++;
            }
            Queue<Integer> q = new LinkedList<>();

            for (int i = 0; i < m; i++) {
                if(indegree[i] != 0)
                    continue;
                degree[i] = 1;
                q.add(i);

                while (!q.isEmpty()) {
                    int curNode = q.poll();
                    indegree[curNode]--;

                    ArrayList<Integer> arr = map.get(curNode);

                    for (int nextNode: arr) {
                        indegree[nextNode]--;

                        if(degree[curNode] == degree[nextNode]){
                            degree[nextNode] += 1;
                        } else if (degree[curNode] > degree[nextNode]){
                            degree[nextNode] = degree[curNode];
                        }

                        if(indegree[nextNode] == 0) {
                            q.add(nextNode);
                        }
                    }
                }
            }

            System.out.println(k + " " + degree[m - 1]);
        }
    }
}