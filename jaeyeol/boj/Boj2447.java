import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Boj2447 {
    static final String STAR = "*";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        System.out.print(printStar(n));
    }

    private static String printStar(int n) {
        if (n == 1) {
            return STAR;
        }

        StringBuilder sb = new StringBuilder(n * n + n);
        String[] parts = printStar(n / 3).split("\n");

        for (String part : parts) {
            sb.append(part.repeat(3)).append("\n");
        }
        for (String part : parts) {
            sb.append(part).append(" ".repeat(n / 3)).append(part).append("\n");
        }
        for (String part : parts) {
            sb.append(part.repeat(3)).append("\n");
        }

        return sb.toString();
    }

}

