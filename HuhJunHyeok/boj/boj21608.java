import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 21608. 상어 초등학교
 */
public class boj21608 {
    /**
     * 학생의 정보를 나타내는 클래스
     */
    static class Student implements Comparable<Student> {
        /**
         * x, y: 좌표
         * likeCnt: 사방탐색 결과 좋아하는 학생 수
         * emptyCnt: 사방탐색 결과 빈 자리의 수
         */
        int x;
        int y;
        int likeCnt;
        int emptyCnt;

        public Student(int x, int y, int likeCnt, int emptyCnt) {
            this.x = x;
            this.y = y;
            this.likeCnt = likeCnt;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public int compareTo(Student o) {
            if(this.likeCnt != o.likeCnt) {
                return Integer.compare(o.likeCnt, this.likeCnt);
            }
            if(this.emptyCnt != o.emptyCnt) {
                return Integer.compare(o.emptyCnt, this.emptyCnt);
            }
            if(this.x != o.x) {
                return Integer.compare(this.x, o.x);
            }
            return Integer.compare(this.y, o.y);
        }
    }
    /**
     * N: 행 또는 열의 수, studentNum: 학생의 수
     * satisfactionSum: 학생 만족도의 총합
     */
    static int N, studentNum, satisfactionSum;
    /**
     * classroom: 교실의 좌석 배치를 나타내는 배열, like: 학생별 좋아하는 학생을 나타내는 배열(students 배열과 연관)
     */
    static int[][] classroom, like;
    /**
     * students: 학생들의 번호를 입력 받은 순서대로 저장할 배열.(like 배열과 연관)
     * dx, dy: delta. 상하좌우 순서.
     */
    static int[] students, dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        studentNum = N * N;

        classroom = new int[N][N];
        students = new int[studentNum + 1];
        like = new int[studentNum + 1][4];

        /**
         * 입력 순서대로 학생의 번호와 그 학생이 좋아하는 학생들을 저장.
         */
        for(int i = 1; i <= studentNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            students[i] = Integer.parseInt(st.nextToken()); // i번째 순서로 자리를 정하는 학생
            for(int j = 0; j < 4; j++) {
                like[students[i]][j] = Integer.parseInt(st.nextToken());
            }
        }

        batchStudent();
        sumSatisfaction();

        System.out.println(satisfactionSum);
    }

    /**
     * 학생들을 순서대로 자리 정하기
     */
    public static void batchStudent() {
        classroom[1][1] = students[1]; // 문제의 조건을 보면, 첫 번째 학생은 무조건 이 위치에 들어감.

        for(int i = 2; i <= studentNum; i++) {
            setPosition(i);
        }
    }

    /**
     * 문제에 주어진 규칙에 따라 자리 정하기(PriorityQueue 이용 type으로 사용하는 Student Class를 통해 규칙 적용)
     * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
     * 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
     * 3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
     *
     * 현재 학생이 들어갈 수 있는 모든 자리에 대해서 학생 객체를 만들어 우선순위 큐에 넣어서 최종 자리 결정.
     */
    public static void setPosition(int studentIdx) {
        PriorityQueue<Student> pq = new PriorityQueue<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(classroom[i][j] == 0) { // 빈 자리에 대해서만 진행
                    pq.add(getStudent(i, j, studentIdx));
                }
            }
        }

        Student batchStudent = pq.poll(); // 우선순위 큐를 통해 정해진 학생 객체.

        classroom[batchStudent.x][batchStudent.y] = students[studentIdx];

//        for(int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(classroom[i]));
//        }
//
//        System.out.println("============");
    }

    public static Student getStudent(int x, int y, int studentIdx) {
        int likeCnt = 0;
        int emptyCnt = 0;

        for(int dir = 0; dir < 4; dir++) {
            int nextX = x + dx[dir];
            int nextY = y + dy[dir];

            if(!isInBoundary(nextX, nextY)) {
                continue;
            }

            if(classroom[nextX][nextY] == 0) {
                emptyCnt++;
                continue;
            }

            for(int i = 0; i < 4; i++) {
                if(like[students[studentIdx]][i] == classroom[nextX][nextY]) {
                    likeCnt++;
                    break;
                }
            }
        }

        return new Student(x, y, likeCnt, emptyCnt);
    }

    /**
     * 학생 만족도의 총합 구하기
     */
    public static void sumSatisfaction() {
        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                int likeCnt = 0;

                for(int dir = 0; dir < 4; dir++) {
                    int nextX = x + dx[dir];
                    int nextY = y + dy[dir];

                    if(!isInBoundary(nextX, nextY)) {
                        continue;
                    }

                    for(int i = 0; i < 4; i++) {
                        if(like[classroom[x][y]][i] == classroom[nextX][nextY]) {
                            likeCnt++;
                            break;
                        }
                    }
                }

                if(likeCnt == 1) {
                    satisfactionSum += 1;
                } else if(likeCnt == 2) {
                    satisfactionSum += 10;
                } else if(likeCnt == 3) {
                    satisfactionSum += 100;
                } else if(likeCnt == 4) {
                    satisfactionSum += 1000;
                }
            }
        }
    }

    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
