import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m, k;
    static int[][] profit;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        profit = new int[n][m];
        visited = new boolean[n][m];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(o->o[2])));
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                profit[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0 || j == 0 || i == n-1 || j == m-1) {
                    pq.offer(new int[] {i, j, profit[i][j]});
                    visited[i][j] = true;
                }
            }
        }
        k = Integer.parseInt(br.readLine());

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int cnt = 0;
        while (cnt++ < k) {
            int[] out = pq.poll();
            sb.append(out[0]+1).append(" ").append(out[1]+1).append('\n');
            for (int i = 0; i < 4; i++) {
                int nx = out[0] + dx[i];
                int ny = out[1] + dy[i];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    if (!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        pq.offer(new int[] {nx, ny, profit[nx][ny]});
                    }
                }
            }

        }

        System.out.println(sb);




    }

}
