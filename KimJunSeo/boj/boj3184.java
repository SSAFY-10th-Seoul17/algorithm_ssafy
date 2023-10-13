import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj3184 {
    static int N, M;
    static char[][] board;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M];
        int yang=0, wolf = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int ret = 0;
                if(!visited[i][j]) ret= dfs(i, j);
                if (ret > 0) {
                    yang+=ret;
                }
                else{
                    wolf-=ret;
                }
            }
        }
        System.out.println(yang +" " + wolf);
    }

    static int dfs(int x, int y) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{x, y});
        visited[x][y]=true;
        int yang = 0, wolf = 0;
        if (board[x][y] == 'o') {
            yang++;
        } else if (board[x][y] == 'v') {
            wolf++;
        }
        while (!queue.isEmpty()) {
            int[] temp = queue.pollLast();
            x = temp[0]; y = temp[1];
            if (board[x][y] == 'o') {
                yang++;
            } else if (board[x][y] == 'v') {
                wolf++;
            }
            for (int i = 0; i < 4; i++) {
                int X = x + dx[i], Y = y + dy[i];
                if (0<=X && X<N && 0<= Y && Y<M && board[X][Y]!='#' && !visited[X][Y]) {
                    visited[X][Y]=true;
                    queue.add(new int[]{X, Y});
                }
            }
        }
        if (yang > wolf) {
            return yang;
        }
        else {
            return -wolf;
        }
    }
}
