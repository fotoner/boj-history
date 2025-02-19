import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] visited = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());

            if(visited[value]) {
                System.out.println(value);
                break;
            }
            visited[value] = true;
        }
    }
}