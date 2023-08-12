import java.io.*;
import java.util.*;

// 숫자판 점프
public class Boj2210 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(map, i, j, 0, 0);
            }
        }

        System.out.println(set.size());
    }
    static Set<Integer> set = new HashSet<>();
    static int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static final int N = 5;

    private static void dfs(int[][] map, int i, int j, int depth, int sum) {
        if (depth > N) {
            set.add(sum);
            return;
        }
        sum *= 10;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0 || y < 0 || x >= N || y >= N) {
                continue;
            }

            dfs(map, x, y, depth + 1, sum + map[x][y]);
        }
    }


}

