import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj2210 {

    static int N = 5;
    static char[][] graph = new char[N][N];

    static Set<Integer> set = new HashSet<>();
    static char[] nums = new char[6];
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = st.nextToken().charAt(0);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(i, j, 0);
            }
        }


        System.out.println(set.size());

    }

    static void dfs(int r, int c, int depth) {
        if (depth == 6) {
            set.add(Integer.parseInt(new String(nums)));
            return;
        }
        nums[depth] = graph[r][c];
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (inGraph(nr, nc)) {
                dfs(nr, nc, depth + 1);
            }
        }
    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < 5 && 0 <= c && c < 5;
    }
}
