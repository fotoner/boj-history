import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int lastK = input.lastIndexOf('K');
        StringBuilder maxTail = new StringBuilder();
        for (int i = 0; i < input.length() - lastK - 1; i++) {
            maxTail.append("1");
        }
        StringBuilder minTail = new StringBuilder();
        for (int i = 0; i < input.length() - lastK - 1; i++) {
            if(i == 0)
                minTail.append('1');
            else
                minTail.append("0");
        }

        int count = 0;

        StringBuilder max = new StringBuilder();
        StringBuilder min = new StringBuilder();
        for (char curChar : input.substring(0, lastK + 1).toCharArray()) {
            if (curChar == 'M') {
                count++;
            } else {
                max.append('5');
                for (int i = 0; i < count; i++) {
                    max.append('0');
                }

                for (int i = 0; i < count; i++) {
                    if(i == 0)
                        min.append('1');
                    else
                        min.append('0');
                }
                min.append('5');

                count = 0;
            }
        }
        System.out.println(max.toString() + maxTail.toString());
        System.out.println(min.toString() + minTail.toString());
    }
}