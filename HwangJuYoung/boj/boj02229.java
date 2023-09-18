package BOJ;

import java.util.*;
import java.io.*;

public class boj02229 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] score = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        int[] memo = new int[N];

        memo[0] = 0;
        memo[1] = Math.abs(score[1] - score[0]);

        for (int i = 2; i < N; i++) {

            for (int j = i; j >= 0; j--) {
                int max = 0;
                int min = Integer.MAX_VALUE;

                for (int k = j; k <= i; k++) {
                    if (max < score[k]) {
                        max = score[k];
                    }
                    if (min > score[k]) {
                        min = score[k];
                    }
                }

                if (j == 0) {
                    memo[i] = Math.max(memo[i], max - min);
                } else {
                    memo[i] = Math.max(memo[i], max - min + memo[j - 1]);
                }
            }
        }
        System.out.println(memo[N - 1]);
    } // end of main
} // end of class