import java.util.*;
import java.io.*;
public class boj11559 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static boolean[][] visited;
    static int H = 12, W = 6;
    static char[][] graph = new char[H][W];
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < H; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int pop = 0;
        boolean flag = true;
        while(flag) {
            flag = false;
            visited = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (graph[i][j] != '.' && !visited[i][j]) {
                        if (isPop(i, j)) {
                            flag = true;
                        }
                    }
                }
            }
            if (flag) {
                pop += 1;
                for (int i = 0; i < W; i++) {
                    for (int j = H-1; j >= 0; j--) {
                        if (graph[j][i] == '.') {
                            int idx = j;
                            while (idx >= 0 && graph[idx][i] == '.') {
                                idx--;
                            }
                            if (idx != -1) {
                                for (int k = idx; k >= 0 ; k--){
                                    graph[k+ (j-idx)][i] = graph[k][i];
                                    graph[k][i] = '.';
                                }
                            }
                        }
                    }
                }
            }
        }
        return pop;

    }

    static boolean isPop(int r, int c) {
        char type = graph[r][c];
        ArrayDeque<Node> que = new ArrayDeque();
        ArrayList<Node> pop = new ArrayList();
        que.add(new Node(r, c));
        visited[r][c] = true;
        while (!que.isEmpty()) {
            int qsize = que.size();
            for (int i = 0; i < qsize; i++) {
                Node poll = que.poll();
                pop.add(poll);
                for (int j = 0; j < 4; j++) {
                    int nr = poll.r + dir[j][0];
                    int nc = poll.c + dir[j][1];
                    if (inGraph(nr, nc) && !visited[nr][nc] && graph[nr][nc] == type) {
                        visited[nr][nc] = true;
                        que.add(new Node(nr, nc));
                    }
                }
            }
        }
        if (pop.size() >= 4) {
            for (Node node : pop) {
                graph[node.r][node.c] = '.';
            }
            return true;
        }
        return false;
    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < H && 0 <= c && c < W;
    }
}