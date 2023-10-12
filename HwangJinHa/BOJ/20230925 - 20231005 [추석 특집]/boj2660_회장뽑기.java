package study10월1주차목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2660_회장뽑기 {
    static int n;
    static int[][] adj;
    static final int INF = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        adj = new int [n + 1][n + 1];
        for (int i = 1; i <= n; i++)
            Arrays.fill(adj[i], INF);
        for (int i = 1; i <= n; i++)
            adj[i][i] = 0;

        int a, b;
        while (true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if (a == -1 && b == -1)
                break;

            adj[a][b] = 1;
            adj[b][a] = 1;
        }

        for (int via = 1; via <= n; via++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    adj[i][j] = Math.min(adj[i][j], adj[i][via] + adj[via][j]);
                }
            }
        }

        int leastLen = INF;
        ArrayList<Integer> ansArr = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = 1; j <= n; j++) {
                if (adj[i][j] == INF)
                    continue;
                max = (adj[i][j] > max)? adj[i][j] : max;
            }
            if (max < leastLen) {
                leastLen = max;
                ansArr.clear();
                ansArr.add(i);
            }
            else if (max == leastLen) {
                ansArr.add(i);
            }
        }

        System.out.println(leastLen + " " +ansArr.size());
        for (int i = 0; i < ansArr.size(); i++) {
            System.out.print(ansArr.get(i) + " ");
        }


    }
}