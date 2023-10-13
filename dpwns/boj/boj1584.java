import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Position{
        int r, c, life;

        public Position(int r, int c, int life) {
            this.r=r;
            this.c=c;
            this.life=life;
        }

    }
    public static final int row = 501, col = 501;
    public static int[][] map = new int[row][col], visited = new int[row][col], direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int k = 0; k < n; k++) {
            setInput(br, 1);
        }

        int m = Integer.parseInt(br.readLine());

        for (int k = 0; k < m; k++) {
            setInput(br, 2);
        }
        System.out.println(bfs());
        br.close();
    }

    public static int bfs() {
        Deque<Position> queue = new ArrayDeque<>();
        queue.addFirst(new Position(0, 0, 0));
        visited[0][0] = 1;
        while (!queue.isEmpty()) {
            Position p = queue.poll();
            if(p.r == row - 1 && p.c == col - 1) return p.life;
            for (int i = 0; i < direction.length; i++) {
                int nextR = p.r + direction[i][0];
                int nextC = p.c + direction[i][1];

                if(checkPos(nextR, nextC) && visited[nextR][nextC] == 0) {
                    // 죽음
                    if(map[nextR][nextC] == 2) continue;
                    // 0-1 경우의 수 분리
                    if(map[nextR][nextC] == 1) queue.addLast(new Position(nextR, nextC, p.life + 1));
                    else queue.addFirst(new Position(nextR, nextC, p.life));
                    visited[nextR][nextC] = 1;
                }
            }
        }
        return -1;
    }

    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }

    public static void setInput(BufferedReader br, int zone) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken()), x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
        for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
            for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                map[i][j] = zone;
            }
        }
    }
    
}
