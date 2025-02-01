import java.io.*;
import java.util.*;

public class Main {
    static class Item{
        int src;
        int cost;
        Item(int src, int cost) {
            this.src = src;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        ArrayList<HashMap<Integer, Integer>> map = new ArrayList<>(n);
        ArrayList<HashMap<Integer, Integer>> reverseMap = new ArrayList<>(n);
        HashSet<String> visited = new HashSet<>();
        int[] indegree = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            map.add(i, new HashMap<>());
            reverseMap.add(i, new HashMap<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            indegree[b]++;

            map.get(a).put(b, c);
            reverseMap.get(b).put(a, c);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int target = Integer.parseInt(st.nextToken()) - 1;

        Queue<Item> q = new LinkedList<>();
        q.add(new Item(start, 0));


        while(!q.isEmpty()) {
            Item curNode = q.poll();
            indegree[curNode.src]--;

            HashMap<Integer, Integer> curEdges = map.get(curNode.src);

            for (int nextNode: curEdges.keySet()) {
                int cost = curEdges.get(nextNode);

                indegree[nextNode]--;
                dp[nextNode] = Math.max(dp[nextNode], cost + dp[curNode.src]);

                if(indegree[nextNode] == 0)
                    q.add(new Item(nextNode, curNode.cost + 1));
            }
        }

        int resTime = dp[target];
        int count = 0;
        q = new LinkedList<>();
        q.add(new Item(target, 0)); // src, cost

        while (!q.isEmpty()) {
            Item curNode = q.poll();

            HashMap<Integer, Integer> curEdges = reverseMap.get(curNode.src);

            for (int nextNode: curEdges.keySet()) {
                int cost = curEdges.get(nextNode) + curNode.cost;

                if(dp[nextNode] + cost != resTime)
                    continue;

                StringBuilder curKey = new StringBuilder();
                curKey.append(curNode.src).append(",").append(nextNode);
                if(!visited.contains(curKey.toString())) {
                    visited.add(curKey.toString());
                    count++;
                    q.add(new Item(nextNode, cost));
                }
            }
        }

        System.out.println(resTime);
        System.out.println(count);
    }
}