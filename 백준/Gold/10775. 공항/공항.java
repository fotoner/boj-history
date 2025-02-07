import java.io.*;
import java.util.*;

public class Main {
    static int[] gate;

    static void union(int a, int b) {
        gate[find(b)] = find(a);
    }
    static int find(int a) {
        if(gate[a] == a) return a;
        return gate[a] = find(gate[a]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine());
        int p = Integer.parseInt(br.readLine());

        gate = new int[g + 1];
        for (int i = 0; i < g + 1; i++) {
            gate[i] = i;
        }
        int count = 0;
        for (int i = 0; i < p; i++) {
            int gi = Integer.parseInt(br.readLine());

            if(find(gi) == 0)
                break;
            union(find(gi) - 1, find(gi));
            count++;
        }

        System.out.println(count);
    }
}