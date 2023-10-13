package BOJ;

import java.util.*;
import java.io.*;

public class boj16198 {
    private static int N;
    private static int maxEnergy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        maxEnergy = 0;

        for (int i = 1; i < N - 1; i++) {
            Energy(arr, i, N, 0);
        }
        System.out.println(maxEnergy);
    } // end of main

    private static void Energy(int[] arr, int idx, int len, int curE) {
        if (len == 2) {
            maxEnergy = Math.max(maxEnergy, curE);
            return;
        }
        if (idx == len - 1) return;
        int[] tmp;
        tmp = Arrays.copyOf(arr, len);

        curE += tmp[idx - 1] * tmp[idx + 1];

        for (int i = idx; i < len - 1; i++) {
            tmp[i] = tmp[i + 1];
        }

        for (int i = 1; i < len - 1; i++) {
            Energy(tmp, i, len - 1, curE);
        }
    }
} // end of class