import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 10159. 저울
 */
public class boj10159 {
    /**
     * N: 물건의 수, M: 미리 측정된 물건 쌍의 수, count: 특정 물건에 대해 비교 결과를 알 수 없는 물건의 수
     */
    static int N, M, count;
    static int[][] adjMatrix;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjMatrix = new int[N + 1][N + 1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());

            adjMatrix[heavy][light] = 1;
            adjMatrix[light][heavy] = -1;
        }

        floydWarshall();

        for(int i = 1; i <= N; i++) {
            count = 0;
            for(int j = 1; j <= N; j++) {
                if(adjMatrix[i][j] == 0 && i != j) {
                    count++;
                }
            }
            sb.append(count).append("\n");
        }

        System.out.print(sb.toString());
    }

    public static void floydWarshall() {
        for(int k = 1; k <= N; k++) { // mid
            for(int i = 1; i <= N; i++) { // start
                if(k == i) { // 최적화 추가
                    continue;
                }
                for(int j = 1; j <= N; j++) { // end
                    if(i == j || j == k) {
                        continue;
                    }
                    if(adjMatrix[i][k] != 0 && adjMatrix[i][k] == adjMatrix[k][j]) {
                        adjMatrix[i][j] = adjMatrix[i][k];
                    }
                }
            }
        }
    }
}
