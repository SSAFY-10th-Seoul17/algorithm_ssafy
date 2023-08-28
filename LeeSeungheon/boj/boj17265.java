import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class boj17265 {

    static final int[] dx = {1, 0};
    static final int[] dy = {0, 1};
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0, index = 0; j < N; j++, index += 2) {
                map[i][j] = input.charAt(index);
            }
        }

        solve(map);
        System.out.println(max + " " + min);
    }

    private static void solve(char[][] map) {

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(0, 0, map[0][0] - '0'));

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if (curNode.row == map.length - 1 && curNode.column == map.length - 1) {
                max = Math.max(max, curNode.result);
                min = Math.min(min , curNode.result);
                continue;
            }

            for (int i = 0; i < 2; i++) {

                int nextGihoRow = curNode.row + dy[i];
                int nextGihoColumn = curNode.column + dx[i];

                if (nextGihoRow < 0 || nextGihoColumn < 0 || nextGihoRow >= map.length || nextGihoColumn >= map.length) {
                    continue;
                }

                char giho = map[nextGihoRow][nextGihoColumn];

                for (int j = 0; j < 2; j++) {
                    int nextRow = nextGihoRow + dy[j];
                    int nextColumn = nextGihoColumn + dx[j];

                    if (nextRow < 0 || nextColumn < 0 || nextRow >= map.length || nextColumn >= map.length) {
                        continue;
                    }


                    if (giho == '*') {
                        queue.offer(new Node(nextRow, nextColumn, curNode.result * (map[nextRow][nextColumn] - '0')));
                    } else if (giho == '+') {
                        queue.offer(new Node(nextRow, nextColumn, curNode.result + (map[nextRow][nextColumn] - '0')));
                    } else { // '-'
                        queue.offer(new Node(nextRow, nextColumn, curNode.result - (map[nextRow][nextColumn] - '0')));
                    }
                }
            }
        }
    }


    private static class Node {
        int row;
        int column;
        int result;

        public Node(int row, int column, int result) {
            this.row = row;
            this.column = column;
            this.result = result;
        }
    }
}
