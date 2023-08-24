import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj3184 {

    static class Node{
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static int R, C, W = 0, S = 0;
    static int[][] graph;
    static boolean[][] visited;
    static ArrayList<Node> wolves, sheep;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                graph[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if ((graph[i][j] == 'o' || graph[i][j] == 'v') && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        System.out.println(S + " " + W);
    }


    static void bfs(int r, int c) {
        Queue<Node> que = new ArrayDeque<>();
        que.add(new Node(r, c));
        visited[r][c] = true;
        int s = 0;
        int w = 0;
        if (graph[r][c] == 'o') {
            s += 1;
        } else {
            w += 1;
        }
        while (!que.isEmpty()) {
            Node poll = que.poll();
            for (int i = 0; i < 4; i++) {
                int nr = poll.r + dr[i];
                int nc = poll.c + dc[i];
                if (inGraph(nr, nc) && graph[nr][nc] != '#'&& !visited[nr][nc]) {
                    if(graph[nr][nc] == 'v'){
                        w +=1;
                    } else if(graph[nr][nc] == 'o'){
                        s += 1;
                    }
                    visited[nr][nc] = true;
                    que.add(new Node(nr, nc));
                }
            }
        }
        if (s > w) {
            S += s;
        } else {
            W += w;
        }
    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
