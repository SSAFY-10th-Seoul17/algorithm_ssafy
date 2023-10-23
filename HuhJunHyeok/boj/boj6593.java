import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 6593. 상범 빌딩
 */
public class boj6593 {
    static int L, R, C, personX, personY, personZ; // L: 빌딩의 층 수, R: 한 층의 행 수, C: 한 층의 열 수
    static char[][][] building;
    static int[][][] visited;
    // 동서남북상하
    static int[] dx = {0, 0, 1, -1, 0, 0};
    static int[] dy = {1, -1, 0, 0, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    // S: 시작 지점, E: 탈출할 수 있는 출구, #: 막혀있는 칸, .: 비어있는 칸
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String testCaseInput;;
        while(!(testCaseInput = br.readLine()).equals("0 0 0")) {
            StringTokenizer st = new StringTokenizer(testCaseInput, " ");
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            building = new char[L][R][C];
            visited = new int[L][R][C];

            for(int i = 0; i < L; i++) {
                for(int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for(int k = 0; k < C; k++) {
                        building[i][j][k] = line.charAt(k);
                        if(building[i][j][k] == 'S') {
                            personX = j;
                            personY = k;
                            personZ = i;
                        }
                    }
                }
                br.readLine();
            }
//			System.out.println(personX + " " + personY + " " + personZ);
            bfs(personX, personY, personZ);
        }
        System.out.print(sb.toString());
    }

    public static void bfs(int x, int y, int z) {
        Queue<int[]> coordinateQueue = new LinkedList<>();
        coordinateQueue.add(new int[] {x, y, z});
        visited[z][x][y] = 1;

        while(!coordinateQueue.isEmpty()) {
            int[] nowCoordinate = coordinateQueue.poll();
            int nowX = nowCoordinate[0];
            int nowY = nowCoordinate[1];
            int nowZ = nowCoordinate[2];

            for(int i = 0; i < 6; i++) {
                int nextX = nowCoordinate[0] + dx[i];
                int nextY = nowCoordinate[1] + dy[i];
                int nextZ = nowCoordinate[2] + dz[i];

                if(!isInBoundary(nextX, nextY, nextZ) || building[nextZ][nextX][nextY] == '#' || visited[nextZ][nextX][nextY] != 0) {
                    continue;
                }

                if(building[nextZ][nextX][nextY] == 'E') {
                    sb.append("Escaped in ").append(visited[nowZ][nowX][nowY]).append(" minute(s).").append("\n");
                    return;
                }

                visited[nextZ][nextX][nextY] = visited[nowZ][nowX][nowY] + 1;
                coordinateQueue.add(new int[] {nextX, nextY, nextZ});
            }
        }
        sb.append("Trapped!").append("\n");
    }

    public static boolean isInBoundary(int x, int y, int z) {
        return 0 <= x && x < R && 0 <= y && y < C && 0 <= z && z < L;
    }
}