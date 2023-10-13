import java.io.*;
import java.util.*;

public class boj14497 {
    static int n, m, startR, startC, endR, endC;
    static int[][] dist;
    static char[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        dist = new int[n][m];

        st = new StringTokenizer(br.readLine());

        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;
        endR = Integer.parseInt(st.nextToken()) - 1;
        endC = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            for (int j = 0; j < m; j++) {
                map[i][j] = line[j];
            }
        }
        dijkstra();
        System.out.println(dist[endR][endC]);

    }

    private static void dijkstra(){
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        q.offer(new int[] {startR, startC, 0});
        while (!q.isEmpty()){
            int[] node = q.poll();
            int cr = node[0];
            int cc = node[1];
            int cw = node[2];
            if(dist[cr][cc] < cw) continue;

            for(int i = 0; i < 4; i++){
                int nr = cr + dr[i];
                int nc = cc + dc[i];
                if(nr < 0 || n <= nr || nc < 0 || m <= nc) continue;
                if(dist[nr][nc] > cw + (map[nr][nc] == '0' ? 0 : 1)){
                    dist[nr][nc] = cw + (map[nr][nc] == '0' ? 0 : 1);
                    q.offer(new int[] {nr, nc, dist[nr][nc]});
                }
            }
        }
    }
}
