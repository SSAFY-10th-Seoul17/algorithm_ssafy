import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 2467. 용액
 */
public class boj2467 {
    /**
     * N: 용액의 수
     * minAbsSumValue: 용액의 특성값 합의 절댓값의 최솟값
     */
    static int N, minAbsSumValue = Integer.MAX_VALUE;
    /**
     * aqueousSolutions: 용액의 특성값 배열, answerAqueousSolution: 특성값의 합이 0에 가장 가까운 용액을 만들어내는 두 용액의 특성값
     */
    static int[] aqueousSolutions, answerAqueousSolution = new int[2];
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        aqueousSolutions = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            aqueousSolutions[i] = Integer.parseInt(st.nextToken());
        }

        binarySearch();

        sb.append(answerAqueousSolution[0]).append(" ").append(answerAqueousSolution[1]).append("\n");

        System.out.print(sb.toString());
    }

    public static void binarySearch() {
        int lastIdx = N - 1;

        for(int i = 0; i < N; i++) {
            int low = i + 1;
            int high = lastIdx;

            while(low <= high) {
                int mid = (low + high) >> 1;

                int sumValue = aqueousSolutions[i] + aqueousSolutions[mid];

                if(Math.abs(sumValue) < minAbsSumValue) {
                    minAbsSumValue = Math.abs(sumValue);
                    answerAqueousSolution[0] = aqueousSolutions[i];
                    answerAqueousSolution[1] = aqueousSolutions[mid];
                }

                if(sumValue < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

    }
}
