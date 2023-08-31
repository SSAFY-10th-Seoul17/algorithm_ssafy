import java.io.*;
import java.util.*;

public class boj1584 {
    static int[][] map, dist;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new int[501][501];
        dist = new int[501][501];

        for (int i = 0; i < 501; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int startX = Math.min(x1, x2);
            int endX = Math.max(x1, x2);
            int startY = Math.min(y1, y2);
            int endY = Math.max(y1, y2);


            for (int j = startX; j <= endX; j++) {
                for (int k = startY; k <= endY; k++) {
                    map[j][k] = 1;
                }
            }
        }

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int startX = Math.min(x1, x2);
            int endX = Math.max(x1, x2);
            int startY = Math.min(y1, y2);
            int endY = Math.max(y1, y2);

            for (int j = startX; j <= endX; j++) {
                for (int k = startY; k <= endY; k++) {
                    map[j][k] = -1;
                }
            }
        }
        bfs(0, 0);
        System.out.println(dist[500][500] == Integer.MAX_VALUE ? -1 : dist[500][500]);

    }
    private static void bfs(int r, int c){
        Deque<int[]> q = new LinkedList<>();
        q.addFirst(new int[] {r, c, 0});
        map[r][c] = -1;
        while(!q.isEmpty()){
            int[] node = q.poll();
            int cr = node[0];
            int cc = node[1];
            int cw = node[2];

            if(cr == 500 && cc == 500) break;

            if(dist[cr][cc] < cw) continue;

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if(nr < 0 || 501 <= nr || nc < 0 || 501 <= nc || map[nr][nc] == -1) continue;

                int nw = map[nr][nc] == 0 ? 0 : 1;

                if(dist[nr][nc] > cw + nw){
                    dist[nr][nc] = cw + nw;
                    if(map[nr][nc] == 0){
                        q.addFirst(new int[] {nr, nc, dist[nr][nc]});
                    }else{
                        q.addLast(new int[] {nr, nc, dist[nr][nc]});
                    }
                }
            }
        }
    }
}
