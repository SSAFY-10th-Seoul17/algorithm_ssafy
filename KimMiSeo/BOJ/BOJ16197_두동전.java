import java.io.*;
import java.util.*;

/**
 *
 */

public class BOJ16197_두동전 {
    static int N,M, result;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] coin1;
    static int[] coin2;
    static boolean answerFlag;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        boolean flag = false;
        for (int i=0; i<N; i++){
            String temp = br.readLine();
            for (int j=0; j<M; j++){
                map[i][j] = temp.charAt(j);

                if (map[i][j] == 'o'){
                    if (flag){
                        coin2 = new int[]{i,j};

                    } else{
                        coin1 = new int[]{i,j};
                        flag = true;
                    }
                }
            }
        }

        result = -1;
        answerFlag = false;
        bfs(coin1[0], coin1[1], coin2[0], coin2[1]);
        System.out.println(result);

    }
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    private static void bfs(int r1, int c1, int r2, int c2){
        int cnt = 0;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r1, c1, r2, c2});
        visited[r1][c1][r2][c2] = true;

        while(!q.isEmpty() && cnt < 10){
            int size = q.size();
            cnt++;

            for (int t=0; t<size; t++) {
                int[] polls = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nr1 = polls[0] + dr[i];
                    int nc1 = polls[1] + dc[i];

                    int nr2 = polls[2] + dr[i];
                    int nc2 = polls[3] + dc[i];

                    // 움직일 수 있으면
                    if (can(nr1, nc1, nr2, nc2)) {
                        if (answerFlag) { // 끝났으면
                            result = cnt;
                            return;
                        }

                        if ((map[nr1][nc1] != '#' || map[nr2][nc2] != '#') && !visited[nr1][nc1][nr2][nc2]) {
                            if (map[nr1][nc1] == '#') {
                                visited[polls[0]][polls[1]][nr2][nc2] = true;
                                q.offer(new int[]{polls[0], polls[1], nr2, nc2});
                            } else if (map[nr2][nc2] == '#') {
                                visited[nr1][nc1][polls[2]][polls[3]] = true;
                                q.offer(new int[]{nr1, nc1, polls[2], polls[3]});
                            } else {
                                visited[nr1][nc1][nr2][nc2] = true;
                                q.offer(new int[]{nr1, nc1, nr2, nc2});
                            }
                        }
                    }

                }
            }

        }

    }

    private static boolean can(int r1, int c1, int r2, int c2){

        boolean coin1 = isOut(r1, c1);
        boolean coin2 = isOut(r2, c2);

        // 둘중 하나만 떨어지면
        if ( ( coin1 == false &&  coin2 == true) || (coin1 == true &&  coin2 == false)){
            answerFlag = true; // 정답이야
            return true;
        } else if (!coin1 && !coin2){ // 둘다 범위 안에 있으면
            return true;
        } else{ // 둘다 떨어지면
            return false;
        }

    }

    private static boolean isOut(int r, int c){
        if (r >=0 && r < N && c >= 0 && c < M ){ // 범위 안에 있으면
            return false;
        }else { // 범위 안에 없으면
            return true;
        }
    }

    private static void print(int[][] print){
        for(int i=0; i<N; i++){
            System.out.println(Arrays.toString(print[i]));
        }
    }
}
