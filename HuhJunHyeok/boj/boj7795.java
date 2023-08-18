import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 7795. 먹을 것인가 먹힐 것인가
 */
public class boj7795 {
    /**
     * T: 테스트 케이스의 수, N: A의 수, M: B의 수, pairCnt: A가 B보다 큰 쌍의 개수
     */
    static int T, N, M, pairCnt;
    static int[] A, B;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for(int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            A = new int[N];
            B = new int[M];

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            binarySearch();
        }
        System.out.print(sb.toString());
    }

    /**
     * 문제의 답을 찾기 위해 B 배열을 정렬 후, A 배열의 원소 각각을 타겟 넘버로 하여 B 배열을 이분 탐색 진행.
     * 탐색의 대상은 B 배열의 index. 탐색 종료 시의 결과인 index 값이 선택된 타겟 넘버로 설정된 A 배열의 원소의 값보다
     * 작은 B 배열의 원소 수이다.
     */
    public static void binarySearch() {
        pairCnt = 0;
        Arrays.sort(B);

        for(int targetNum: A) {
            int start = 0;
            int end = M - 1;
            int index = 0;

            while(start <= end) {
                int mid = (start + end) / 2;
                if(B[mid] < targetNum) {
                    start = mid + 1;
                    index = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
            pairCnt += index;
        }
        sb.append(pairCnt).append("\n");
    }
}
