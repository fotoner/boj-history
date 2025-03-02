import java.io.*;
import java.util.*;

class Main {
    static class Pos{
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n + 2][m + 2];
        int[][] visited = new int[n + 2][m + 2];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        st = new StringTokenizer(br.readLine());

        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());

        int targetX = Integer.parseInt(st.nextToken());
        int targetY = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            char[] row = br.readLine().toCharArray();

            for (int j = 0; j < m; j++) {
                map[i + 1][j + 1] = row[j];
            }
        }
        Deque<Pos> dq = new LinkedList<>();
        dq.add(new Pos(startX, startY));
        visited[startX][startY] = 1;

        while (!dq.isEmpty()) {
            Pos pos = dq.poll();

            if(targetX == pos.x  && targetY == pos.y) {
                System.out.println(visited[targetX][targetY]);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = pos.x + dx[i];
                int nextY = pos.y + dy[i];

                int nextVal = map[nextX][nextY];
                if(nextVal != 0 && visited[nextX][nextY] == 0){
                    Pos newPos = new Pos(nextX, nextY);
                    if(nextVal == '1') {
                        visited[nextX][nextY] = visited[pos.x][pos.y] + 1;
                        dq.addLast(newPos);
                    } else {
                        visited[nextX][nextY] = visited[pos.x][pos.y];
                        dq.addFirst(newPos);
                    }
                }
            }
        }
    }
}