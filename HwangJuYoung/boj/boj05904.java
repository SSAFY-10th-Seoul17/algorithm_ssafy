package BOJ;

import java.io.*;

public class boj05904 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int K = 3, cnt = 1;
        while (true) {
            if (2 * K + (cnt+1) + 3 > N) break;
            K = 2 * K + (cnt++) + 3;
        }

        moo(K, cnt);

    } // end of main

    private static void moo(int k, int cnt) {
        if (N == k+1) {
            System.out.println('m');
            return;
        } else if (N > k + 1 && N <= k + cnt + 3) {
            System.out.println('o');
            return;
        } else if (N <= k) {
            moo((k - (cnt+2))/2, cnt -1);
        } else {
            N -= k + cnt + 3;
            moo((k - (cnt+2))/2, cnt -1);
        }
    }
} // end of class