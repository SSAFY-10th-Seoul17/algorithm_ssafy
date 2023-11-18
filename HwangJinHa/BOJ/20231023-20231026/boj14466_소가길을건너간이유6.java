package study10월4주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj14466_소가길을건너간이유6 {
    static int n, k, r;
    static HashSet[][] connections;
    static boolean[][] cowMap;
    static boolean[][] visited;

    static int dy[] = {0, 1, 0, -1};
    static int dx[] = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        connections = new HashSet[n][n];
        cowMap = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                connections[i][j] = new HashSet<Integer>();
            }
        }

        int a, b, c, d;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            d = Integer.parseInt(st.nextToken()) - 1;

            if (b + 1 == d){
                connections[a][b].add(0);
                connections[c][d].add(2);
            } else if (b - 1 == d) {
                connections[a][b].add(2);
                connections[c][d].add(0);
            } else if (a + 1 == c) {
                connections[a][b].add(1);
                connections[c][d].add(3);
            } else {
                connections[a][b].add(3);
                connections[c][d].add(1);
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            cowMap[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1]
            = true;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cowMap[i][j]) {
                    visited = new boolean[n][n];
                    ans += calc(i, j);
                }
            }
        }
        System.out.println((k * (k-1) - ans)/2);
    }

    // 연결된 쌍의 수 반환
    private static int calc(int i, int j) {
        Queue<int[]> loc = new LinkedList<>();
        loc.add(new int[]{i, j});
        visited[i][j] = true;

        int cnt = 0;
        while (!loc.isEmpty()) {
            int[] now = loc.poll();
            int y = now[0];
            int x = now[1];
            for (int d = 0; d < 4; d++) {
                if (connections[y][x].contains(d))
                    continue;
                int yy = y + dy[d];
                int xx = x + dx[d];
                if (yy < 0 || n <= yy || xx < 0 || n <= xx || visited[yy][xx])
                    continue;
                visited[yy][xx] = true;
                loc.add(new int[]{yy, xx});
                if (cowMap[yy][xx])
                    cnt++;
            }
        }
        return cnt;
    }
}