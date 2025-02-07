import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int sum = 0;
        for (int i = 0; i < h; i++) {
            String input = br.readLine();
            boolean isArea = false;
            for (int j = 0; j < input.length(); j++) {
                char curChar = input.charAt(j);

                if(curChar == '/' || curChar == '\\')
                    isArea = !isArea;

                if(isArea)
                    sum++;
            }
        }

        System.out.println(sum);
    }
}