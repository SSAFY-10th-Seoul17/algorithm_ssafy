import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * [boj] 1958. LCS 3
 */
public class boj1958 { // LCS (Longest Common Subsequence)
    static String firstString, secondString, thirdString;
    /**
     * lcsLength: 세 문자열의 LCS의 길이
     */
    static int lcsLength;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        firstString = br.readLine();
        secondString = br.readLine();
        thirdString = br.readLine();

        getLCS();

        System.out.println(lcsLength);
    }

    /**
     * LCS 알고리즘을 활용
     */
    public static void getLCS() {
        int firstLength = firstString.length();
        int secondLength = secondString.length();
        int thirdLength = thirdString.length();

        dp = new int[firstLength + 1][secondLength + 1][thirdLength + 1];

        for(int i = 1; i <= firstLength; i++) {
            char firstChar = firstString.charAt(i - 1); // charAt에 -1을 해주는 이유는 dp 배열의 경우 0 인덱스는 마진으로 생각, 문자열에서는 0번 인덱스 위치 사용.
            for(int j = 1; j <= secondLength; j++) {
                char secondChar = secondString.charAt(j - 1);
                for(int k = 1; k <= thirdLength; k++) {
                    char thirdChar = thirdString.charAt(k - 1);

                    if(firstChar == secondChar && firstChar == thirdChar) { // 세 문자가 모두 같으면
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(Math.max(dp[i - 1][j][k], dp[i][j - 1][k]), dp[i][j][k - 1]);
                    }
                }
            }
        }

        lcsLength = dp[firstLength][secondLength][thirdLength];
    }
}
