import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int num: arr) {
            if(answer.size() == 0 || num != answer.get(answer.size() - 1))
                answer.add(num);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}