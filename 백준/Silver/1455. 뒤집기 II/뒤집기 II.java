import java.io.*;
import java.util.*;

class Main {
    static int[][] arr;

    public static void flip(int x, int y){
        for (int j = 0; j <= y; j++) {
            for (int k = 0; k <= x; k++) {
                arr[j][k] = (arr[j][k] + 1) % 2;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n][];

        for (int i = 0; i < n; i++) {
            arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
        
        int count = 0;

        for (int y = n - 1; y >= 0; y--) {
            for (int x = m - 1; x >= 0; x--) {
                if(arr[y][x] != 0) {
                    flip(x, y);
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}