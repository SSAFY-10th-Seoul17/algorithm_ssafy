import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 17265. 나의 인생에는 수학과 함께
 */
public class boj17265 {
    /**
     * N: 지도의 크기, max: 연산 결과의 최댓값, min: 연산 결과의 최솟값
     */
    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    /**
     * map: 지도
     */
    static char[][] map;
    /**
     * dx, dy: 아래쪽, 오른쪽 방향의 delta
     */
    static int[] dx = {1, 0}, dy = {0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0, idx = 0; j < N; j++, idx += 2) {
                map[i][j] = st.nextToken().charAt(0);
            }
        }

        dfs(0, 0, map[0][0] - '0');

        System.out.println(max + " " + min);
    }

    public static void dfs(int x, int y, int value) {
        if(x == N - 1 && y == N - 1) { // 학교(목적지) 도착
            max = max > value ? max : value;
            min = min < value ? min : value;
            return;
        }

        for(int i = 0; i < 2; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(!isInBoundary(nextX, nextY)) {
                continue;
            }

            if(map[x][y] == '+') { // 현재 칸이 '+'
                dfs(nextX, nextY, value + (map[nextX][nextY] - '0'));
            } else if(map[x][y] == '-') { // 현재 칸이 '-'
                dfs(nextX, nextY, value - (map[nextX][nextY] - '0'));
            } else if(map[x][y] == '*') { // 현재 칸이 '*'
                dfs(nextX, nextY, value * (map[nextX][nextY] - '0'));
            } else { // 현재 칸이 숫자칸
                dfs(nextX, nextY, value);
            }
        }
    }

    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
