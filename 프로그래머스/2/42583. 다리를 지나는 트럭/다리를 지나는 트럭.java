import java.util.*;

class Solution {
    public int solution(int bridgLength, int weight, int[] truckWeights) {
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> trucks = new LinkedList<>();
        
        int sumWeight = 0;
        int sec = 0; 
        int count = 0;
        
        for(int i = 0; i < bridgLength; i++){ 
            q.add(0);
        }
        for(int truck: truckWeights) {
            trucks.add(truck);
        }
        
        while(count != truckWeights.length) {
            int curTruck = q.poll();
            sec++;
            
            if(curTruck > 0){
                sumWeight -= curTruck;
                count++;
            }
            
            int nextTruck = 0;
            if(!trucks.isEmpty()) {
                nextTruck = trucks.peek();
            }
            
            if((sumWeight + nextTruck) <= weight) {
                q.add(nextTruck);
                trucks.poll();
                sumWeight += nextTruck;
            } else {
                q.add(0);
            }
            
        }
        
        return sec;
    }
}