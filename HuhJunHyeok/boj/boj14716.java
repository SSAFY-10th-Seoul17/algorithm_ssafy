import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 14716. 현수막
 */
public class boj14716 {
    static int m, n, count;
    static int[][] banner;
    static boolean[][] visited;
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        banner = new int[m][n];
        visited = new boolean[m][n];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < n; j++) {
                banner[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(banner[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(count);
    }


    public static void bfs(int x, int y) {
        Queue<Integer> queueX = new ArrayDeque<Integer>();
        Queue<Integer> queueY = new ArrayDeque<Integer>();
        queueX.add(x);
        queueY.add(y);
        visited[x][y] = true;
        count++;

        while(!queueX.isEmpty()) {
            int nowX = queueX.poll();
            int nowY = queueY.poll();

            for(int i = 0; i < 8; i++) {
                int nextX = nowX + delta[i][0];
                int nextY = nowY + delta[i][1];

                if(!isInBoundary(nextX, nextY) || banner[nextX][nextY] == 0 || visited[nextX][nextY]) {
                    continue;
                }

                visited[nextX][nextY] = true;
                queueX.add(nextX);
                queueY.add(nextY);
            }
        }
    }

    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

}