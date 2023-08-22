import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj3184 {

    static char[][] map;
    static boolean[][] visited;
    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static int countV = 0;
    static int countO = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int row = 0; row < N; row++) {
            map[row] = br.readLine().toCharArray();
        }
        visited = new boolean[map.length][map[0].length];

        for (int row = 1; row < N-1; row++) {
            for (int column = 1; column < M-1; column++) {
                if (map[row][column] == '#' || visited[row][column]) {
                    continue;
                }
                solve(row, column);
            }
        }
        System.out.print(countO + " " + countV);
    }

    private static void solve(int row, int column) {

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{row, column});
        int cntV = 0;
        int cntO = 0;

        while (!queue.isEmpty()) {

            int[] cur = queue.poll();
            if (visited[cur[0]][cur[1]]) {
                continue;
            }
            visited[cur[0]][cur[1]] = true;

            if (map[cur[0]][cur[1]] == 'v') {
                cntV++;
            } else if (map[cur[0]][cur[1]] == 'o') {
                cntO++;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = cur[0] + dy[i];
                int nextColumn = cur[1] + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= map.length || nextColumn >= map[0].length) {
                    return;
                }
                if (map[nextRow][nextColumn] == '#') {
                    continue;
                }

                queue.offer(new int[]{nextRow, nextColumn});
            }
        }

        if (cntV >= cntO) {
            countV += cntV;
        } else {
            countO += cntO;
        }
    }
}
