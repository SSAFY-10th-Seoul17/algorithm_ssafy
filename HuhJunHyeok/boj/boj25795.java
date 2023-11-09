import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 25795. 예쁜 초콜릿과 숫자놀이
 */
public class boj25795 {
    /**
     * N: 화이트 초콜릿과 다크 초콜릿의 개별 수량,
     * a: 시작 정수, b: 화이트 초콜릿인 경우 더할 수, c: 다크 초콜릿인 경우 곱할 수
     */
    static int N, a, b, c;
    static long maxScore;
    static final int MOD = 100_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        solve(0, 0, a);

        System.out.println(maxScore);
    }

    public static void solve(int whiteCnt, int darkCnt, long score) {
        if(whiteCnt == N && darkCnt == N) {
            maxScore = Math.max(maxScore, score);
            return;
        }

        if(whiteCnt == darkCnt) {
            solve(whiteCnt + 1, darkCnt, (score + b) % MOD);
        } else if(whiteCnt > darkCnt && whiteCnt == N) {
            solve(whiteCnt, darkCnt + 1, (score * c) % MOD);
        } else if(whiteCnt > darkCnt) {
            solve(whiteCnt + 1, darkCnt, (score + b) % MOD);
            solve(whiteCnt, darkCnt + 1, (score * c) % MOD);
        }
    }
}
