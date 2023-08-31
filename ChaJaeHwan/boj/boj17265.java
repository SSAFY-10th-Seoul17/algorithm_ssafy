import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17265 {

    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static char[][] graph;
    static int[][] dir = {{1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = st.nextToken().charAt(0);
            }
        }

        dfs(0, 0, (graph[0][0] - '0'), 'd');

        System.out.println(max + " " + min);

    }

    static void dfs(int r, int c, int sum, char prev) {
        if (r == N - 1 && c == N - 1) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }
        for (int i = 0; i < 2; i++) {
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];
            if (inGraph(nr, nc)) {
                switch (graph[nr][nc]) {
                    case '+':
                        dfs(nr, nc, sum, '+');
                        break;
                    case '-':
                        dfs(nr, nc, sum, '-');
                        break;
                    case '*':
                        dfs(nr, nc, sum, '*');
                        break;
                    default:
                        if (prev == '*') {
                            dfs(nr, nc, sum * (graph[nr][nc] - '0'), 'd');
                        } else if (prev == '-') {
                            dfs(nr, nc, sum - (graph[nr][nc] - '0'), 'd');
                        } else if (prev == '+') {
                            dfs(nr, nc, sum + (graph[nr][nc] - '0'), 'd');
                        }
                        break;
                }
            }

        }
    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }


}
