import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj3079 {

    static int N, M;
    static int[] simsa;
    static int[] dp;
    static long min = 1000000000000000000l;
    static long MAX = 1000000000000000000l;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        simsa = new int[N];
        for(int i = 0; i < N; i++) {
            simsa[i] = Integer.parseInt(br.readLine());
        }
        long left = 1;
        long right = min;
        while(left <= right) {
            long mid = (left + right) /2;
            long answer = second(mid);
            if(answer >= M) {

                min = Math.min(min, mid);
                right = mid-1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(min);
    }
    public static long second(long t) {
        long sum = 0l;
        for(int i = 0; i < N; i++) {
            sum += (t/simsa[i]);
            if(sum >= MAX){
                return sum;
            }
        }
        return sum;
    }
}
