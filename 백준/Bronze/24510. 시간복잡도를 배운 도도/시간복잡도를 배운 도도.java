import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int max = 0;
        Pattern pattern = Pattern.compile("for|while");

        for (int i = 0; i < n; i++) {
            Matcher matcher = pattern.matcher(br.readLine());
            int count = 0;

            while (matcher.find()){
                count++;
            }
            max = Math.max(count, max);
        }
        System.out.println(max);
    }
}