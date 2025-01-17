import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> map = new HashMap<>();
        
        for(String key: participant) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        for(String key: completion) {
            if(map.containsKey(key)){
                int count = map.get(key) - 1;
                
                if(count == 0) {
                    map.remove(key);
                } else {
                    map.put(key, count);
                }
            }
        }
        
        return (String)map.keySet().toArray()[0];
    }
}