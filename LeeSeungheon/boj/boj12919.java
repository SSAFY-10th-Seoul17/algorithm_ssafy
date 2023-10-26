import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj12919 {

    static String S;
    static String SR;
    static String T;
    static int result;
    static int SLen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        SR = new StringBuilder(S).reverse().toString();
        SLen = S.length();
        T = br.readLine();
        solve(0, T.length(), 0, 1);
        System.out.println(result > 0 ? 1 : 0);
    }

    private static void solve(int start, int end, int startIdx, int endIdx) {
        // dir == 0 앞 // dir == length 뒤
        if (result > 0 || SLen == end - start) {
            if (T.substring(start, end).equals(startIdx == 1 ? SR : S)) {
                result++;
                return;
            }
            return;
        }

        if (T.substring(start,end).charAt((end - start - 1) * endIdx) == 'A') {
            solve(start + startIdx, end - endIdx, startIdx, endIdx);
        }
        if (T.substring(start,end).charAt((end - start - 1) * startIdx) == 'B') {
            solve(start + endIdx, end - startIdx, endIdx, startIdx);
        }
    }


}
