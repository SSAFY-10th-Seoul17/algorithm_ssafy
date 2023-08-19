import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 3184. 양
 */
public class boj3184 {
    /**
     * R: 행의 수, C: 열의 수
     * nowSheep: 현재 영역의 양의 수, nowWolf: 현재 영역의 늑대의 수
     * sheep: 살아남은 양의 수, wolf: 살아남은 늑대의 수
     */
    static int R, C, nowSheep, nowWolf, sheep, wolf;
    /**
     * map: 뒷마당 정보. '.': 빈 필드, '#': 울타리, 'o': 양, 'v': 늑대
     * 수평 수직으로만 이동 가능. 울타리를 지나지 않고 다른 칸 이동 가능 -> 같은 영역. 탈출할 수 있는 칸은 어떤 영역도 아님
     * 영역에서 양의 수 > 늑대 수 ? 늑대 쫓아냄 : 늑대가 양을 모두 잡아 먹음
     */
    static char[][] map;
    /**
     * visited: 방문체크용
     */
    static boolean[][] visited;
    /**
     * 수평 수직 이동용 dx, dy. 상하좌우 순서.
     */
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    /**
     * 좌표 저장을 위한 클래스
     */
    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            String line = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if((map[i][j] == 'o' || map[i][j] == 'v') && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(sheep + " " + wolf); // 한 줄. 컴파일러가 최적화.
    }

    /**
     * 영역 탐색 BFS
     */
    public static void bfs(int x, int y) {
        nowSheep = 0;
        nowWolf = 0;

        if(map[x][y] == 'o') {
            nowSheep++;
        } else { // else if 가 아니라 else 쓴 이유는 bfs함수를 호출할 때 'o' or 'v' 라는 조건이 있기 때문.
            nowWolf++;
        }

        Queue<Point> pointQueue = new ArrayDeque<>();
        pointQueue.offer(new Point(x, y));
        visited[x][y] = true;

        while(!pointQueue.isEmpty()) {
            Point nowPoint = pointQueue.poll();

            for(int i = 0; i < 4; i++) {
                int nextX = nowPoint.x + dx[i];
                int nextY = nowPoint.y + dy[i];

                if(!isInBoundary(nextX, nextY) || map[nextX][nextY] == '#' || visited[nextX][nextY]) {
                    continue;
                }

                if(map[nextX][nextY] == 'o') {
                    nowSheep++;
                } else if(map[nextX][nextY] == 'v') {
                    nowWolf++;
                }
                pointQueue.offer(new Point(nextX, nextY));
                visited[nextX][nextY] = true;
            }
        }

        if(nowSheep > nowWolf) {
            sheep += nowSheep;
        } else {
            wolf += nowWolf;
        }
    }

    /**
     * 해당 좌표가 map에 있는 좌표인지 확인.
     */
    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
