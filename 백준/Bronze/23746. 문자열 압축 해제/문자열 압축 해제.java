import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashMap<Character, String> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String real = st.nextToken();
            char key = st.nextToken().charAt(0);

            map.put(key, real);
        }

        StringBuilder sb = new StringBuilder();
        for (char key :br.readLine().toCharArray()) {
            sb.append(map.get(key));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        System.out.println(sb.substring(s - 1, e));
    }
}