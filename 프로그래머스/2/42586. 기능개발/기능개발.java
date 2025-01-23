import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> qProgress = new LinkedList<>();
        Queue<Integer> qSpeeds = new LinkedList<>();
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        for(int i = 0; i < progresses.length; i++) {
            qProgress.add(progresses[i]);
            qSpeeds.add(speeds[i]);
        }
        
        int count = 0;
        int day = 1;
        
        while(!qProgress.isEmpty()) {
            int progress = qProgress.peek();
            int curSpeed = qSpeeds.peek();
            
            if(progress + (curSpeed * day) >= 100) {
                count++;
                qProgress.poll();
                qSpeeds.poll();
            } else if (count > 0) {
                answer.add(count);
                count = 0;
            } else {
                day++; 
            }
        }
        answer.add(count);
        return answer.stream().mapToInt(i -> i).toArray();
    }
}