import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj16234 {

    static int N, L, R;
    static int[][] graph;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static boolean[][] visited;

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int cnt = 0;
        while (isMovable()) {
            cnt += 1;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }
        }


        System.out.println(cnt);

    }

    static void bfs(int r, int c) {
        ArrayDeque<Node> que = new ArrayDeque<>();
        ArrayList<Node> list = new ArrayList<>();
        que.add(new Node(r, c));
        list.add(new Node(r, c));
        visited[r][c] = true;
        int sum = graph[r][c];
        int cnt = 1;
        while (!que.isEmpty()) {
            Node poll = que.poll();
            for (int i = 0; i < 4; i++) {
                int nr = poll.r + dir[i][0];
                int nc = poll.c + dir[i][1];
                if (inGraph(nr, nc) && !visited[nr][nc] && inRange(graph[poll.r][poll.c], graph[nr][nc])) {
                    que.add(new Node(nr, nc));
                    list.add(new Node(nr, nc));
                    cnt += 1;
                    visited[nr][nc] = true;
                    sum += graph[nr][nc];
                }
            }
        }
        for (Node node : list) {
            graph[node.r][node.c] = sum / cnt;
        }
    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static boolean inRange(int val1, int val2) {
        int abs = Math.abs(val1 - val2);
        return L <= abs && abs <= R;
    }

    static boolean isMovable() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int p = graph[i][j];
                for (int k = 0; k < 4; k++) {
                    int nr = i + dir[k][0];
                    int nc = j + dir[k][1];
                    if (inGraph(nr, nc) && inRange(graph[i][j], graph[nr][nc])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}
