import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 1701. Cubeditor
 */
public class boj1701 {
    /**
     * text: 전체 문자열
     * subText: 패턴 문자열
     */
    static String text, subText;
    /**
     * maxLength: 두 번 이상 나오는 부분 문자열의 최장 길이
     */
    static int maxLength;
    /**
     * pi: 부분 일치 테이블
     */
    static int[] pi;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        text = br.readLine();

        // KMP 알고리즘 이용
        for(int i = 0; i < text.length(); i++) {
            subText = text.substring(i);

            getPi();
        }

        System.out.println(maxLength);
    }

    /**
     * 부분 일치 테이블 구하기
     */
    public static void getPi() {
        pi = new int[subText.length()];

        // j: 접두사 index, i: 접미사 index
        int j = 0;
        for(int i = 1; i < subText.length(); i++) {
            while(j > 0 && subText.charAt(i) != subText.charAt(j)) {
                j = pi[j - 1];
            }

            if(subText.charAt(i) == subText.charAt(j)) {
                maxLength = Math.max(maxLength, pi[i] = ++j);
            }
        }
    }
}
