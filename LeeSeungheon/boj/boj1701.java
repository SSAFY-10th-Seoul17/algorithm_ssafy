import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1701 {

    static String input;
    static int[] pi;
    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        System.out.println(solve(input));
    }

    private static int solve(String input) {
        int max = 0;

        len = input.length();
        pi = new int[len];

        for (int startIdx = 0; startIdx < len; startIdx++) {
            if (max > len - startIdx) {
                break;
            }
            max = Math.max(max, getPi(startIdx));
        }
        return max;
    }

    /**
     * KMP에서 PI를 구하는 과정에서 2번 나오는 문자열을 찾게 된다.
     */
    private static int getPi(int startIdx) {
        // input << 문자열
        int max = 0;

        for (int suffixIdx = startIdx + 1, prefixIdx = startIdx; suffixIdx < len; suffixIdx++) {

            while (prefixIdx > startIdx && input.charAt(suffixIdx) != input.charAt(prefixIdx)) {
                prefixIdx = pi[prefixIdx - startIdx] + startIdx;
            }
            if (input.charAt(suffixIdx) == input.charAt(prefixIdx)) {
                pi[suffixIdx] = ++prefixIdx - startIdx;
                max = Math.max(prefixIdx - startIdx, max);
            } else {
                pi[suffixIdx - startIdx] = 0;
            }
        }
        return max;
    }
}
