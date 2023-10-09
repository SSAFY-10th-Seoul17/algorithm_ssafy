import java.util.*;
import java.io.*;

/**
 * 처음부터 이분탐색으로 풀이하는 것을 떠올리는 건 저에겐 아직 어려운 것 같습니다 🥲
 * 최대 질투심을 정해놓고 이분탐색을 하면서 가능한 최소 질투심을 찾았습니다.
 */
public class BOJ2792_보석상자 {
    static int N,M,minResult,max;
    static int[] jewels;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 아이들의 수
        M = Integer.parseInt(st.nextToken()); // 색상의 수
        jewels = new int[M];
        minResult = Integer.MAX_VALUE;
        max = 1;

        for (int i=0; i<M; i++){
            jewels[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, jewels[i]);
        }

        int start = 1;
        int end = max;
        int mid = end;

        while(start <= end){
            mid = (start + end) / 2;
            int cnt = jiltu(mid);

            if (cnt > N){ // 질투심이 더 커도 될때
                start = mid+1;
            } else { // 질투심을 줄여야 할 때
                end = mid-1;
                minResult = Math.min(minResult, mid);
            }
        }
        System.out.println(minResult);
    }

    private static int jiltu(int num){
        int cnt = 0;
        for (int i=0; i<M; i++){
            cnt += Math.ceil (jewels[i] / (double)num);
        }
        return cnt;
    }
}
