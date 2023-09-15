import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 1802. 종이 접기
 */
public class boj1802 {
    /**
     * T: 테스트 케이스의 수
     */
    static int T;
    /**
     * fold: 종이의 정보.
     * 길이는 3000보다 작으며, 항상 2^N - 1꼴이다. (N ≥ 1) => 즉 무조건 홀수
     */
    static String fold;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        testCase:
        for(int t = 0; t < T; t++) {
            fold = br.readLine();

            if(divideAndConquer(0, fold.length() - 1)) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }

        System.out.print(sb.toString());
    }

    /**
     * 분할 정복 활용.
     * 문자열의 중간값은 어떤 방향으로 접어도 된다. (문자열의 길이가 1일 때도 어떤 방향으로 접어도 된다.)
     * 중간값을 기준으로 봤을 때, 대칭되는 좌우의 값이 달라야 한다.
     */
    public static boolean divideAndConquer(int firstIdx, int lastIdx) {
        if(firstIdx == lastIdx) {
            return true;
        }

        int midIdx = (firstIdx + lastIdx) / 2;

        for(int i = firstIdx; i < midIdx; i++) {
            if(fold.charAt(i) == fold.charAt(lastIdx - i)) {
                return false;
            }
        }

        return divideAndConquer(firstIdx, midIdx - 1) && divideAndConquer(midIdx + 1, lastIdx);
    }
}
