import java.util.*;

class Solution {
    public static class Song {
        int idx;
        int play;
        
        public Song(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>();
        ArrayList<Integer> answer = new ArrayList<>();

        for(int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }
        
        ArrayList<String> genresRank = new ArrayList<>(map.keySet());
        genresRank.sort((a1, a2) -> map.get(a2) - map.get(a1));
        
        for(String key :genresRank) {
            ArrayList<Song> songs = new ArrayList<>();
            
            for(int i = 0; i < genres.length; i++) {
                if(key.equals(genres[i])){
                    songs.add(new Song(i, plays[i]));
                }
            }
            songs.sort((a1, a2) -> a1.play == a2.play? a1.idx - a2.idx: a2.play - a1.play);
            
            answer.add(songs.get(0).idx);
            if(songs.size() != 1) {
                answer.add(songs.get(1).idx);
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}