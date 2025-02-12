import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void parse(String input) {
        String[] split = input.split(" ");

        char curChar = split[0].charAt(0);
        int idx = 0;
        for (int i = 0; i < split[1].length(); i++) {
            if(curChar == split[1].charAt(i)){
                idx++;
                if(idx == split[0].length()){
                    System.out.println("Yes");
                    return;
                }
                curChar = split[0].charAt(idx);
            }
        }
        System.out.println("No");
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (String input :br.lines().toArray(String[]::new)){
            parse(input);
        }
    }
}