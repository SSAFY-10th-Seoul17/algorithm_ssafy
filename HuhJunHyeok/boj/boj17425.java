import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 17425. 약수의 합
 */
public class boj17425 {
    /**
     * T: 테스트 케이스의 수, N: 자연수
     * 1 ≤ T ≤ 100,000
     * 1 ≤ N ≤ 1,000,000
     */
    static int T, N;
    /**
     * fx: 해당 자연수의 모든 약수를 더한 값을 저장하는 배열
     * gx: 해당 자연수 이하의 모든 자연수의 fx값을 더한 값을 저장하는 배열
     */
    static long[] fx, gx;
    static final int MAX_N_VALUE = 1_000_000;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        fx = new long[MAX_N_VALUE + 1];
        gx = new long[MAX_N_VALUE + 1];

        for(int i = 1; i <= MAX_N_VALUE; i++) {
            fx[i] = 1; // 1은 모든 자연수의 약수이기 때문에 1로 초기화.
        }

            /*
            자연수 N의 약수 M. ==> M의 배수 N 이용.
             */
        for(int i = 2; i <= MAX_N_VALUE; i++) { // 약수
            for(int j = 1; i * j <= MAX_N_VALUE; j++) { // 배수 설정
                fx[i * j] += i; // 자연수 i의 배수인 i * j에 해당하는 fx값을 i 증가.
            }
        }
        // gx는 fx를 이용한 누적 합으로 해결.
        for(int i = 1; i <= MAX_N_VALUE; i++) {
            gx[i] += (gx[i - 1] + fx[i]);
        }

        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());

            sb.append(gx[N]).append("\n");
        }
        System.out.print(sb.toString());
    }
}
