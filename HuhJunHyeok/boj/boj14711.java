import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 14711. 타일 뒤집기 (Easy)
 */
public class boj14711 {
    /**
     * N: 게임판의 크기
     */
    static int N;
    /**
     * board: 게임판
     */
    static char[][] board;
    static boolean[][] visited;
    /**
     * dr, dc: delta값. 하좌우 순서.
     */
    static int[] dr = {1, 0, 0}, dc = {0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        board = new char[N][N];
        visited = new boolean[N][N];
        String firstLine = br.readLine();
        for(int i = 0; i < N; i++) {
            board[0][i] = firstLine.charAt(i);
            sb.append(board[0][i]);
        }
        sb.append("\n");

        solve();

        System.out.print(sb.toString());

    }

    public static void solve() {
        for(int i = 0, checkSize = N - 1; i < checkSize; i++) {
            for(int j = 0; j < N; j++) {
                if(board[i][j] == '#') {
                    for(int d = 0; d < 3; d++) {
                        int nextR = i + dr[d];
                        int nextC = j + dc[d];

                        if(!isInBoard(nextR, nextC)) {
                            continue;
                        }

                        visited[nextR][nextC] = !visited[nextR][nextC];
                    }
                }
            }

            for(int j = 0; j < N; j++) {
                board[i + 1][j] = visited[i][j] ? '#' : '.';
                sb.append(board[i + 1][j]);
            }
            sb.append("\n");
        }
    }

    public static boolean isInBoard(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
