import java.io.*;
import java.util.*;

public class Main {
    static int[] tile;
    static char[][] map;
    static boolean[][] visited;

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a > b) {
            tile[a] = b;
        } else {
            tile[b] = a;
        }
    }

    static int find (int a) {
        if(tile[a] == a) return a;
        return tile[a] = find(tile[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        tile = new int[n * m];
        map = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n * m; i++) {
            tile[i] = i;  // union find 초기화
        }

        for (int i = 0; i < n; i++) {
            String value = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = value.charAt(j);
            }
        }
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j])
                    continue;

                q.add(new int[]{i, j});
                while (!q.isEmpty()) {
                    int[] pos = q.poll();
                    int y = pos[0];
                    int x = pos[1];
                    int nextY = y;
                    int nextX = x;

                    visited[y][x] = true;

                    char curDirection = map[y][x];

                    if(curDirection == 'U') nextY -= 1;
                    if(curDirection == 'D') nextY += 1;
                    if(curDirection == 'L') nextX -= 1;
                    if(curDirection == 'R') nextX += 1;

                    union(y * m + x, nextY * m + nextX);
                    if (!visited[nextY][nextX])
                        q.add(new int[]{nextY, nextX});
                }
            }
        }

        for (int i = 0; i < n * m; i++) {
            set.add(find(i));
        }

        System.out.println(set.size());
    }
}