import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();        
        int answer = 1;
        
        for(String[] clothe: clothes) {
            map.put(clothe[1], map.getOrDefault(clothe[1], 1) + 1);
        }
        
        
        for(String key: map.keySet()){
            answer *= map.get(key);
        }
        
        return answer - 1;
    }
}