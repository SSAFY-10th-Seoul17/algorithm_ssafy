import java.io.*;
import java.util.*;

public class Boj14466 {
    static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] roads = getRoads(br, n, r);
        int[][] visited = getVisitedInit(br, n, k);
        List<Integer> cows = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (visited[i][j] == 1) {
                    cows.add(dfs(roads, visited, i, j));
                }
            }
        }

        System.out.println(getPairs(cows));
    }

    private static int[][] getRoads(BufferedReader br, int n, int r) throws IOException {
        StringTokenizer st;
        int[][] roads = new int[n + 1][n + 1];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            roads[x1][y1] += 1 << getDir(x1, y1, x2, y2);
            roads[x2][y2] += 1 << getDir(x2, y2, x1, y1);
        }
        return roads;
    }

    private static int[][] getVisitedInit(BufferedReader br, int n, int k) throws IOException {
        StringTokenizer st;
        int[][] visited = new int[n + 2][n + 2];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            visited[x][y] = 1;
        }

        for (int i = 0; i < visited.length; i++) {
            visited[i][0] = visited[0][i] = -1;
            visited[i][n + 1] = visited[n + 1][i] = -1;
        }

        return visited;
    }

    private static int getPairs(List<Integer> cows) {
        int pairs = 0;
        for (int i = 0; i < cows.size(); i++) {
            for (int j = i + 1; j < cows.size(); j++) {
                pairs += cows.get(i) * cows.get(j);
            }
        }
        return pairs;
    }

    private static int dfs(int[][] roads, int[][] visited, int i, int j) {
        int count = visited[i][j];
        visited[i][j] = -1;

        for (int d = 0; d < dirs.length; d++) {
            int x = i + dirs[d][0];
            int y = j + dirs[d][1];

            if (visited[x][y] == -1 || (roads[i][j] & (1 << d)) != 0) {
                continue;
            }

            count += dfs(roads, visited, x, y);
        }

        return count;
    }

    private static int getDir(int x1, int y1, int x2, int y2) {
        for (int i = 0; i < dirs.length; i++) {
            int x = x1 + dirs[i][0];
            int y = y1 + dirs[i][1];
            if (x == x2 && y == y2) {
                return i;
            }
        }

        return -1;
    }

}

