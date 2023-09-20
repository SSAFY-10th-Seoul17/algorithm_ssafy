import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class No11559 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] map = new char[12][6];
        for (int i = 0; i < map.length; i++) {
            String str = br.readLine();
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        System.out.println(new Puyo(map).play());
    }

    static class Puyo {
        char[][] map;

        public Puyo(char[][] map) {
            this.map = map;
        }

        public int play() {
            int count = 0;

            while (isExploded()) {
                count++;
                setMap();
            }

            return count;
        }

        private void setMap() {

            for (int j = map[0].length - 1; j >= 0; j--) {
                int idx = map.length - 1;

                for (int i = map.length - 1; i >= 0; i--) {
                    if (map[i][j] != EMPTY) {
                        map[idx--][j] = map[i][j];
                    }
                }

                while (idx >= 0) {
                    map[idx][j] = EMPTY;
                    idx--;
                }
            }

        }

        private boolean isExploded() {
            boolean[][] visited = new boolean[map.length][map[0].length];
            boolean isExploded = false;

            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] != EMPTY && !visited[i][j]) {
                        int count = sameCount(visited, map[i][j], i, j);

                        if (count >= 4) {
                            remove(map[i][j], i, j);
                            isExploded = true;
                        }
                    }
                }
            }

            return isExploded;
        }

        private void remove(char color, int i, int j) {
            map[i][j] = EMPTY;

            for (int[] dir : dirs) {
                int x = dir[0] + i;
                int y = dir[1] + j;
                if (x < 0 || y < 0 || x >= map.length || y >= map[0].length || map[x][y] != color) {
                    continue;
                }

                remove(color, x, y);
            }
        }

        private int sameCount(boolean[][] visited, char color, int i, int j) {
            int count = 1;
            visited[i][j] = true;

            for (int[] dir : dirs) {
                int x = dir[0] + i;
                int y = dir[1] + j;
                if (x < 0 || y < 0 || x >= map.length || y >= map[0].length || map[x][y] != color || visited[x][y]) {
                    continue;
                }

                count += sameCount(visited, color, x, y);
            }

            return count;
        }
    }

    static final int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static final char EMPTY = '.';

}

