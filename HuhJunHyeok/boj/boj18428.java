import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [boj] 18428. 감시 피하기
 */
public class boj18428 {
    static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    /**
     * N: 복도의 크기
     */
    static int N;
    /**
     * map: 복도의 정보, 학생 = S, 선생님 = T, 빈 칸 = X, 설치한 장애물 = O
     */
    static char[][] map;
    /**
     * teacherList: 선생님들의 위치 정보 리스트
     */
    static ArrayList<Point> teacherList = new ArrayList<>();
    /**
     * dr, dc: 상하좌우 순서의 delta 값.
     */
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = st.nextToken().charAt(0);

                if(map[i][j] == 'T') {
                    teacherList.add(new Point(i, j));
                }
            }
        }

        setObstacle(0, 0, 0);

        // 아래 코드를 실행한다면 결과적으로 감시 회피를 실패한 것.(회피 성공인 경우 System.exit(0)으로 종료 처리)
        System.out.println("NO");
    }

    /**
     * dfs를 통해 장애물을 3개 설치.(조합, combination)
     * @param r
     * @param c
     * @param cnt
     */
    public static void setObstacle(int r, int c, int cnt) {
        if(cnt == 3) {
            if(isAvailableToAvoidSurveillance()) { // 감시 회피 성공
                System.out.println("YES");
                System.exit(0); // 프로그램 종료.
            }
            return;
        }

        for(int i = r; i < N; i++) {
            for(int j = c; j < N; j++) {
                if(map[i][j] == 'X') { // 빈 칸인 경우
                    map[i][j] = 'O';
                    setObstacle(i, j + 1, cnt + 1);
                    map[i][j] = 'X';
                }
            }
            c = 0;
        }
    }

    /**
     * 장애물 설치를 완료한 후, 모든 학생들이 선생님들의 감시를 피할 수 있는지 확인.
     * @return
     */
    public static boolean isAvailableToAvoidSurveillance() {
        for(int i = 0, size = teacherList.size(); i < size; i++) {
            Point teacher = teacherList.get(i);

            for(int j = 0; j < 4; j++) {
                int row = teacher.row;
                int col = teacher.col;

                while(true) {
                    row += dr[j];
                    col += dc[j];

                    // 1. 해당 방향의 끝까지 학생이 없어서 범위를 벗어난 경우
                    // 2. 장애물이 있는 경우
                    if(!isInBoundary(row, col) || map[row][col] == 'O') {
                        break; // 해당 방향으로의 탐색 종료.
                    }

                    if(map[row][col] == 'S') { // 학생이 감시 범위에 걸림.
                        return false; // 학생들이 회피에 실패.
                    }
                }
            }
        }

        return true; // 학생들이 회피에 성공.
    }

    public static boolean isInBoundary(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
