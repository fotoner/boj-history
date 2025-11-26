import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[5][];

        Set<String> set = new HashSet<>();
        Stack<int[][]> stack = new Stack<>();


        for(int i = 0; i < map.length; i++){
            int[] values = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map[i] = values;
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++){
                int[] pos = {i, j, 1};
                int[] value = {-1, -1, -1, -1, -1, -1};

                stack.push(new int[][]{pos, value});

                while (!stack.isEmpty()){
                    int[][] curItem = stack.pop();
                    int[] curPos = curItem[0];
                    int[] curValue = curItem[1].clone();
                    int x = curPos[0];
                    int y = curPos[1];

                    curValue[curPos[2] - 1] = map[x][y];

                    if(curPos[2] == 6) {
                        set.add(Arrays.toString(curValue));
                    } else {
                        for(int k = 0; k < 4; k++) {
                            int nextX = x + dx[k];
                            int nextY = y + dy[k];
                            if(nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map[i].length) {
                                continue;
                            }
                            int[] newPos = {nextX, nextY, curPos[2] + 1};
                            stack.push(new int[][]{newPos, curValue});
                        }
                    }
                }
            }
        }

        System.out.println(set.size());
    }
}