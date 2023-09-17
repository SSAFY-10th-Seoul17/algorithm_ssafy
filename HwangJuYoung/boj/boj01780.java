package BOJ;

import java.util.*;
import java.io.*;

public class boj01780 {
    private static int N;
    private static int minusCnt, zeroCnt, plusCnt;
    private static int[][] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        minusCnt = 0;
        zeroCnt = 0;
        plusCnt = 0;

        paper = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cut(0, 0, N);

        StringBuilder sb = new StringBuilder();
        sb.append(minusCnt).append("\n").append(zeroCnt).append("\n")
                .append(plusCnt);
        System.out.println(sb.toString());
    } // end of main

    private static void cut(int x, int y, int n) {
        if (correctAll(x, y, n)) {
            switch (paper[x][y]) {
                case -1:
                    minusCnt++;
                    break;
                case 0:
                    zeroCnt++;
                    break;
                case 1:
                    plusCnt++;
                    break;
            }
            return;
        }
        n /= 3;
        cut(x, y, n);
        cut(x, y+n, n);
        cut(x, y+2*n, n);
        cut(x+n, y, n);
        cut(x+n, y+n, n);
        cut(x+n, y+2*n, n);
        cut(x+2*n, y, n);
        cut(x+2*n, y+n, n);
        cut(x+2*n, y+2*n, n);
    }

    private static boolean correctAll(int x, int y, int n) {
        int first = paper[x][y];
        for (int i = x; i < x + n; i++) {
            for (int j = y; j < y+ n; j++) {
                if (paper[i][j] != first) {
                    return false;
                }
            }
        }
        return true;
    }
} // end of class