import java.util.HashMap;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for(int key: nums) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        
        return nums.length / 2 < map.keySet().size()? nums.length / 2 : map.keySet().size();
    }
}