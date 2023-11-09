import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 23829. 인문예술탐사주간
 */
public class boj23829 {
    /**
     * N: 나무의 수, Q: 찍은 사진의 수, leftTreeCnt: 사진 찍은 위치보다 왼쪽에 위치한 나무의 수
     */
    static int N, Q, leftTreeCnt;
    static int[] trees;
    static long[] prefixSumArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        trees = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(trees);

        prefixSum();
        while(Q-- > 0) {
            int picLocation = Integer.parseInt(br.readLine());

            lowerBound(picLocation);
//            System.out.println("AAA : " + leftTreeCnt);
            long leftSum = ((long) picLocation * leftTreeCnt) - prefixSumArr[leftTreeCnt];
            long rightSum = (prefixSumArr[N] - prefixSumArr[leftTreeCnt]) - ((long) picLocation * (N - leftTreeCnt));

            sb.append(leftSum + rightSum).append("\n");
        }

        System.out.print(sb.toString());

    }

    public static void prefixSum() {
        prefixSumArr = new long[N + 1];

        for(int i = 1; i <= N; i++) {
            prefixSumArr[i] = prefixSumArr[i - 1] + trees[i - 1];
        }
    }

    public static void lowerBound(int picLoc) {
        leftTreeCnt = -1;
        int low = 0;
        int high = N - 1;

        while(low <= high) {
            int mid = (low + high) >> 1;
            if(trees[mid] >= picLoc) {
//                System.out.println("mid : " + mid);
                leftTreeCnt = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if(leftTreeCnt == -1) {
            leftTreeCnt = N;
        }
    }
}
