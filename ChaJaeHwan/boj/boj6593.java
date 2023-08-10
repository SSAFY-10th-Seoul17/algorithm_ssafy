import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj6593 {

    static class Node {
        int l, r, c;

        public Node(int l, int r, int c) {
            this.l = l;
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "l=" + l +
                    ", r=" + r +
                    ", c=" + c +
                    '}';
        }
    }

    static int L, R, C;
    static char[][][] graph;
    static boolean[][][] visited;

    static Node start;

    //
    static int[] dl = {0, 0, 0, 0, -1, 1};
    static int[] dr = {0, -1, 0, 1, 0, 0};
    static int[] dc = {-1, 0, 1, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 & C == 0) {
                break;
            }
            graph = new char[L][R][C];
            for (int l = 0; l < L; l++) {
                for (int r = 0; r < R; r++) {
                    String s = br.readLine();
                    for (int c = 0; c < C; c++) {
                        char ch = s.charAt(c);
                        if (ch == 'S') {
                            start = new Node(l, r, c);
                        }
                        graph[l][r][c] = ch;
                    }
                }
                br.readLine();
            }
            int x = bfs();
            if (x == 0) {
                sb.append("Trapped!").append("\n");
            } else {
                sb.append("Escaped in ").append(x).append(" minute(s).").append("\n");
            }
        }
        System.out.println(sb);

    }

    static int bfs() {
        Queue<Node> que = new ArrayDeque<>();
        que.add(start);
        graph[start.l][start.r][start.c] = '#';
        int cnt = 0;
        while (!que.isEmpty()) {
            cnt += 1;
            int qsize = que.size();
            for (int i = 0; i < qsize; i++) {
                Node pop = que.remove();
                for (int j = 0; j < 6; j++) {
                    int nl = pop.l + dl[j];
                    int nr = pop.r + dr[j];
                    int nc = pop.c + dc[j];
                    if (inGraph(nl, nr, nc)) {
                        if (graph[nl][nr][nc] == 'E') {
                            return cnt;
                        } else if (graph[nl][nr][nc] == '.') {
                            que.add(new Node(nl, nr, nc));
                            graph[nl][nr][nc] = '#';
                        }
                    }
                }
            }
        }
        return 0;
    }

    static boolean inGraph(int l, int r, int c) {
        return 0 <= l && l < L && 0 <= r && r < R && 0 <= c && c < C;
    }

}
