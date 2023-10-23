import java.io.*;
import java.util.*;

/**
 * 📚
 * 처음에는 완전 탐색을 해야하나 싶었지만 n이 10^6 , 1초여서 시간초과가 걸릴 것이라고 생각해서 다른 방법을 찾아야 한다는 판단을 내렸습니다.
 * 정렬 후에 시작점과 끝점을 두고, 두 수의 합이 k보다 작을 때는 시작점을 ++해주고, 클 때는 끝점을 --해줍니다.
 * 만약 minGap > gap 일 경우에는 answer을 0으로 초기화해주고, minGap을 갱신해줍니다.
 * 만약 minGap == gap 일 경우에는 answer ++ 해줍니다.
 */
public class BOJ9024_두수의합 {
    static int N,K;
    static int[] numbers;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc =0; tc<T; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            numbers = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<N; i++){
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(numbers);
            int answer = 0;
            int start = 0;
            int end = N-1;
            int minGap = Integer.MAX_VALUE;

            while(start < end){
                int sum = numbers[start] + numbers[end];
                int gap = Math.abs(sum - K);
                if ( minGap >= gap ){
                    if (minGap > gap){
                        minGap = gap;
                        answer = 0;
                    }
                    answer++;
                }
                if (sum >= K){
                    end--;
                }else{
                    start++;
                }
            }
            sb.append(answer).append("\n");
        } // end of tc
        System.out.println(sb.toString());
    }
}
