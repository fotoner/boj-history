import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();

        String str = "";
        int size = 0;

        while((str = br.readLine()) != null){
            int count = map.getOrDefault(str, 0);
            map.put(str, count + 1);
            size++;
        }
        String[] treeArr = map.keySet().toArray(new String[0]);
        Arrays.sort(treeArr);

        StringBuilder sb = new StringBuilder();

        for(String tree : treeArr){
            sb.append(tree);
            sb.append(" ");
            sb.append(String.format("%.4f", (double)map.get(tree) / (double) size * 100));
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}