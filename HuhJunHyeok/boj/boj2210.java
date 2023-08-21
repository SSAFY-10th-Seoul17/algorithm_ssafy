import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * [boj] 2210. 숫자판 점프
 */
public class boj2210 {
    static String[][] board;
    static HashSet<String> sixNums = new HashSet<>();
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static String[] usingNum;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new String[5][5];
        for(int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 5; j++) {
                board[i][j] = st.nextToken();
            }
        }

        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                usingNum = new String[6];
                dfs(0, i, j);
            }
        }

        System.out.println(sixNums.size());
    }

    public static void dfs(int depth, int x, int y) {
        if(depth == 6) {
            for(int i = 0; i < 6; i++) {
                sb.append(usingNum[i]);
            }
            sixNums.add(sb.toString());
            sb.setLength(0);
            return;
        }

        usingNum[depth] = board[x][y];
        for(int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(!isInBoundary(nextX, nextY)) {
                continue;
            }
            dfs(depth + 1, nextX, nextY);
        }

    }

    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < 5 && 0 <= y && y < 5;
    }
}