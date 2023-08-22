import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj17086 {

    static final int[] dx = {1, 0, -1, 1, -1, 1, 0, -1};
    static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        Queue<int[]> queue = new ArrayDeque<>();

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int column = 0; column < M; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
                if (map[row][column] == 1) {
                    queue.offer(new int[]{row, column, 1});
                }
            }
        }

        System.out.println(solve(queue, map));
    }

    private static int solve(Queue<int[]> queue, int[][] map) {

        long[] flag = new long[map.length];

        int max = 0;
        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            if ((flag[cur[0]] & (1L << cur[1])) != 0) {
                continue;
            }
            flag[cur[0]] |= (1L << cur[1]);
            max = Math.max(max, cur[2]);

            for (int i = 0; i < 8; i++) {
                int nextRow = cur[0] + dy[i];
                int nextColumn = cur[1] + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= map.length || nextColumn >= map[0].length) {
                    continue;
                }

                queue.offer(new int[]{nextRow, nextColumn, cur[2] + 1});
            }
        }
//        for (int i = 0; i < map.length; i++) {
//            System.out.println(Arrays.toString(visited[i]));
//        }
//        for (int i = 0; i < map.length; i++) {
//            System.out.println(flag[i]);
//        }

        return max - 1;
    }
}
