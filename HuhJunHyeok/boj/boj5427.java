import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 5427. 불
 */
public class boj5427 {
    static int tc, w, h, personX = -1, personY = -1;
    static char[][] building;
    static boolean[][] visited;
    static Queue<Integer> firePointX;
    static Queue<Integer> firePointY;
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        tc = Integer.parseInt(br.readLine());
        for(int testCase = 0; testCase < tc; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            building = new char[h][w];
            visited = new boolean[h][w];
            firePointX = new ArrayDeque<>();
            firePointY = new ArrayDeque<>();

            for(int i = 0; i < h; i++) {
                String nowRow = br.readLine();
                for(int j = 0; j < w; j++) {
                    building[i][j] = nowRow.charAt(j);
                    if(building[i][j] == '@') { // 사람이면
                        personX = i;
                        personY = j;
                    } else if(building[i][j] == '*') { // 불이면
                        firePointX.add(i);
                        firePointY.add(j);
                    }
                }
            }

            escapeBfs(personX, personY);
        }
        System.out.print(sb.toString());
    }

    public static void escapeBfs(int x, int y) {
        Queue<Integer> queuePointX = new ArrayDeque<>();
        Queue<Integer> queuePointY = new ArrayDeque<>();
        queuePointX.add(-1); // 사람 움직임보다 불길 확산이 먼저
        queuePointY.add(-1); // 사람 움직임보다 불길 확산이 먼저
        queuePointX.add(x);
        queuePointY.add(y);
        visited[x][y] = true;
        int time = 0;

        while(!queuePointX.isEmpty()) {
            int nowX = queuePointX.poll();
            int nowY = queuePointY.poll();

            if(nowX == - 1 && nowY == -1) { // 불길 확산 먼저
                spreadFire();
                if(!queuePointX.isEmpty()) {
                    queuePointX.add(nowX);
                    queuePointY.add(nowY);
                }
                time++;
                continue;
            }

            for(int i = 0; i < 4; i++) { // 사람 이동
                int nextX = nowX + delta[i][0];
                int nextY = nowY + delta[i][1];

                if(!isInBoundary(nextX, nextY)) { // 사람이 범위 밖으로 나감. 즉, 탈출.
                    sb.append(time).append("\n");
                    return;
                }

                if(building[nextX][nextY] == '.' && !visited[nextX][nextY]) { // 미방문 빈 공간이면
                    queuePointX.add(nextX);
                    queuePointY.add(nextY);
                    visited[nextX][nextY] = true;
                }
            }
        }
        sb.append("IMPOSSIBLE").append("\n");
    }

    public static void spreadFire() { // 불길 확산
        int fireQueueSize = firePointX.size();

        for(int i = 0; i < fireQueueSize; i++) {
            int nowX = firePointX.poll();
            int nowY = firePointY.poll();

            for(int j = 0; j < 4; j++) {
                int nextX = nowX + delta[j][0];
                int nextY = nowY + delta[j][1];

                if(!isInBoundary(nextX, nextY) || building[nextX][nextY] == '#' || building[nextX][nextY] == '*') {
                    continue;
                }

                firePointX.add(nextX);
                firePointY.add(nextY);
                building[nextX][nextY] = '*';
            }
        }
    }

    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }
}
