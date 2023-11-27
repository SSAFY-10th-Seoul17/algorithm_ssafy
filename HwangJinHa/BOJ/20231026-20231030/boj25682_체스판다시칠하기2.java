package study10월5주차월;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj25682_체스판다시칠하기2 {
    static int n, m, k;
    static char[][] chess;
    static int[][] sum;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        chess = new char[n][];
        sum = new int[n][m];
        for (int i = 0; i < n; i++) {
            chess[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                sum[i][j] = ((i + j)%2 == 0 ? 'B' : 'W') == chess[i][j]? 0 : 1;
                setSum(i, j);
            }
        }
        int ans = k * k;
        int size = ans;
        for (int i = k; i <= n; i++) {
            for (int j = k; j <= m; j++) {
                int tmp = getSum(i, j);
                int tmpreversed = size - tmp;
                if (tmp < ans) {
                    ans = tmp;
                }
                if (tmpreversed < ans) {
                    ans = tmpreversed;
                }
            }
        }
        System.out.println(ans);
    }
    static void setSum(int y, int x) {
        if (y == 0 && x == 0) {
            return;
        }
        else if (y == 0) {
            sum[y][x] = sum[y][x-1] + sum[y][x];
        }
        else if (x == 0) {
            sum[y][x] = sum[y-1][x] + sum[y][x];
        }
        else {
            sum[y][x] = sum[y-1][x] + sum[y][x-1] - sum[y-1][x-1] + sum[y][x];
        }
    }

    static int getSum(int y, int x) {
        y-=1;
        x-=1;
        int ans = sum[y][x];
        boolean ydone = false;
        boolean xdone = false;
        if (y-k >= 0) {
            ans -= sum[y - k][x];
            ydone = true;
        }
        if (x-k >= 0) {
            ans -= sum[y][x-k];
            xdone = true;
        }
        if (ydone && xdone)
            ans += sum[y-k][x-k];
        return ans;
    }
}