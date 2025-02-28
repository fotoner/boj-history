import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int weight = 0;
        int value = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String type = st.nextToken();
            int w = Integer.parseInt(st.nextToken()) / 12;
            int h = Integer.parseInt(st.nextToken()) / 12;
            int l = Integer.parseInt(st.nextToken()) / 12;

            if(type.equals("A")) {
                int count = w * h * l;
                weight += 1000 + (500 * count);
                value += 4000 * count;
            } else {
                weight += 6000;
            }
        }

        System.out.println(weight);
        System.out.println(value);
    }
}