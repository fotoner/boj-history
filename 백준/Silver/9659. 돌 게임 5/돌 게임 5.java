import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();

        System.out.println((n.charAt(n.length() - 1) - '0') % 2 == 1 ? "SK" : "CY");
    }
}