import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class boj18428 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }

    }

    static int N;
    static char[][] graph;
    static int[][] obstacles;

    static List<Node> students = new ArrayList<>();
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new char[N][N];
        obstacles = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                char ch = st.nextToken().charAt(0);
                graph[i][j] = ch;
                if (ch == 'S') {
                    students.add(new Node(i, j));
                }
            }
        }
        if (solve()){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static boolean solve() {
        boolean flag = false;
        for (Node student : students) {
            if (nearT(student)) {
                return false;
            }
            int r = student.r;
            int c = student.c;
            //상
            for (int i = r - 1; i >= 0; i--) {
                if (graph[i][c] == 'T') {
                    for (int j = r - 1; j > i; j--) {
                        obstacles[j][c]++;
                    }
                }
            }
            //하
            for (int i = r + 1; i < N; i++) {
                if (graph[i][c] == 'T') {
                    for (int j = r + 1; j < i; j++) {
                        obstacles[j][c]++;
                    }
                }
            }
            //좌
            for (int i = c - 1; i >= 0; i--) {
                if (graph[r][i] == 'T') {
                    for (int j = c - 1; j > i; j--) {
                        obstacles[r][j]++;
                    }
                }
            }
            //우
            for (int i = c + 1; i < N; i++) {
                if (graph[r][i] == 'T') {
                    for (int j = c + 1; j < i; j++) {
                        obstacles[r][j]++;
                    }
                }
            }
        }
//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(obstacles[i]));
//        }
//        System.out.println("++++++++++");
        for (Node student : students) {
            int r = student.r;
            int c = student.c;
            //상
            for (int i = r - 1; i >= 0; i--) {
                if (graph[i][c] == 'O'){
                    break;
                }
                if (graph[i][c] == 'T') {
                    int max = 0;
                    int maxIdx = i;
                    for (int j = r - 1; j > i; j--) {
                        if (max < obstacles[j][c]) {
                            maxIdx = j;
                            max = obstacles[j][c];
                        }
                        obstacles[j][c] = 0;
                    }
                    graph[maxIdx][c] = 'O';
                    break;
                }
            }
            //하
            for (int i = r + 1; i < N; i++) {
                if (graph[i][c] == 'O'){
                    break;
                }
                if (graph[i][c] == 'T') {
                    int max = 0;
                    int maxIdx = i;
                    for (int j = r + 1; j < i; j++) {
                        if (max < obstacles[j][c]) {
                            maxIdx = j;
                            max = obstacles[j][c];
                        }
                        obstacles[j][c] = 0;
                    }
                    graph[maxIdx][c] = 'O';
                    break;
                }
            }
            //좌
            for (int i = c - 1; i >= 0; i--) {
                if (graph[r][i] == 'O'){
                    break;
                }
                if (graph[r][i] == 'T') {
                    int max = 0;
                    int maxIdx = i;
                    for (int j = c - 1; j > i; j--) {
                        if (max < obstacles[r][j]) {
                            maxIdx = j;
                            max = obstacles[r][j];
                        }
                        obstacles[r][j] = 0;
                    }
                    graph[r][maxIdx] = 'O';
                    break;
                }
            }
            //우
            for (int i = c + 1; i < N; i++) {
                if (graph[r][i] == 'O'){
                    break;
                }
                if (graph[r][i] == 'T') {
                    int max = 0;
                    int maxIdx = i;
                    for (int j = c + 1; j < i; j++) {
                        if (max < obstacles[r][j]) {
                            maxIdx = j;
                            max = obstacles[r][j];
                        }
                        obstacles[r][j] = 0;
                    }
                    graph[r][maxIdx] = 'O';
                    break;
                }
            }

//            for (int i = 0; i < N; i++) {
//                System.out.println(Arrays.toString(graph[i]));
//            }
//            System.out.println("++++++++++++");
//            for (int i = 0; i < N; i++) {
//                System.out.println(Arrays.toString(obstacles[i]));
//            }
//            System.out.println("++++++++++++");
        }
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 'O') cnt++;
            }
        }
        return cnt <= 3;
    }

    static boolean nearT(Node student) {
        for (int i = 0; i < dir.length; i++) {
            int nr = student.r + dir[i][0];
            int nc = student.c + dir[i][1];
            if (inGraph(nr, nc) && graph[nr][nc]=='T') {
                return true;
            }
        }
        return false;
    }
}
/**

 4
 X S X T
 X X S X
 X X X X
 T T T X

 5
 X X S X X
 X X X X X
 S X T X S
 X X X X X
 X X S X X


 5
 X T X T X
 T X S X T
 X S S S X
 T X S X X
 X T X X X

 5
 X S S S X
 T X X S X
 X T X S X
 X X T X S
 X X X T X
 */