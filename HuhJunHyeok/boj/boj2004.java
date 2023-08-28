import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 2004. 조합 0의 개수
 */
public class boj2004 {
    static int n, m, zeroCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        combZeroCount();

        System.out.println(zeroCount);
    }

    public static void combZeroCount() {
        // a가 x * 10^y 로 나타날 수 있다면 끝자리 0은 y개.
        // 10을 소인수분해하면 2 * 5.
        // 만약, 어떤 수를 소인수분해 했을 때 2^7 * 5^3 이면, y는 3이다. -> 2와 5의 페어를 만족하는 수의 최솟값.
        // 만약, 어떤 수를 소인수분해 했을 때 2^3 * 5^4 이면, y는 3이다. -> 2와 5의 페어를 만족하는 수의 최솟값.
        // 2의 exponent와 5의 exponent 중에서 min값이 y값이다.

        // nCr = n! / ((n - r)! * r!) : 문제에서는 r이 m으로 표현.
        // 위의 식으로 나오는 답의 끝자리 0의 개수를 구하는 문제. -> 식의 결과를 소인수분해.
        // int 범위 문제로 식의 결과를 나타낼 수 없음.

        int fiveCount = fiveExponentCount(n) - (fiveExponentCount(n - m) + fiveExponentCount(m));
        int twoCount = twoExponentCount(n) - (twoExponentCount(n - m) + twoExponentCount(m));

        zeroCount = fiveCount < twoCount ? fiveCount : twoCount;
    }

    public static int fiveExponentCount(int targetNum) {
        int fiveExponent = 0;
        while(targetNum >= 5) {
            targetNum /= 5;
            fiveExponent += targetNum;
        }
        return fiveExponent;
    }

    public static int twoExponentCount(int targetNum) {
        int twoExponent = 0;
        while(targetNum >= 2) {
            targetNum /= 2;
            twoExponent += targetNum;
        }
        return twoExponent;
    }
}
