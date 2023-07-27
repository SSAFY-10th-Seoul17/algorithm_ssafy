import java.util.*;
import java.io.*;

class Main {
    static class Position {
        int r, c, cost, dir;
        public Position(int r, int c, int cost, int dir) {
            this.r = r;
            this.c = c;
            this.cost = cost;
            this.dir = dir;
        }
    }
    public static int[][] map, direction = {{1,-1}, {1,0}, {1,1}};
    public static int row, col, min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        for(int i=0; i<row; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i=0; i<col; i++) {
            bfs(0, i);
        }
        System.out.println(min);
        br.close();
    }

    public static void bfs(int r, int c) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(r, c, map[r][c], -1));
        while(!queue.isEmpty()) {
            Position p = queue.poll();
            if(p.r == row - 1) {
                min = Math.min(min, p.cost);
                continue;
            }
            for(int i=0; i<direction.length; i++) {
                if(p.dir != i) {
                    int nextR = p.r + direction[i][0];
                    int nextC = p.c + direction[i][1];
                    if(checkPos(nextR, nextC)) {
                        queue.offer(new Position(nextR, nextC, p.cost+map[nextR][nextC], i));
                    }
                }
            }
        }
    }

    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }
}