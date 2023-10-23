import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj5427 {

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            st =   new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());


            Character[][] map = new Character[N][M];
            int[] start = new int[2];
            for (int row = 0; row < N; row++) {
                String input = br.readLine();

                for (int column = 0; column < M; column++) {
                    map[row][column] = input.charAt(column);

                    if (map[row][column] == '@') {
                        start = new int[]{row, column};
                    }
                }
            }

            int result = solve(map, start);

            if (result == -1) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(result);
            }

        }
    }


    private static int solve(Character[][] map, int[] start) {
        Queue<Node> que = new LinkedList<>();
        int[][] visited = new int[map.length][map[0].length];

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                if (map[row][column] == '*') {
                    que.offer(new Node(row, column, 1));
                }
            }
        }

        while (!que.isEmpty()){

            Node curNode = que.poll();

            if(visited[curNode.row][curNode.column] != 0){
                continue;
            }
            visited[curNode.row][curNode.column] = curNode.count;

            for (int i = 0; i < 4; i++) {

                int nextRow = curNode.row + dy[i];
                int nextColumn = curNode.column + dx[i];

                if (nextRow < 0 || nextColumn < 0 || nextRow >= visited.length || nextColumn >= visited[0].length) {
                    continue;
                }

                if (map[nextRow][nextColumn] == '#') {
                    continue;
                }

                que.offer(new Node(nextRow, nextColumn, curNode.count + 1));
            }
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start[0], start[1], 1));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();

            if (visited[curNode.row][curNode.column] != 0 && visited[curNode.row][curNode.column] <= curNode.count) {
                continue;
            }
            visited[curNode.row][curNode.column] = curNode.count;

            if(curNode.row == map.length-1 || curNode.row == 0 || curNode.column == map[0].length-1 || curNode.column == 0){
                return curNode.count;
            }

            for (int i = 0; i < 4; i++) {

                int nextRow = curNode.row + dy[i];
                int nextColumn = curNode.column + dx[i];


                if (map[nextRow][nextColumn] == '*' || map[nextRow][nextColumn] == '#') {
                    continue;
                }

                queue.offer(new Node(nextRow, nextColumn, curNode.count + 1));
            }

        }

        return -1;

    }

    private static class Node {

        int row;
        int column;
        int count;

        public Node(int row, int column, int count) {
            this.row = row;
            this.column = column;
            this.count = count;
        }
    }
}
