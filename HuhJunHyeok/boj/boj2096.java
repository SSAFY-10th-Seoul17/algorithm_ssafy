import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 2096. 내려가기
 */
public class boj2096 {
    /**
     * N: 줄의 수
     * maxScore: 얻을 수 있는 최대 점수
     * minScore: 얻을 수 있는 최소 점수
     */
    static int N, maxScore, minScore;
    /**
     * maxDP: 최대 점수를 구하는 DP 배열, minDP: 최소 점수를 구하는 DP 배열
     * 지금 행과 이전 행만 비교하면 됨. 열은 3개로 고정. 따라서 new int[2][3] => 슬라이딩 윈도우 적용.
     */
    static int[][] maxDP = new int[2][3], minDP = new int[2][3];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            /*
            0열의 값은 이전 행의 0열과 1열 고려.
            1열의 값은 이전 행의 모든 열을 고려.
            2열의 값은 이전 행의 1열과 2열 고려.
            이후 현재 위치의 값을 더함.
             */
//            for(int j = 0; j < 3; j++) {
//                int nowValue = Integer.parseInt(st.nextToken());
//
//                if(j == 0) { // 0열
//                    maxDP[1][j] = Math.max(maxDP[0][0], maxDP[0][1]) + nowValue;
//                    minDP[1][j] = Math.min(minDP[0][0], minDP[0][1]) + nowValue;
//                } else if(j == 1) { // 1열
//                    maxDP[1][j] = Math.max(Math.max(maxDP[0][0], maxDP[0][1]), maxDP[0][2]) + nowValue;
//                    minDP[1][j] = Math.min(Math.min(minDP[0][0], minDP[0][1]), minDP[0][2]) + nowValue;
//                } else { // 2열
//                    maxDP[1][j] = Math.max(maxDP[0][1], maxDP[0][2]) + nowValue;
//                    minDP[1][j] = Math.min(minDP[0][1], minDP[0][2]) + nowValue;
//                }
//            }
            int idxZeroValue = Integer.parseInt(st.nextToken());
            int idxOneValue = Integer.parseInt(st.nextToken());
            int idxTwoValue = Integer.parseInt(st.nextToken());

            maxDP[1][0] = Math.max(maxDP[0][0], maxDP[0][1]) + idxZeroValue;
            maxDP[1][1] = Math.max(Math.max(maxDP[0][0], maxDP[0][1]), maxDP[0][2]) + idxOneValue;
            maxDP[1][2] = Math.max(maxDP[0][1], maxDP[0][2]) + idxTwoValue;

            minDP[1][0] = Math.min(minDP[0][0], minDP[0][1]) + idxZeroValue;
            minDP[1][1] = Math.min(Math.min(minDP[0][0], minDP[0][1]), minDP[0][2]) + idxOneValue;
            minDP[1][2] = Math.min(minDP[0][1], minDP[0][2]) + idxTwoValue;

            if(i != N - 1) {
                // maxDP와 minDP는 primitive type인 원소를 가진다. 따라서, Object.clone()은 원래 얕은 복사이지만 문제 없이 작동(마치 깊은 복사처럼).
                maxDP[0] = maxDP[1].clone();
                minDP[0] = minDP[1].clone();
            }
        }

        maxScore = Math.max(Math.max(maxDP[1][0], maxDP[1][1]), maxDP[1][2]);
        minScore = Math.min(Math.min(minDP[1][0], minDP[1][1]), minDP[1][2]);

//        System.out.println(maxScore + " " + minScore);
        sb.append(maxScore).append(" ").append(minScore);
        System.out.print(sb.toString());
    }
}
