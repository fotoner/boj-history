import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    static int y;
    static int x;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    public static int[] bfs(int size) {
        int n = graph.length;
        int[][] visited = new int[n][n];
        visited[y][x] = 1;
        ArrayList<int[]> res = new ArrayList<>();
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{y, x, 0});

        while (!q.isEmpty()) {
            int[] nextPos = q.poll();
            int curY = nextPos[0];
            int curX = nextPos[1];
            int sec = nextPos[2];

            int bite = graph[curY][curX];

            if (bite > size){
                continue;
            } else if(bite < size && bite != 0){
                res.add(new int[]{curY, curX, sec, bite});
            }

            for(int i = 0; i < 4; i++) {
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];

                if(nextY >= 0 && nextY <= n - 1 && nextX >= 0 && nextX <= n - 1 && visited[nextY][nextX] == 0) {
                    q.add(new int[]{nextY, nextX, sec + 1});
                    visited[nextY][nextX] = 1;
                }
            }
        }
        res.sort((a, b) -> {
            if(a[2] == b[2]){
                if(a[0] == b[0]){
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }

            return a[2] - b[2];
        });

        return !res.isEmpty()? res.get(0) : new int[]{-1, -1, -1, -1};
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int size = 2;
        int sec = 0;

        int n = Integer.parseInt(st.nextToken());

        graph = new int[n][];

        for (int i = 0; i < n; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[i] = arr;

            for(int j = 0; j < n; j++) {
                if(arr[j] == 9){
                    y = i;
                    x = j;
                    graph[y][x] = 0;
                }
            }
        }
        int biteCount = 0;

        while (true) {
            int[] res = bfs(size);

            if(res[0] == -1)
                break;

            y = res[0];
            x = res[1];
            graph[y][x] = 0;

            sec += res[2];

            biteCount++;
            if(biteCount == size) {
                biteCount = 0;
                size++;
            }
        }

        System.out.println(sec);
    }
}