import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 28449. 누가 이길까
 */
public class boj28449 {
    /**
     * N: HI팀의 인원 수, M: ARC팀의 인원 수
     */
    static int N, M;
    /**
     * teamHI: HI팀 참가자들의 점수, teamARC: ARC팀 참가자들의 점수
     */
    static int[] teamHI, teamARC;
    /**
     * hiWinCount: HI팀 참가자의 승리 횟수, arcWinCount: ARC팀 참가자의 승리 횟수, drawCount: 무승부 횟수
     */
    static long hiWinCount, arcWinCount, drawCount;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        teamHI = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            teamHI[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(teamHI);

        teamARC = new int[M];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < M; i++) {
            teamARC[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(teamARC);


        solve();

        sb.append(hiWinCount).append(" ").append(arcWinCount).append(" ").append(drawCount).append("\n");

        System.out.print(sb.toString());
    }

    public static void solve() {
        for(int score: teamHI) {
            int lowerBoundResult = lowerBound(score);
            int upperBoundResult = upperBound(score);
            int onlySame = upperBoundResult - lowerBoundResult;

//            System.out.println(lowerBoundResult + " " + upperBoundResult);

            drawCount += onlySame;
            hiWinCount += (upperBoundResult - onlySame);
            arcWinCount += (M - upperBoundResult);
        }
    }

    /**
     * 이분 탐색 중 lower bound 이용.
     * hi팀 참가자의 점수(이하 score)를 기준으로
     * arc팀 참가자 중 score보다 같거나 큰 점수를 받은 index 반환.
     * @param score hi팀 참가자의 점수
     * @return
     */
    public static int lowerBound(int score) {
        int result = M;

        int low = 0;
        int high = M - 1;

        while(low <= high) {
            int mid = (low + high) >> 1;

            if(teamARC[mid] >= score) {
                high = mid - 1;
                result = mid;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    /**
     * 이분 탐색 중 upper bound 이용.
     * hi팀 참가자의 점수(이하 score)를 기준으로
     * arc팀 참가자 중 score보다 큰 점수를 받은 index 반환.
     * @param score hi팀 참가자의 점수
     * @return
     */
    public static int upperBound(int score) {
        int result = M;

        int low = 0;
        int high = M - 1;

        while(low <= high) {
            int mid = (low + high) >> 1;

            if(teamARC[mid] > score) {
                high = mid - 1;
                result = mid;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }
}
