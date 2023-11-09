import java.util.*;
import java.io.*;

// 10:32 -
// n x m 옥수수 가치
// 바깥을 돌아다니면서 바깥과 인접한 칸의 옥수수를 수확 가능
// 안에서 수확한 칸으로만 돌아다니면서 상하좌우
// k그루의 옥수수만 수확 , 가장 가치가 높은 옥수수 수확하는 과정 k 번 반복
// 위치를 순서대로 구하기
public class BOJ30024_옥수수밭 {
    static class Corn{
        int r;
        int c;
        int value;

        public Corn(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Corn{" +
                    "r=" + r +
                    ", c=" + c +
                    ", value=" + value +
                    '}';
        }
    }
    static int N, M, K;
    static int[][] map;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static boolean[][] visited;
    static PriorityQueue<Corn> q = new PriorityQueue<>((Corn o1, Corn o2) -> o2.value - o1.value );
    public static void main(String[] args) throws Exception {
        // 다해보기! k번 수확 하기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        } // end of input
        K = Integer.parseInt(br.readLine());
        visited = new boolean[N][M];

        // 바깥 테두리 큐에 넣기
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                if (i==0 || i==N-1 || j==0 || j==M-1){
                    q.offer(new Corn(i,j,map[i][j]));
                    visited[i][j] = true;
                }
            }

        }

        while(K-- > 0){
            Corn cur = q.poll();
            sb.append(cur.r+1).append(" ").append(cur.c+1).append('\n');

            for (int i=0; i<4; i++){
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr>=0 && nr<N && nc>=0 && nc<M && !visited[nr][nc]){
                    visited[nr][nc] = true;
                    q.offer(new Corn(nr, nc, map[nr][nc]));
                }
            }
        }
        System.out.println(sb.toString());
    }
}
