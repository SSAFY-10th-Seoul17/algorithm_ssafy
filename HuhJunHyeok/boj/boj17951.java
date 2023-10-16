import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 17951. 흩날리는 시험지 속에서 내 평점이 느껴진거야
 */
public class boj17951 {
    /**
     * N: 시험지의 수, K: 시험지를 나눌 그룹의 수, totalScore: 시험지의 맞은 수의 합, maxScore: 현수가 받을 수 있는 최대 점수
     */
    static int N, K, totalScore, maxScore;
    /**
     * testPapers: 시험지
     */
    static int[] testPapers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        testPapers = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            testPapers[i] = Integer.parseInt(st.nextToken());
            totalScore += testPapers[i];
        }

        parametricSearch();

        System.out.println(maxScore);
    }

    public static void parametricSearch() {
        int low = 0;
        int high = totalScore;

        while(low <= high) {
            int mid = (low + high) / 2;

            if(getGroupCount(mid) >= K) {
                low = mid + 1;
                maxScore = mid;
            } else {
                high = mid - 1;
            }
        }
    }

    public static int getGroupCount(int score) {
        int groupCount = 0;
        int sumScore = 0;

        for(int testScore: testPapers) {
            sumScore += testScore;
            if(sumScore >= score) {
                sumScore = 0;
                groupCount++;
            }
        }

        return groupCount;
    }

}
