import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14716 {

    static int M;
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visited = new boolean[M][N];

        for (int row = 0; row < M; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < N; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }

        for (int row = 0; row < M; row++) {
            for (int column = 0; column < N; column++) {

                if (map[row][column] == 0 || visited[row][column]) {
                    continue;
                }
                solve(row, column);
                count++;
            }
        }

        System.out.println(count);
    }

    public static void solve(int row, int column) {

        visited[row][column] = true;

        for (int i = 0; i < 8; i++) {

            int nextRow = row + dy[i];
            int nextColumn = column + dx[i];

            if (nextRow < 0 || nextColumn < 0 || nextRow >= M || nextColumn >= N) {
                continue;
            }

            if (visited[nextRow][nextColumn] || map[nextRow][nextColumn] == 0) {
                continue;
            }

            solve(nextRow, nextColumn);
        }
    }
}
