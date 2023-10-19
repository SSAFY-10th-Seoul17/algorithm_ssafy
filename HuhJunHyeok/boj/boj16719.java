import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 16719. ZOAC
 */
public class boj16719 {
    /**
     * text: 주어진 문자열
     */
    static String text;
    /**
     * textLength: 문자열의 길이
     */
    static int textLength;
    /**
     * check: 문자열에서 각 index에 해당하는 문자(글자)를 사용 여부 체크.
     */
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        text = br.readLine();
        textLength = text.length();
        check = new boolean[textLength];

        recur(0, textLength - 1);

        System.out.print(sb.toString());
    }

    public static void recur(int leftIdx, int rightIdx) {
        if(leftIdx > rightIdx) {
            return;
        }

        int idx = leftIdx;
        for(int i = leftIdx; i <= rightIdx; i++) { // leftIdx ~ rightIdx에서 사전순 가장 빠른 글자 찾기
            if(text.charAt(i) < text.charAt(idx)) {
                idx = i;
            }
        }
        check[idx] = true;

        for(int i = 0; i < textLength; i++) {
            if(check[i]) {
                sb.append(text.charAt(i));
            }
        }
        sb.append("\n");

        // 사전순 가장 빠른 글자를 기준으로 오른쪽, 왼쪽 순서로 확인.
        recur(idx + 1, rightIdx);
        recur(leftIdx, idx - 1);
    }
}
