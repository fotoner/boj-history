import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 1; j < arr.length - 1 ; j++) {
                graph.get(arr[j] - 1).add(arr[j + 1] - 1);
                indegree[arr[j + 1] - 1]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            if(indegree[i] == 0)
                q.add(i);
        }
        ArrayList<Integer> res = new ArrayList<>();

        while (!q.isEmpty()) {
            int curNode = q.poll();
            res.add(curNode + 1);
            ArrayList<Integer> edges = graph.get(curNode);

            for (int nextNode: edges) {
                indegree[nextNode]--;
                if(indegree[nextNode] == 0)
                    q.add(nextNode);
            }
        }
        StringBuilder sb = new StringBuilder();
        if(res.size() != n){
            sb.append(0);
        } else {
            for (int val: res ){
                sb.append(val).append("\n");
            }
        }

        System.out.println(sb.toString().trim());
    }
}