import java.io.*;
import java.util.*;

class Main {
    static int[][] map;
    static boolean[][] visited;
    static int height;
    static int width;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new int[height + 2][width + 2];
        visited = new boolean[height + 2][width + 2];

        Arrays.fill(map[0], -1);
        Arrays.fill(map[height + 1], -1);

        for (int i = 1; i <= height; i++) {
            map[i][0] = -1;
            map[i][width + 1] = -1;

            char[] arr = br.readLine().toCharArray();

            for (int j = 1; j <= width; j++) {
                map[i][j] = arr[j - 1] == 'X' ? 1 : 0;
            }
        }
        boolean flag = false;
        Queue<int[]> q = new LinkedList<>();

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int i = 1; i <= height ; i++) {
            for (int j = 1; j <= width ; j++) {
                if(visited[i][j] || map[i][j] != 0)
                    continue;

                q.add(new int[]{i, j});

                while (!q.isEmpty()) {
                    int[] curPos = q.poll();
                    int x = curPos[1];
                    int y = curPos[0];

                    visited[y][x] = true;

                    int count = 0;
                    for (int k = 0; k < 4; k++) {
                        int nextX = x + dx[k];
                        int nextY = y + dy[k];

                        if(map[nextY][nextX] == 0){
                            count++;
                            if(!visited[nextY][nextX]) {
                                q.add(new int[]{nextY, nextX, k});
                            }
                        }
                    }
                    if(count < 2) {
                        flag = true;
                    }
                }
            }
        }

        System.out.println(flag? 1 : 0);
    }
}