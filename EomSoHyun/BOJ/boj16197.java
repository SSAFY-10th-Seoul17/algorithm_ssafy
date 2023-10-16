import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] map;
    static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        q = new LinkedList<int[]>();
        int[] coin = new int[5];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'o') {
                    coin[idx++] = i;
                    coin[idx++] = j;
                }
            }
        }
        coin[4] = 0;
        q.offer(coin);
        System.out.println(bfs());



    }

    public static int bfs() {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};


        while (true) {
            int[] xy = q.poll();
            int cnt = xy[4];
            int[] coin1 = new int[] {xy[0], xy[1]};
            int[] coin2 = new int[] {xy[2], xy[3]};

            if (cnt++ == 10) break;

            for (int i = 0; i < 4; i++) {
                int x1 = coin1[0] + dx[i];
                int x2 = coin2[0] + dx[i];
                int y1 = coin1[1] + dy[i];
                int y2 = coin2[1] + dy[i];

                if (!(0 <= x1 && x1 < n && 0 <= y1 && y1 < m)) {
                    if (!(0 <= x2 && x2 < n && 0 <= y2 && y2 < m)) {
                        // 둘 다 떨어짐
                        continue;
                    }
                    else {
                        // coin1 떨어짐
                        return cnt;
                    }
                }
                else if (!(0 <= x2 && x2 < n && 0 <= y2 && y2 < m)) {
                    // coin2 떨어짐
                    return cnt;
                }
                else {
                    if (map[x1][y1] == '#') {
                        x1 = coin1[0];
                        y1 = coin1[1];
                    }
                    if (map[x2][y2] == '#') {
                        x2 = coin2[0];
                        y2 = coin2[1];
                    }
                    q.offer(new int[] {x1, y1, x2, y2, cnt});
                }

            }
        }

        return -1;

    }

}
