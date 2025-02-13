import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] checked = new boolean[m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++ ){
                if(str.charAt(j) == 'O'){
                    checked[j] = true;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            if(!checked[i]){
                System.out.println(i + 1);
                return;
            }
        }

        System.out.println("ESCAPE FAILED");
    }
}