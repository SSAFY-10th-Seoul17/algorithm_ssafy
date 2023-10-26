import java.io.*;
import java.util.*;

public class boj16197 {
    static int[][] board;
    static int n, m;
    static boolean[][][][] visited;
    static Coins coins;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int r1 = -1, r2 = -1, c1 = -1, c2 = -1;
        board = new int[n][m];
        visited = new boolean[n][m][n][m];

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                char c = line[j];
                if(c == '.'){
                    board[i][j] = 1;
                }else if(c == '#'){
                    board[i][j] = -1;
                }else{
                    if(r1 == -1){
                        r1 = i;
                        c1 = j;
                    }else{
                        r2 = i;
                        c2 = j;
                    }
                }
            }
        }
        coins = new Coins(r1, c1, r2, c2, 0);

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Coins> q = new LinkedList<>();
        q.offer(coins);
        int cnt = -1;
        visited[coins.r1][coins.c1][coins.r2][coins.c2] = true;
        while (!q.isEmpty()){
            Coins c = q.poll();
            for (int[] drc : dir) {
                int nr1 = c.r1 + drc[0];
                int nr2 = c.r2 + drc[0];
                int nc1 = c.c1 + drc[1];
                int nc2 = c.c2 + drc[1];

                int dropCnt = 0;

                if(nr1 < 0 || nc1 < 0 || nr1 >= n || nc1 >= m){
                    dropCnt++;
                }
                if(nr2 < 0 || nc2 < 0 || nr2 >= n || nc2 >= m){
                    dropCnt++;
                }

                if(dropCnt == 1){
                    return c.cnt + 1;
                } else if (dropCnt == 2) {
                    continue;
                }

                if(c.cnt >= 10) return -1;

                if(board[nr1][nc1] == -1){
                    nr1 = c.r1;
                    nc1 = c.c1;
                }
                if(board[nr2][nc2] == -1){
                    nr2 = c.r2;
                    nc2 = c.c2;
                }

                if(visited[nr1][nc1][nr2][nc2]) continue;
                visited[nr1][nc1][nr2][nc2] = true;
                q.offer(new Coins(nr1, nc1, nr2, nc2, c.cnt + 1));

            }
        }
        return cnt;
    }

    static class Coins{
        int r1;
        int c1;
        int r2;
        int c2;
        int cnt;

        public Coins(int r1, int c1, int r2, int c2, int cnt) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.cnt = cnt;
        }
    }
}