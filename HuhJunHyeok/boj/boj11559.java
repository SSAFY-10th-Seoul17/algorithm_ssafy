import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 * [boj] 11559. Puyo Puyo
 */
public class boj11559 {
    static class Puyo {
        int r, c;
        char color;

        public Puyo(int r, int c, char color) {
            this.r = r;
            this.c = c;
            this.color = color;
        }
    }
    /**
     * HEIGHT: 필드 행의 수, WIDTH: 필드 열의 수
     */
    static final int HEIGHT = 12, WIDTH = 6;
    /**
     * field: 뿌요뿌요 필드
     */
    static char[][] field;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    /**
     * chainCnt: 일어난 연쇄의 수
     */
    static int chainCnt;
    /**
     * visited: 방문 체크
     */
    static boolean[][] visited;
    /**
     * puyoQueue: bfs용 queue
     */
    static Queue<Puyo> puyoQueue = new ArrayDeque<>();
    /**
     * nowChaining: 연결된 뿌요
     */
    static ArrayList<int[]> nowChaining;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        field = new char[HEIGHT][WIDTH];
        for(int i = 0; i < HEIGHT; i++) {
            field[i] = br.readLine().toCharArray();
        }

        while(true) {
            if(!existChain()) {
                break;
            }
            moveDownPuyo();
            chainCnt++;
        }

        System.out.println(chainCnt);
    }

    /**
     * 연쇄 여부 확인.
     * @return
     */
    public static boolean existChain() {
        int chainGroup = 0;
        for(int i = HEIGHT - 1; i >= 0; i--) {
            for(int j = 0; j < WIDTH; j++) {
                if(field[i][j] != '.' && bfsFindChainingPuyo(i, j)) { // 뿌요 발견 & 해당 뿌요로 연쇄가 가능
                    chainGroup++;
                }
            }
        }
        return chainGroup > 0;
    }

    public static boolean bfsFindChainingPuyo(int r, int c) {
        visited = new boolean[HEIGHT][WIDTH];
        visited[r][c] = true;

        puyoQueue.offer(new Puyo(r, c, field[r][c]));

        nowChaining = new ArrayList<>();
        nowChaining.add(new int[] {r, c});

        while(!puyoQueue.isEmpty()) {
            Puyo now = puyoQueue.poll();

            for(int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];

                if(!isInField(nextR, nextC) || visited[nextR][nextC] || field[nextR][nextC] != now.color) {
                    continue;
                }

                puyoQueue.add(new Puyo(nextR, nextC, field[nextR][nextC]));
                nowChaining.add(new int[] {nextR, nextC});
                visited[nextR][nextC] = true;
            }
        }

        if(nowChaining.size() >= 4) {
            for(int[] chainPuyo : nowChaining) {
                field[chainPuyo[0]][chainPuyo[1]] = '.'; // 연쇄 가능한 뿌요는 연쇄하면서 터트림.
            }
            return true;
        }
        return false;
    }

    public static void moveDownPuyo() {
        for(int i = 0; i < WIDTH; i++) {
            int moveR = -1;
            for(int j = HEIGHT - 1; j >= 0; j--) {
                if(moveR == -1 && field[j][i] == '.') {
                    moveR = j; // 옮길 위치의 행의 값.
                } else if(moveR != -1 && field[j][i] != '.') {
                    field[moveR--][i] = field[j][i];
                    field[j][i] = '.';
                }
            }
        }
    }

    public static boolean isInField(int r, int c) {
        return 0 <= r && r < HEIGHT && 0 <= c && c < WIDTH;
    }
}
