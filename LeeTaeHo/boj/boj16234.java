import java.io.*;
import java.util.*;

public class boj16234 {
    static int[][] map;
    static int n, L, R;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        flag = true;

        int result = 0;

        while(true){
            visited = new boolean[n][n];

            int sum = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(!visited[i][j]){
                        sum += bfs(i, j);
                    }
                }
            }
            if(sum == 0) break;

            result++;
        }
        System.out.println(result);
    }

    static int bfs(int r, int c){
        Queue<int[]> inq = new LinkedList<>();
        inq.offer(new int[] {r, c});
        visited[r][c] = true;

        Queue<int[]> outq = new LinkedList<>();
        outq.offer(new int[] {r, c});

        int sum = map[r][c];
        int size = 1;

        while(!inq.isEmpty()){
            int[] node = inq.poll();
            int cr = node[0];
            int cc = node[1];
            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if(0 <= nr && nr < n && 0 <= nc && nc < n && !visited[nr][nc]){
                    int diff = Math.abs(map[cr][cc] - map[nr][nc]);
                    if(L <= diff && diff <= R){
                        visited[nr][nc] = true;
                        inq.offer(new int[] {nr, nc});
                        outq.offer(new int[] {nr, nc});
                        sum += map[nr][nc];
                        size++;
                    }
                }
            }
        }

        if(size == 1){
            return 0;
        } else{
            int avg = sum / size;
            while(!outq.isEmpty()){
                int[] node = outq.poll();
                map[node[0]][node[1]] = avg;
            }
            return size;
        }

    }
}