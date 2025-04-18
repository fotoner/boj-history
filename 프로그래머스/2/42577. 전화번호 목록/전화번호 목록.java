import java.util.*;

class Solution {
    public boolean solution(String[] phoneBook) {
        Set<String> set = new HashSet<>();
        
        for(String number: phoneBook) {
            set.add(number);
        }
        
        for(int i = 0; i < phoneBook.length; i++){
            for(int j = 0; j < phoneBook[i].length(); j++){
                if(set.contains(phoneBook[i].substring(0,j))){
                    return false;
                }
            }
        }
        
        return true;
    }
}