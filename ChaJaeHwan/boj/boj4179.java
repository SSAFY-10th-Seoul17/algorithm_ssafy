import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj4179 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C;
    static char[][] graph;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static ArrayDeque<Node> fire = new ArrayDeque<>();
    static Node start, fireStart;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new char[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                char ch = str.charAt(j);
                graph[i][j] = ch;
                if (ch == 'J') {
                    start = new Node(i, j);
                } else if (ch == 'F') {
                    fire.add(new Node(i, j));
                }
            }
        }
//        for (int i = 0; i < R; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }
//        System.out.println("+=======");
        int answer = dfs();
        if (answer == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer);
        }
    }

    static int dfs() {
        ArrayDeque<Node> jihoon = new ArrayDeque<>();
        jihoon.add(start);

        int answer = 0;
        while (!jihoon.isEmpty()) {
            answer += 1;
            int jSize = jihoon.size();

            //불이동
            int fSize = fire.size();
            for (int i = 0; i < fSize; i++) {
                Node poll = fire.poll();
                for (int j = 0; j < 4; j++) {
                    int nr = poll.r + dir[j][0];
                    int nc = poll.c + dir[j][1];
                    if (inGraph(nr, nc) && (graph[nr][nc] == '.' || graph[nr][nc] == 'J')) {
                        graph[nr][nc] = 'F';
                        fire.add(new Node(nr, nc));
                    }
                }
            }

            //지훈
            for (int i = 0; i < jSize; i++) {
                Node poll = jihoon.poll();
                for (int j = 0; j < 4; j++) {
                    int nr = poll.r + dir[j][0];
                    int nc = poll.c + dir[j][1];
                    if (inGraph(nr, nc) && graph[nr][nc] == '.') {
                        graph[nr][nc] = 'J';
                        jihoon.add(new Node(nr, nc));
                    } else if (!inGraph(nr, nc)) {
                        return answer;
                    }
                }
            }

//            for (int i = 0; i < R; i++) {
//                System.out.println(Arrays.toString(graph[i]));
//            }
//            System.out.println("+=======");

        }


        return -1;

    }

    public static boolean inGraph(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    static boolean isEdge(int r, int c) {
        return (r == 0 || r == R - 1) && (c == 0 || c == C - 1);
    }
}
