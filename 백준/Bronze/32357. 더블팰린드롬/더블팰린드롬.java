import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            if(str.length() % 2 == 1) {
                continue;
            }

            String head = str.substring(0, str.length() / 2);
            String tail = str.substring(str.length() / 2);

            StringBuilder sb = new StringBuilder(tail);

            if(head.equals(sb.reverse().toString()))
                count++;
        }

        System.out.println(count * (count - 1));
    }
}