package study10월4주차월;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj10159_저울 {
    static int n, m;

    static int[][] forward;
    static int[][] backward;
    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        forward = new int[n][n];
        backward = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(forward[i], INF);
            Arrays.fill(backward[i], INF);
            forward[i][i] = 0;
            backward[i][i] = 0;
        }


        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            forward[a][b] = 1;
            backward[b][a] = 1;
        }

        for (int via = 0; via < n; via++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    forward[i][j] = Math.min(forward[i][j], forward[i][via] + forward[via][j]);
                    backward[i][j] = Math.min(backward[i][j], backward[i][via] + backward[via][j]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (forward[i][j] == INF && backward[i][j] == INF)
                    cnt++;
            }
            System.out.println(cnt);
        }
    }
}
