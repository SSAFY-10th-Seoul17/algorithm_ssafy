import java.util.*;
import java.io.*;

class Main{
    static class Position{
        int r, c;
        public Position(int r, int c){
            this.r=r; this.c=c;
        }
    }
    public static int[][] initMap;
    public static int n, m, max = 0;
    public static int[][] direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        initMap = new int[n][m];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                initMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(max+"");
        br.close();
    }
    public static void dfs(int cnt){
        if(cnt==3){
            cntSafeArea();
            return;
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(initMap[i][j] == 0){
                    initMap[i][j] = 1;
                    dfs(cnt+1);
                    initMap[i][j] = 0;
                }
            }
        }
    }
    public static void cntSafeArea(){
        Queue<Position> queue = findVirus();
        int[][] map = new int[n][m];
        for(int i=0; i<n; i++){
            map[i] = Arrays.copyOf(initMap[i], initMap[i].length);
        }
        while(!queue.isEmpty()){
            Position p = queue.poll();
            for(int i=0; i<direction.length; i++){
                int nextR = p.r + direction[i][0];
                int nextC = p.c + direction[i][1];
                if(checkPos(nextR, nextC) && map[nextR][nextC] == 0){
                    map[nextR][nextC] = 2;
                    queue.offer(new Position(nextR, nextC));
                }
            }
        }
        int cnt = (int)Arrays.stream(map).flatMapToInt(m -> Arrays.stream(m)).filter(a -> a == 0).count();
        max = Math.max(cnt, max);
    }
    public static boolean checkPos(int r, int c){
        return (r<0 || r>=n || c<0 || c>=m) ? false : true;
    }

    public static Queue<Position> findVirus(){
        Queue<Position> q = new LinkedList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(initMap[i][j] == 2) q.offer(new Position(i, j));
            }
        }
        return q;
    }
}
