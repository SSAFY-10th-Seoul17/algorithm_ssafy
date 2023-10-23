import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj3079 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());

        long[] time = new long[N];
        for (int num = 0; num < N; num++) {
            time[num] = Integer.parseInt(br.readLine());
        }

        solve(time, M);
    }

    private static void solve(long[] times, long m) {

        Arrays.sort(times);
        long low = 0;
        long high = times[times.length-1] * m;

        while (low + 1 < high){

            long mid = (low + high)/2;
            long count = 0;

            for(long time : times){
                if((count+= mid/time) >= m){
                    break;
                }
            }

            if(count >= m){
                high = mid;
            }else{
                low = mid;
            }
        }
        System.out.println(high);
    }
}
