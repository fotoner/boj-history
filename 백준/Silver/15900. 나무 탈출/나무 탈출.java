import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        ArrayList<HashSet<Integer>> map = new ArrayList<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            map.add(new HashSet<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            map.get(a).add(b);
            map.get(b).add(a);
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        int sum = 0;

        while (!q.isEmpty()){
            int[] arr = q.poll();
            int curNode = arr[0];
            int length = arr[1];
            visited[curNode] = true;
            HashSet<Integer> nextNodes = map.get(curNode);

            int count = 0;
            for (int nextNode : nextNodes) {
                if(visited[nextNode])
                    continue;

                q.add(new int[]{nextNode, length + 1});
                count++;
            }
            if(count == 0) {
                sum += length;
            }
        }

        System.out.println(sum % 2 == 0? "No" : "Yes");
    }
}