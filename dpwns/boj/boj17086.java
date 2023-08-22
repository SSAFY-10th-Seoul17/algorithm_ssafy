import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        int r, c, dis;
        public Position(int r, int c, int dis){
            this.r = r;
            this.c = c;
            this.dis = dis;
        }
    }
    public static int[][] map, distance, direction = {{1,0}, {-1,0}, {1,1}, {1, -1}, {-1, -1}, {-1, 1}, {0,1}, {0,-1}};
    public static int row, col;
    public static Queue<Position> sharks = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        distance = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(distance[i], Integer.MAX_VALUE);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    distance[i][j] = 0;
                    sharks.add(new Position(i, j, 0));
                }
            }
        }
        calDistance();
        System.out.println(Arrays.stream(distance).flatMapToInt(d -> Arrays.stream(d)).max().getAsInt());
        br.close();
    }

    public static void calDistance() {
        while(!sharks.isEmpty()) {
            Position p = sharks.poll();

            for (int i = 0; i < direction.length; i++) {
                int nextR = p.r + direction[i][0];
                int nextC = p.c + direction[i][1];
                if(checkPos(nextR, nextC) && distance[nextR][nextC] > p.dis+1) {
                    distance[nextR][nextC] = p.dis + 1;
                    sharks.offer(new Position(nextR, nextC, distance[nextR][nextC]));
                }
            }
        }
    }

    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }

}
