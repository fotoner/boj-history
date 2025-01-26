import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int count = 0;
        for(int value: scoville) {
            pq.add(value);
        }
        
        while(!pq.isEmpty()) {
            if(pq.peek() >= K) {
                break;
            }
            if(pq.size() == 1) {
                return -1;
            }
            
            int food1 = pq.poll();
            int food2 = pq.poll();
        
            count++;
            
            int newFood = food1 + food2 * 2;
            pq.add(newFood);
        }
        
        return count;
    }
}