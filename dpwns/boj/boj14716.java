import java.util.*;
import java.io.*;

class Main{
    static class Position {
        int r, c;
        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static int[][] map, visited, direction = {{1,0}, {-1,0}, {0,1}, {0,-1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
    public static int row, col;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        visited = new int[row][col];
        Queue<Position> queue = new LinkedList<>();
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) queue.offer(new Position(i, j));
            }
        }
        int cnt = 0;
        for(Position p : queue) {
            if(visited[p.r][p.c] == 0) {
                cnt++;
                visited[p.r][p.c] = 1;
                dfs(p.r, p.c);
            }
        }
        System.out.println(cnt);
        br.close();
    }

    public static void dfs(int r, int c) {
        for(int i=0; i<direction.length; i++) {
            int nextR = r + direction[i][0];
            int nextC = c + direction[i][1];
            if(checkPos(nextR, nextC) && visited[nextR][nextC] == 0 && map[nextR][nextC] == 1) {
                visited[nextR][nextC] = 1;
                dfs(nextR, nextC);
            }
        }
    }

    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }

}