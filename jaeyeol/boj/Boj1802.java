import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1802 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        for (int testCase = 0; testCase < T; testCase++) {
            String paper = br.readLine();
            result.append(isFoldable(paper, paper.length() >> 1, paper.length() >> 1)
                            ? "YES" : "NO")
                    .append("\n");
        }

        System.out.println(result);
    }

    private static boolean isFoldable(String paper, int center, int size) {
        if (size == 0) {
            return true;
        }

        for (int i = 1; i <= size; i++) {
            if (paper.charAt(center - i) == paper.charAt(center + i)) {
                return false;
            }
        }

        int different = (size + 1) >> 1;
        size >>= 1;

        return isFoldable(paper, center - different, size) &&
                isFoldable(paper, center + different, size);
    }

}
