import java.io.*;
import java.util.*;

/**
 * longlong!!!! long 끼리 계산할 때는 long 처리를 합시다~~
 */
public class BOJ23829_인물예술탐사주간 {
    static int N,Q,P;
    static long result;
    static int[] trees;
    static long[] sums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        trees = new int[N];
        sums = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++){
            trees[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(trees); // 정렬
        // 구간합 구하기
        sums[0] = trees[0];
        for (int i=1; i<N; i++){
            sums[i] = sums[i-1] + trees[i];
        }

        for (int i=0; i<Q; i++){ // 사진 수만큼
            P = Integer.parseInt(br.readLine());
            int location = find(P);
            long smallerSum = 0;
            int smallerCnt = 0;
            long biggerSum = 0;
            int bigCnt = 0;

            if (location == 0){
                biggerSum = sums[N-1];
                bigCnt = N;

            } else{
                smallerSum = sums[location-1];
                smallerCnt = location;
                biggerSum = sums[N-1] - sums[location-1];
                bigCnt = N - (location);
            }

            result = 0;
            result += ((long)P * smallerCnt) - smallerSum;
            result += biggerSum - ((long)P * bigCnt);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
    private static int find(int p){ // 이분 탐색으로 위치 찾기
        int start = 0;
        int end = N;

        while (start < end){
            int mid = (start + end) / 2;
            if (trees[mid] < p){ // 크면 -> end 이동
                start = mid + 1;
            } else{
                end = mid;
            }
        }
        return end;
    }
}
