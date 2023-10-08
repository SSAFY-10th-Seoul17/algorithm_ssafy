import java.util.*;
import java.io.*;

/**
 * 점화식이 문제에 그대로 드러나 있으며, 중간 부분과 moo 에서는 m, o를 판단할 수 있습니다.
 * 따라서 재귀를 활용하여 n의 위치를 중간 부분이나, moo 안에 위치할 수 있도록 범위를 줄여나가야 합니다.
 */
public class BOJ5904_Moo게임 {
    static int N;
    static char result;
    static String m = "moo";
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int cnt = 3;
        int k = 0;

        while (cnt < N){
            k++;
            cnt = cnt + k+3 + cnt;
        }
        moo(k, cnt, N);
        System.out.println(result);
    }

    private static void moo (int k, int len, int N){
        if (len == 3){
            result = m.charAt(N-1);
            return;
        }
        int l = (len - (k+3))/2;
        int midlen = k+3;

        // 중앙에 있을 때
        if ( N >= l+1 && N <= l+midlen ){
            if (N == l+1){
                result = 'm';
            }else{
                result = 'o';
            }
            return;
        }else{ // 양옆일 때
            if (N > l+midlen){ // 왼쪽일 때
                N -= l+midlen;
            }
            moo(k-1, l , N);
        }
    }
}
