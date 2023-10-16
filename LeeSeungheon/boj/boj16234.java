import java.io.*;
import java.util.*;

public class boj16234 {

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());

            for (int column = 0; column < N; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve(map, L, R));
    }

    private static int solve(int[][] map, int l, int r) {

        int[][] sorted = new int[map.length][map.length];
        ArrayList<Integer> avg = new ArrayList<>();
        int day = 0;
        boolean IsChange = true;

        while (IsChange) {
            IsChange = false;
            int sort = 1;
            avg.clear();

            for (int row = 0; row < map.length; row++) {
                Arrays.fill(sorted[row], 0);
            }

            for (int row = 0; row < map.length; row++) {
                for (int column = 0; column < map.length; column++) {

                    if (sorted[row][column] != 0) {
                        continue;
                    }

                    Queue<Node> queue = new ArrayDeque<>();
                    queue.offer(new Node(row, column));

                    int sum = map[row][column];
                    int count = 0;

                    while (!queue.isEmpty()) {
                        Node curNode = queue.poll();

                        sorted[curNode.row][curNode.column] = sort;
                        count++;

                        for (int i = 0; i < 4; i++) {
                            int nextRow = curNode.row + dy[i];
                            int nextColumn = curNode.column + dx[i];

                            if (nextRow >= map.length || nextColumn >= map.length || nextRow < 0 || nextColumn < 0
                                    || sorted[nextRow][nextColumn] != 0) {
                                continue;
                            }
                            int diff = Math.abs(map[curNode.row][curNode.column] - map[nextRow][nextColumn]);

                            if (diff < l || diff > r) {
                                continue;
                            }
                            sorted[nextRow][nextColumn] = sort;
                            sum += map[nextRow][nextColumn];
                            queue.offer(new Node(nextRow, nextColumn));
                        }
                    }

                    avg.add(sum / (count));
                    sort++;
                }
            }


            for (int row = 0; row < map.length; row++) {
                for (int column = 0; column < map.length; column++) {
                    if (sorted[row][column] == 0 || map[row][column] == avg.get(sorted[row][column] - 1)) {
                        continue;
                    }
                    IsChange = true;
                    map[row][column] = avg.get(sorted[row][column] - 1);
                }
            }
            day++;

        }

        return --day;
    }

    private static class Node {

        int row;
        int column;

        public Node(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }


}
