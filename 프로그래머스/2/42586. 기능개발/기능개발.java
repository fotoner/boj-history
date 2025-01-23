import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Pair<Integer, Integer>> q = new Queue<>();
        ArrayList<Integer> answer = new ArrayList<>();
        int day = 0;
        
        for(int i = 0; i progresses.length; i++) {
            q.push(Pair.of(progresses[i], speeds[i]));
        }
        
        while(!q.isEmpty()) {
            
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}