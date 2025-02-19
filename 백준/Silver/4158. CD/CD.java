import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            HashSet<Integer> sang = new HashSet<>();
            HashSet<Integer> sun = new HashSet<>();

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) {
                break;
            }

            for (int i = 0; i < n; i++) {
                sang.add(Integer.parseInt(br.readLine()));
            }

            for (int i = 0; i < m; i++) {
                sun.add(Integer.parseInt(br.readLine()));
            }
            sang.retainAll(sun);
            System.out.println(sang.size());
        }
    }
}