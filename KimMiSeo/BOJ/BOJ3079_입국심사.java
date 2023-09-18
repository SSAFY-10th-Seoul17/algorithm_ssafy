import java.util.*;
import java.io.*;

/**
 * 1. 문제에서 주어진 대로 풀리지 않는다면
 * 2. 아무 결과를 대입해봤을 때 (찍었을 때) 그에 대한 결과가 나온다면
 * 3. 이분탐색을 의심해보자!
 */
public class BOJ3079_입국심사 {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 입국 심사대
        long M = Integer.parseInt(st.nextToken()); // 사람 수
        long maxTime = Long.MIN_VALUE;
        long result = 0;

        long[] T = new long[N]; // 심사대에서 걸리는 시간 배열

        for (int i=0; i<N; i++){
            T[i] = Long.parseLong(br.readLine());
            maxTime = Math.max(maxTime, T[i]);
        }

        long start = 1;
        long end = maxTime * M;

        while (start <= end){
            long mid = (start + end)/2;
            long cnt = 0;

            for (int i=0; i<N; i++){
                cnt += mid / T[i];
                if (cnt > M){
                    break;
                }
            }

            if (cnt < M){ // 사람수보다 적을 때 -> 무조건 뒤로
                start = mid+1;
            }else{
                end = mid -1;
                result = mid;
            }
        }
        System.out.println(result);
    }
}
