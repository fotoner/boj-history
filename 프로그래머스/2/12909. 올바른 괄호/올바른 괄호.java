import java.util.*;

class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0; i < s.length(); i++){
            char value = s.charAt(i);
            if(value == '(')
                stack.add(value);
            else if (!stack.isEmpty() && value == ')'){
                char peek = stack.peek();
                
                if(peek == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
            
        }
        
        return stack.isEmpty();
    }
}