import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        ArrayList<Integer> list = new ArrayList<>();
        
        int[][] pattern = {
            {1, 2, 3, 4, 5},
            {2, 1, 2, 3, 2, 4, 2, 5},
            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };
        int[] count = {answers.length, answers.length, answers.length};

        
        for(int i = 0; i < answers.length; i++) {
            int curMax = 0;
            for(int j = 0; j < 3; j++) {
                if(pattern[j][i % pattern[j].length] != answers[i]){
                    count[j]--; 
                }
            }
        }

        int max = 0;
        for (int i = 0; i < 3; i++) {
            max = Math.max(count[i], max);
        }
        
        for (int i = 0; i < 3; i++) {
            if (count[i] == max) {
                list.add(i + 1);
            }
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
}