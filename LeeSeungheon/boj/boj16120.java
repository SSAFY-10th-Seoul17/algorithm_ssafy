import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj16120 {

    static final String IS_PPAP = "PPAP";
    static final String NOT_PPAP = "NP";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();

        System.out.println(solve(N) ? IS_PPAP : NOT_PPAP);
    }

    private static boolean solve(String N) {
        int len = N.length();
        int countA = 0;
        int countP = 0;
        int totalA = len / 3;

        if (len % 3 != 1) {
            return false;
        }
        if (len == 1) {
            return N.equals("P");
        }

        for (int idx = 0; idx < len - 1; idx++) {

            if (N.charAt(idx) == 'A') {
                countA++;
                if (countA * 2 > countP || N.charAt(idx + 1) == 'A') {
                    return false;
                }
            } else {
                countP++;
            }
        }

        return totalA == countA;
    }

}