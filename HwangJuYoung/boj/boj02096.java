package BOJ;

import java.util.*;
import java.io.*;

public class boj02096 {
    private static int N;
    private static int[][] memoMax, memoMin, map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        memoMax = new int[N+1][3];
        memoMin = new int[N+1][3];

        for (int i = 1; i < N+1; i++) {
            Arrays.fill(memoMin[i], 900000);
        }


        map = new int[N+1][3];

        for (int i = 1; i < N+1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                if (j > 0) {
                    memoMax[i+1][j-1] = Math.max(memoMax[i+1][j-1], memoMax[i][j] + map[i+1][j-1]);
                    memoMin[i+1][j-1] = Math.min(memoMin[i+1][j-1], memoMin[i][j] + map[i+1][j-1]);
                }
                if (j < 2) {
                    memoMax[i+1][j+1] = Math.max(memoMax[i+1][j+1], memoMax[i][j] + map[i+1][j+1]);
                    memoMin[i+1][j+1] = Math.min(memoMin[i+1][j+1], memoMin[i][j] + map[i+1][j+1]);
                }
                memoMax[i+1][j] = Math.max(memoMax[i+1][j], memoMax[i][j] + map[i+1][j]);
                memoMin[i+1][j] = Math.min(memoMin[i+1][j], memoMin[i][j] + map[i+1][j]);
            }
        }

        int max = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(memoMax[N][i], max);
            min = Math.min(memoMin[N][i], min);
        }
        System.out.println(max + " " + min);
    } // end of main
} // end of class