import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int width = 0; int height = 0;
        
        for(int[] card: sizes) {
            int curWidth = card[0] > card[1]? card[0] : card[1];
            int curHeight = card[0] > card[1]? card[1] : card[0];
            
            width = Math.max(curWidth, width);
            height = Math.max(curHeight, height);
        }
        
        return width * height;
    }
}