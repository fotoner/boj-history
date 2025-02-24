import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        char[] arr = new char[n];
        Arrays.fill(arr, '?');

        for (int i = 0; i < h; i++) {
            char[] inputs = br.readLine().toCharArray();

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < w; k++) {
                    char curChar = inputs[j * w + k];

                    if(curChar != '?') {
                        arr[j] = curChar;
                    }
                }
            }
        }
        System.out.println(arr);
    }
}