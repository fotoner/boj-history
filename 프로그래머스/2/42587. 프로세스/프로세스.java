import java.util.*;

class Solution {
    class Node {
        int value;
        int index;
        
        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
    public int solution(int[] priorities, int location) {
        Queue<Node> list = new LinkedList<>();
        ArrayList<Integer> sortedPriorities = new ArrayList<>();
        int answer = 0;
        
        for(int i = 0; i < priorities.length; i++) {
            sortedPriorities.add(priorities[i]);
            list.add(new Node(priorities[i], i));
        }
        
        sortedPriorities.sort((a, b) -> b - a);
        int prioritieCount = 0;
        
        while(!list.isEmpty()) {
            int curPrioritie = sortedPriorities.get(prioritieCount);         
            
            for(int i = 0; i < list.size(); i++){
                Node curNode = list.poll();
                
                if(curNode.value == curPrioritie){
                    if(curNode.index == location){
                        return answer + 1;
                    } 
                    answer++;
                    prioritieCount++;
                    curPrioritie = sortedPriorities.get(prioritieCount);
                    
                } else {
                    list.add(curNode); 
                }
            }
        }
        
        return answer;
    }
}