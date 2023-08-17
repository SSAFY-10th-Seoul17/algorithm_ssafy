import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj6593 {

    static final int[] dx = {1, 0, -1, 0, 0, 0};
    static final int[] dy = {0, -1, 0, 1, 0, 0};
    static final int[] dz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[] start = new int[3];
            int[] end = new int[3];

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            Character[][][] map = new Character[L][R][C];
            boolean[][][] visited = new boolean[L][R][C];
            for (int l = 0; l < L; l++) {

                for (int r = 0; r < R; r++) {
                    String input = br.readLine();

                    for (int c = 0; c < C; c++) {
                        map[l][r][c] = input.charAt(c);
                        if (map[l][r][c] == 'S') {
                            start = new int[]{l, r, c};
                        } else if (map[l][r][c] == 'E') {
                            end = new int[]{l, r, c};
                        } else if (map[l][r][c] == '#') {
                            visited[l][r][c] = true;
                        }
                    }
                }
                br.readLine();
            }

            int result = solve(visited, start, end, L, R, C);
            if(result == -1){
                System.out.println("Trapped!");
            }else{
                System.out.println("Escaped in "+ result +" minute(s).");
            }

        }
    }

    public static int solve(boolean[][][] visited, int[] start, int[] end, int L, int R, int C) {

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start[0], start[1], start[2], 0));
        visited[start[0]][start[1]][start[2]] = true;

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();

            if (curNode.l == end[0] && curNode.r == end[1] && curNode.c == end[2]) {

                return curNode.time;
            }

            for (int i = 0; i < 6; i++) {
                int nextL = curNode.l + dz[i];
                int nextR = curNode.r + dy[i];
                int nextC = curNode.c + dx[i];

                if (nextL < 0 || nextR < 0 || nextC < 0 || nextL >= L || nextR >= R || nextC >= C
                        || visited[nextL][nextR][nextC]) {
                    continue;
                }
                visited[nextL][nextR][nextC] = true;

                queue.offer(new Node(nextL, nextR, nextC, curNode.time + 1));
            }

        }
        return -1;

    }

    private static class Node {
        int l;
        int r;
        int c;
        int time;

        public Node(int l, int r, int c, int time) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
}
