import java.io.*;
import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(i -> Integer.parseInt(i) - 1).toArray();
            boolean[] visited = new boolean[n];

            int count = 0;
            for (int i = 0; i < n; i++) {
                if(visited[i])
                    continue;

                HashSet<Integer> team = new HashSet<>();
                int curNode = i;

                while (true) {
                    team.add(curNode);
                    visited[curNode] = true;

                    int nextNode = arr[curNode];

                    if(visited[nextNode]){
                        if(team.contains(nextNode)){
                            count++;
                            for (int j = nextNode; j != curNode ; j = arr[j]) {
                                count++;
                            }
                        }
                        break;
                    }
                    curNode = nextNode;
                }
            }

            System.out.println(n - count);
        }
    }
}