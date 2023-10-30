import java.util.*;
import java.io.*;

/**
 * 온전한 알약을 꺼내먹는 경우와 반쪽짜리 알약을 꺼내먹는 경우를 나누어 재귀함수로 구현했습니다.
 * 시간초과가 나서 메모이제이션으로 해결해주었습니다.
 * 또, int로 설정하면 오버플로우가 나므로 long으로 선언해주었습니다.
 */
public class BOJ4811_알약 {
    static int N,result;
    static long[][] memo;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        memo = new long[31][31]; // w , h 알약
        while (true){
            N =Integer.parseInt(br.readLine());
            if ( N == 0){
                break;
            }

            result = 0;
            System.out.println(solve(N,0));
        }
    }

    private static long solve(int whole, int half ){
        long w = 0;
        long h = 0;
        if (whole == 0 && half == 0){ // 약 다먹으면
            return 1;
        }

        if ( memo[whole][half] > 0){
            return memo[whole][half];
        }

        if (whole > 0){ // 온전한 알약이 있으면 - 온전한 알약꺼내기
            w = solve(whole-1 , half+1);
        }

        if (half > 0){ // 반쪽짜리 있으면 - 반쪽짜리 먹기
            h = solve(whole, half -1);
        }

        memo[whole][half] = w + h;
        return memo[whole][half];
    }
}
