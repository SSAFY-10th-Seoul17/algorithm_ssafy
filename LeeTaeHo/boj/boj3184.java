import java.io.*;
import java.util.*;
public class boj3184 {
    static int r, c, sheep, wolf;
    static char[][] map;
    static boolean[][] visited;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sheep = 0;
        wolf = 0;
        map = new char[r][c];
        visited = new boolean[r][c];
        for (int i = 0; i < r; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j = 0; j < c; j++){
                if(line[j] == 'o') sheep++;
                if(line[j] == 'v') wolf++;
                map[i][j] = line[j];
            }
        }
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(!visited[i][j] && map[i][j] != '#'){
                    bfs(i , j);
                }
            }
        }
        System.out.println(sheep + " " + wolf);
    }
    public static void bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        int sheepCnt = 0;
        int wolfCnt = 0;
        if(map[x][y] == 'o') sheepCnt++;
        if(map[x][y] == 'v') wolfCnt++;
        visited[x][y] = true;
        q.offer(new int[] {x, y});
        while (!q.isEmpty()){
            int[] current = q.poll();
            int cx = current[0];
            int cy = current[1];
            for(int[] nDir : dir){
                int dx = cx + nDir[0];
                int dy = cy + nDir[1];
                if(0 <= dx && dx < r && 0 <= dy && dy < c && !visited[dx][dy] && map[dx][dy] != '#'){
                    if(map[dx][dy] == 'o') sheepCnt++;
                    if(map[dx][dy] == 'v') wolfCnt++;
                    visited[dx][dy] = true;
                    q.offer(new int[] {dx, dy});
                }
            }
        }
        if(sheepCnt > wolfCnt){
            wolf = wolf - wolfCnt;
        }else{
            sheep = sheep - sheepCnt;
        }
    }
}
