import java.io.*;
import java.util.*;
/**
 * 문자열을 모두 만들려고 하지말자!!
 */
public class BOJ25795_예쁜초콜릿과숫자놀이 {
    static int N;
    static long a,b,c;
    static long result;
    static long pretty;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());
        result = 0;
        pretty = (a+b)*c;

        if (N == 1){ // N이 1이면 -> WD밖에 안됨
            result = ((a+b) * c) % 100000;
        } else{
            solve(0,0,a);
        }
        System.out.println(result);
    }

    private static void solve(int white, int black, long r){
        if( white == N && black == N){ // 모두 다 썼으면
            result = Math.max(result, r);
            return;
        }
        if (white < N ){
            solve(white+1, black, (r+b) % 100000 );
        }
        if ( white > black && black < N) {
            solve(white, black+1, (r*c) % 100000 );
        }
    }
}
