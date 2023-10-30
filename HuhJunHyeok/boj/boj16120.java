import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 16120. PPAP
 */
public class boj16120 {
    /**
     * text: PPAP 문자열인지 판별할 문자열.
     */
    static String text;
    static int textLength, pCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        text = br.readLine();
        textLength = text.length();

        for(int i = 0; i < textLength; i++) {
            if(text.charAt(i) == 'P') {
                pCount++;
                continue;
            }

            if(pCount >= 2 && i < textLength - 1 && text.charAt(i + 1) == 'P') {
                pCount--;
                i++;
            } else {
                System.out.println("NP");
                return;
            }
        }

        System.out.println(pCount == 1 ? "PPAP" : "NP");
    }
}
