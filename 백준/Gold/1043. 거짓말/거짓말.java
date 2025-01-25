import java.io.*;
import java.util.*;

public class Main {
    static int[] person;

    // union 연산
    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return false;

        if(x <= y) person[y] = x;
        else person[x] = y;
        return true;
    }

    // find 연산
    public static int find(int x) {
        if(person[x] == x) return x;
        return person[x] = find(person[x]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> set = new HashSet<>();
        int count = 0;


        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = s[0];
        int m = s[1];
        person = new int[n];
        int[][] party = new int[m][];

        int[] truth = Arrays.stream(br.readLine().split(" ")).mapToInt(s1 -> Integer.parseInt(s1) - 1).toArray();
        for (int i = 1; i < truth.length; i++) {
            set.add(i - 1);
        }

        for (int i = 0; i < person.length; i++) {
            person[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int[] curParty = Arrays.stream(br.readLine().split(" ")).mapToInt(s1 -> Integer.parseInt(s1) - 1).toArray();
            party[i] = curParty;
            for (int j = 2; j < curParty.length; j++) {
                union(curParty[1], curParty[j]);
            }
        }

        for (int i = 0; i < n; i++) {
            find(i);
        }
        int[] res = new int[n];
        for (int i = 1; i < truth.length; i++){
            int key = truth[i];
            int parent = find(key);

            for (int j = 0; j < res.length; j++) {
                if(person[j] == parent){
                    res[j] = 1;
                }
            }
        }

        for (int i = 0; i < party.length; i++) {
            boolean flag = true;
            for (int j = 1; j < party[i].length; j++) {
                if(res[party[i][j]] == 1) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                count++;
            }
        }

        System.out.println(count);
    }
}