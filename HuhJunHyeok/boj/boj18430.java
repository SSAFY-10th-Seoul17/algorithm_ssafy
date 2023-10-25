import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 18430. 무기 공학
 */
public class boj18430 {
    /**
     * N: 나무 재료의 세로 크기, M: 나무 재료의 가로 크기
     * size: 나무 재료의 총 면적, maxStrength: 부메랑들의 강도 합의 최댓값
     */
    static int N, M, size, maxStrength;
    /**
     * material: 나무 재료
     */
    static int[][] material;
    static boolean[][] visited;
    static int[][] dr = {{1, 0}, {-1, 0}, {-1, 0}, {1, 0}}, dc = {{0, -1}, {0, -1}, {0, 1}, {0, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        size = N * M;

        material = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                material[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        if(N >= 2 && M >= 2) {
            solve(0, 0);
        }

        System.out.println(maxStrength);
    }

    public static void solve(int idx, int nowStrength) {
        if(idx == size) {
            maxStrength = Math.max(maxStrength, nowStrength);

            return;
        }

        int r = idx / M;
        int c = idx % M;

        if(!visited[r][c]) {
            for(int i = 0; i < 4; i++) {
                int nextR1 = r + dr[i][0];
                int nextC1 = c + dc[i][0];
                int nextR2 = r + dr[i][1];
                int nextC2 = c + dc[i][1];

                if(isInMaterial(nextR1, nextC1) && !visited[nextR1][nextC1] && isInMaterial(nextR2, nextC2) && !visited[nextR2][nextC2]) {
                    visited[r][c] = true;
                    visited[nextR1][nextC1] = true;
                    visited[nextR2][nextC2] = true;

                    solve(idx + 1, nowStrength + material[r][c] * 2 + material[nextR1][nextC1] + material[nextR2][nextC2]);

                    visited[r][c] = false;
                    visited[nextR1][nextC1] = false;
                    visited[nextR2][nextC2] = false;
                }
            }
        }
        solve(idx + 1, nowStrength);
    }

    public static boolean isInMaterial(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
