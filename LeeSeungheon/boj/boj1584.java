import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj1584 {

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};
    static int[][] map;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        map = new int[501][501];
        visited = new boolean[501][501];
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int X1 = Integer.parseInt(st.nextToken());
            int Y1 = Integer.parseInt(st.nextToken());
            int X2 = Integer.parseInt(st.nextToken());
            int Y2 = Integer.parseInt(st.nextToken());

            for (int row = Math.min(Y1, Y2); row <= Math.max(Y1, Y2); row++) {
                Arrays.fill(map[row], Math.min(X1, X2), Math.max(X1, X2) + 1, 1);
            }

        }

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int X1 = Integer.parseInt(st.nextToken());
            int Y1 = Integer.parseInt(st.nextToken());
            int X2 = Integer.parseInt(st.nextToken());
            int Y2 = Integer.parseInt(st.nextToken());

            for (int row = Math.min(Y1, Y2); row <= Math.max(Y1, Y2); row++) {
                Arrays.fill(visited[row], Math.min(X1, X2), Math.max(X1, X2) + 1 , true);
            }
        }
        visited[0][0] = false;

        System.out.println(solve());
    }

    private static int solve() {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));

        while (!pq.isEmpty()) {

            Node curNode = pq.poll();

            if (visited[curNode.row][curNode.column]) {
                continue;
            }
            visited[curNode.row][curNode.column] = true;

            if (curNode.row == 500 && curNode.column == 500) {
                return curNode.life;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = curNode.row + dy[i];
                int nextColumn = curNode.column + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextColumn > 500 || nextRow > 500
                        || visited[nextRow][nextColumn]) {
                    continue;
                }
                pq.offer(new Node(nextRow, nextColumn , curNode.life + map[nextRow][nextColumn]));
            }
        }
        return -1;
    }


    private static class Node implements Comparable<Node> {

        int row;
        int column;
        int life;

        public Node(int row, int column, int life) {
            this.row = row;
            this.column = column;
            this.life = life;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(life, o.life);
        }
    }
}
