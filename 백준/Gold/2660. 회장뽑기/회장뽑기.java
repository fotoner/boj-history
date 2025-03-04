import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dist = new int[n][];

        for (int i = 0; i < n; i++) {
            int[] arr = new int[n];
            Arrays.fill(arr, 987654321);
            arr[i] = 0;
            dist[i] = arr;
        }

        while (true) {
            int[] pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = pos[0] - 1;
            int b = pos[1] - 1;

            if(pos[0] == -1 && pos[1] == - 1){
                break;
            }

            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int score = 987654321;
        int count = 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int max = Arrays.stream(dist[i]).max().getAsInt();
            if(max < score) {
                sb = new StringBuilder();
                score = max;
                count = 1;
                sb.append(i + 1);
            } else if (max == score) {
                count++;
                sb.append(' ').append(i + 1);
            }
        }
        System.out.printf("%d %d\n", score, count);
        System.out.println(sb);
    }
}