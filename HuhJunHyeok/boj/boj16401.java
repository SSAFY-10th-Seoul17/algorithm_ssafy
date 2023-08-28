import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 16401. 과자 나눠주기
 */
public class boj16401 {
    /**
     * M: 조카의 수, N: 과자의 수, maxLength: 나눠주는 과자의 최대 길이, max: 과자 길이의 배열에서 최댓값
     */
    static int M, N, maxLength, max;
    /**
     * snack: 과자 길이의 배열
     */
    static int[] snack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        snack = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            snack[i] = Integer.parseInt(st.nextToken());
            max = max > snack[i] ? max : snack[i];
        }

        distributeSanck();

        System.out.println(maxLength);
    }

    public static void distributeSanck() {
//        Arrays.sort(snack); // 밑에서 탐색할 때 snack의 0번 인덱스부터 끝까지 다 돌기 때문에 배열의 최대 과자 길이만 배열 원소 입력 받을 때 확인하면 됨 그러면 불필요한 라인.

        int low = 1;
//        int high = snack[N - 1];
        int high = max;
        while(low <= high) {
            int mid = (low + high) / 2;
            int distributeCnt = 0;

            for(int j = 0; j < N; j++) {
                distributeCnt += snack[j] / mid;
            }

            if(distributeCnt >= M) {
                low = mid + 1;
                maxLength = maxLength > mid ? maxLength : mid;
            } else {
                high = mid - 1;
            }
        }
//        maxLength = 0; // maxLength default value => 0. 따라서 주석 처리.
    }
}
