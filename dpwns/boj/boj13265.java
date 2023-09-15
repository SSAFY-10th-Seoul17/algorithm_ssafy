import java.io.*;
import java.util.*;

public class Main {
    public static int[] color = new int[1001];
    public static int[][] map = new int[1001][1001];
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        Loop:
        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            for (int i = 1; i <= n; i++) {
                Arrays.fill(map[i], 0);
            }
            Arrays.fill(color, -1);
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
                map[a][b] = 1;
                map[b][a] = 1;
            }
            for (int i = 1; i <= n; i++) {
                if(color[i] == -1 && !isPaintColor(i, 1)) {
                    sb.append("impossible\n");
                    continue Loop;
                }
            }
            sb.append("possible\n");
        }
        System.out.print(sb);
        br.close();
    }

    public static boolean isPaintColor(int node, int c) {
        color[node] = c;
        boolean isPaint = true;
        for (int i = 1; i <= n; i++) {
            if(map[node][i] == 1) {
                if(color[i] == color[node]) return false;
                if(color[i] == -1) isPaint = isPaintColor(i, 1 - color[node]);
            }
        }
        return isPaint;
    }
}
