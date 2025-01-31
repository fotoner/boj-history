import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1000000007;

    static long[][] map = {
        {0, 1, 1, 0, 0, 0, 0, 0},
        {1, 0, 1, 1, 0, 0, 0, 0},
        {1, 1, 0, 1, 1, 0, 0, 0},
        {0, 1, 1, 0, 1, 1, 0, 0},
        {0, 0, 1, 1, 0, 1, 0, 1},
        {0, 0, 0, 1, 1, 0, 1, 0},
        {0, 0, 0, 0, 0, 1, 0, 1},
        {0, 0, 0, 0, 1, 0, 1, 0}
    };

    static long[][] multiply(long[][] a, long[][] b){
        long[][] temp = new long[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    temp[i][j] += a[i][k] * b[k][j];
                    temp[i][j] %= MOD;
                }
            }
        }
        return temp;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int d = Integer.parseInt(st.nextToken());

        long[][] ans = new long[8][8];

        for (int i = 0; i < 8; i++) {
            ans[i][i] = 1;
        }

        long[][] target = map;

        while (d > 0) {
            if(d % 2 == 1) {
                ans = multiply(ans, target);
                d--;
            }
            target = multiply(target, target);
            d /= 2;
        }

        System.out.println(ans[0][0]);
    }
}