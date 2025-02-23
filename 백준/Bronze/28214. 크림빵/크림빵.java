import java.io.*;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int count = 0;

        for (int i = 0; i < n; i++) {
            int empty = 0;
            boolean flag = true;
            for (int j = 0; j < k; j++) {
                int bread = Integer.parseInt(st.nextToken());

                empty += bread == 0? 1 : 0;

                if(p <= empty) {
                    flag = false;
                }
            }
            if(flag)
                count++;
        }

        System.out.println(count);
    }
}