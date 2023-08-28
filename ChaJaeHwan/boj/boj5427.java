import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj5427 {
    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int T;
    static int W, H;
    static char[][] graph;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static Node start;
    static Queue<Node> fire;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            graph = new char[H][W];
            fire = new ArrayDeque<>();

            for (int i = 0; i < H; i++) {
                String nextLine = br.readLine();
                for (int j = 0; j < W; j++) {
                    if (nextLine.charAt(j) == '@') {
                        start = new Node(i, j);
                    } else if (nextLine.charAt(j) == '*') {
                        fire.add(new Node(i, j));
                    }
                    graph[i][j] = nextLine.charAt(j);
                }
            }

            int answer = bfs();
            if (answer == 0) {
                sb.append("IMPOSSIBLE").append("\n");
            } else {
                sb.append(answer).append("\n");
            }
        }

        System.out.println(sb);


    }


    static int bfs() {
        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node(start.r, start.c));
        graph[start.r][start.c] = '#';
        int cnt = 0;
        while (!que.isEmpty()) {
            //불
            int fsize = fire.size();
            for (int i = 0; i < fsize; i++) {
                Node pop = fire.poll();
                int fr = pop.r;
                int fc = pop.c;

                for (int j = 0; j < 4; j++) {
                    int nfr = fr + dx[j];
                    int nfc = fc + dy[j];

                    if (inGraph(nfr, nfc) && (graph[nfr][nfc] == '@' || graph[nfr][nfc] == '.')) {
                        fire.add(new Node(nfr, nfc));
                        graph[nfr][nfc] = '*';
                    }
                }
            }

            cnt += 1;
            int qsize = que.size();

            for (int i = 0; i < qsize; i++) {
                Node pop = que.remove();
                for (int j = 0; j < 4; j++) {
                    int nr = pop.r + dx[j];
                    int nc = pop.c + dy[j];
                    if (!inGraph(nr, nc)) {
                        return cnt;
                    }else if (inGraph(nr, nc) && graph[nr][nc] == '.') {
                        que.add(new Node(nr, nc));
                        graph[nr][nc] = '#';
                    }
                }
            }

        }
        return 0;
    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < H && 0 <= c && c < W;
    }

    static void print() {
        for (int i = 0; i < H; i++) {
            System.out.println(graph[i]);
        }
    }
}
