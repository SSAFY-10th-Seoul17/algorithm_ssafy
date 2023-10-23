import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 2660. 회장뽑기
 */
public class boj2660 {
    static final int MAX = Integer.MAX_VALUE;
    /**
     * N: 회원의 수,
     * minScore: 회장 후보의 점수
     * candidateCount: 회장 후보의 수
     */
    static int N, minScore = MAX, candidateCount = 1;
    /**
     * adjMatrix: 친구 사이를 나타내는 인접행렬
     */
    static int[][] adjMatrix;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        adjMatrix = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                adjMatrix[i][j] = MAX;
            }
        }
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int friendOne = Integer.parseInt(st.nextToken());
            if(friendOne == -1) { // -1 이 있는 경우는 한 줄에 -1 이 두 개인 경우뿐
                break;
            }
            int friendTwo = Integer.parseInt(st.nextToken());

            adjMatrix[friendOne][friendTwo] = adjMatrix[friendTwo][friendOne] = 1;
        }

        // floyd-warshall 적용
        for(int k = 1; k <= N; k++) { // 경유 회원
            for(int i = 1; i <= N; i++) { // 시작 회원
                if(k == i) {
                    continue;
                }
                for(int j = 1; j <= N; j++) { // 끝 회원
                    if(k == j || adjMatrix[i][k] == MAX || adjMatrix[k][j] == MAX) {
                        continue;
                    }
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            int nowScore = 0;
            for(int j = 1; j <= N; j++) {
                if(i != j) {
                    nowScore = Math.max(nowScore, adjMatrix[i][j]);
                }
            }
            adjMatrix[i][i] = nowScore != 0 ? nowScore : MAX;
            if(minScore > adjMatrix[i][i]) {
                minScore = adjMatrix[i][i];
                candidateCount = 1;
            } else if(minScore == adjMatrix[i][i]) {
                candidateCount++;
            }
        }

        sb.append(minScore).append(" ").append(candidateCount).append("\n");

        for(int i = 1; i <= N; i++) {
            if(adjMatrix[i][i] == minScore) {
                sb.append(i).append(" ");
            }
        }
        sb.append("\n");

        System.out.print(sb.toString());
    }
}
