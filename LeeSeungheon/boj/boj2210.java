import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2210 {
    static boolean[] visited = new boolean[1_000_000];
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int[][] map;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[5][5];
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                solve(i, j, 1, "");
            }
        }
        System.out.println(cnt);
    }

    private static void solve(int row, int column, int count, String cur) {

        if (count == 7) {
            if (visited[Integer.parseInt(cur)]) {
                return;
            }
            cnt++;
            visited[Integer.parseInt(cur)] = true;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextRow = row + dy[i];
            int nextColumn = column + dx[i];

            if (nextRow < 0 || nextColumn < 0 || nextRow >= 5 || nextColumn >= 5) {
                continue;
            }

            solve(nextRow, nextColumn, count + 1, cur + map[nextRow][nextColumn]);
        }
    }
}
