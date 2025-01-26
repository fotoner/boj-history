import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] arr = new String[numbers.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.toString(numbers[i]);
        }

        Arrays.sort(arr, (a, b) ->{
            String a1 = a + b;
            String a2 = b + a;
            
            return a2.compareTo(a1);
        });

        StringBuilder sb = new StringBuilder();

        for(String var: arr) {
            sb.append(var);
        }

        return sb.toString().startsWith("0")? "0" : sb.toString();
    }
}