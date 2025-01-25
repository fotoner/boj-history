import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[prices.length];
        
        for(int i = 0; i < prices.length; i++) {
            int curValue = prices[i];
            
            while(!stack.isEmpty() && curValue < prices[stack.peek()]){
                int idx = stack.pop();
                answer[idx] = i - idx;
            } 
            stack.add(i);
        }
        while (!stack.isEmpty()) { 
            answer[stack.peek()] = prices.length - stack.peek() - 1;
            stack.pop();
        }
        return answer;
    }
}