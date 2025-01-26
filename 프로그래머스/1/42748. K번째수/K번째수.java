import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        ArrayList<Integer> res = new ArrayList<>();
        
        for(int[] command: commands) {
            command[0]--; command[2]--;    
            ArrayList<Integer> list = new ArrayList<>();
            
            for(int i = command[0]; i < command[1]; i++) {
                list.add(array[i]);
            }
            list.sort((a, b) -> a - b);
            
            res.add(list.get(command[2]));
        }
        
        return res.stream().mapToInt(i -> i).toArray();
    }
}