import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 18243. Small World Network
 */
public class boj18243 {
    /**
     * N: 지구에 있는 사람의 수, K: 친구 관계의 수
     */
    static int N, K;
    /**
     * adjMatrix: 인접 행렬. 플로이드 워셜의 결과값.
     */
    static int[][] adjMatrix;
    static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adjMatrix = new int[N + 1][N + 1];

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                adjMatrix[i][j] = i == j ? 0 : MAX;
            }
        }
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            // 양방향
            adjMatrix[from][to] = 1;
            adjMatrix[to][from] = 1;
        }

        floydWarshall();

        System.out.println(isSmallWorldNetwork() ? "Small World!" : "Big World!");
    }

    /**
     * 플로이드 워셜 알고리즘 적용.
     * 점화식: D[i][j] = min(D[i][j], D[i][k] + D[k][j])
     */
    public static void floydWarshall() {
        for(int k = 1; k <= N; k++) { // 중간 정점이라 생각.
            for(int i = 1; i <= N; i++) {
                if(k == i || adjMatrix[i][k] == MAX) { // 최적화 추가
                    continue;
                }
                for(int j = 1; j <= N; j++) {
                    adjMatrix[i][j] = adjMatrix[i][j] < adjMatrix[i][k] + adjMatrix[k][j]
                            ? adjMatrix[i][j] : adjMatrix[i][k] + adjMatrix[k][j];
                }
            }
        }
    }

    /**
     * 플로이드 워셜로 나온 결과값을 토대로 해당 네트워크가 작은 세상 네트워크인지 판별.
     * 결과값 배열에서 값이 6이 넘는 경우 false return.
     */
    public static boolean isSmallWorldNetwork() {
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(adjMatrix[i][j] > 6) {
                    return false;
                }
            }
        }
        return true;
    }
}
