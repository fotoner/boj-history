import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> plus = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minus = new PriorityQueue<>();

        int sum = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num > 0){
                plus.add(num);
            } else {
                minus.add(num);
            }
        }

        while(!minus.isEmpty()) {
            int curValue = minus.poll();
            if (minus.isEmpty()) {
               sum += curValue;
               break;
            }
            sum += curValue * minus.poll();
        }
        while (!plus.isEmpty()) {
            int curValue = plus.poll();
            if (plus.isEmpty()) {
                sum += curValue;
                break;
            }
            int nextValue = plus.poll();
            sum += Math.max(nextValue + curValue, nextValue * curValue);
        }

        System.out.println(sum);
    }
}