import java.io.*;
import java.math.BigInteger;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger n = new  BigInteger(br.readLine());

        System.out.println(n.mod(BigInteger.valueOf(2)).intValue() == 1 ? "SK" : "CY");
    }
}