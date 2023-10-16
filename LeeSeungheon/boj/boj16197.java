import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj16197 {

    static final int[] dx = {1, 0, -1, 0};
    static final int[] dy = {0, 1, 0, -1};
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        List<Coin> coin = new ArrayList<>(2);

        for (int row = 0; row < N; row++) {
            map[row] = br.readLine().toCharArray();

            if (!Arrays.toString(map[row]).contains("o")) {
                continue;
            }

            for (int column = 0; column < M; column++) {
                if (map[row][column] != 'o') {
                    continue;
                }
                coin.add(new Coin(row, column));
            }
        }
        System.out.println(solve(coin));
    }

    private static int solve(List<Coin> coin) {

        Queue<Coins> queue = new ArrayDeque<>();
        queue.offer(new Coins(coin.get(0), coin.get(1), 0));
        boolean[][][][] visited = new boolean[map.length][map[0].length][map.length][map[0].length];

        while (!queue.isEmpty()) {
            Coins curCoins = queue.poll();
            Coin coin1 = curCoins.coin1;
            Coin coin2 = curCoins.coin2;

            if (curCoins.count >= 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow1 = coin1.row + dy[i];
                int nextColumn1 = coin1.column + dx[i];
                int nextRow2 = coin2.row + dy[i];
                int nextColumn2 = coin2.column + dx[i];

                if (isOutside(nextRow1, nextColumn1) ^ isOutside(nextRow2, nextColumn2)) {
                    return curCoins.count + 1;
                }

                if ( isOutside(nextRow1, nextColumn1) || visited[nextRow1][nextColumn1][nextRow2][nextColumn2] || map[nextRow1][nextColumn1] == '#' && map[nextRow2][nextColumn2] == '#') {
                    continue;
                }

                if (map[nextRow1][nextColumn1] == '#') {
                    nextRow1 = coin1.row;
                    nextColumn1 = coin1.column;
                } else if (map[nextRow2][nextColumn2] == '#') {
                    nextRow2 = coin2.row;
                    nextColumn2 = coin2.column;
                }
                visited[nextRow1][nextColumn1][nextRow2][nextColumn2] = true;
                queue.offer(new Coins(new Coin(nextRow1, nextColumn1), new Coin(nextRow2, nextColumn2), curCoins.count + 1));
            }
        }
        return -1;
    }

    private static boolean isOutside(int nextRow1, int nextColumn1) {
        return nextRow1 >= map.length || nextColumn1 >= map[0].length || nextRow1 < 0 || nextColumn1 < 0;
    }

    private static class Coins {
        Coin coin1;
        Coin coin2;
        int count;

        public Coins(Coin coin1, Coin coin2, int count) {
            this.coin1 = coin1;
            this.coin2 = coin2;
            this.count = count;
        }
    }

    private static class Coin {
        int row;
        int column;

        public Coin(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}
