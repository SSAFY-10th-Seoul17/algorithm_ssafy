package BOJ;

import java.io.*;
import java.util.*;

public class boj16401 {

    private static int M, N, maxLength;
    private static int[] snacks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        snacks = new int[N];

        int sum = 0;
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
            sum += snacks[i];
        }

        Arrays.sort(snacks);

        maxLength = 0;
        Bsearch(1, snacks[N-1], (snacks[N-1]+1)/2);


        if (sum < M) {
            System.out.println(0);
        } else {
            System.out.println(maxLength);
        }



    } // end of main

    private static void Bsearch(int start, int end, int mid) {
        if (start > end) {
            return;
        }

        int cnt = 0;
        for (int snack : snacks) {
            if (snack / mid > 0) {
                cnt += snack / mid;
            }
        }

        if (cnt < M) {
            Bsearch(start, mid - 1, (start + mid - 1)/2);
        } else {
            maxLength = Math.max(maxLength, mid);
            Bsearch(mid + 1, end, (mid + 1 + end)/2);
        }
    }

} // end of class

// ⌘⇧↑/↓ 또는 Alt +Shift+↑/↓ – 줄 이동
// ⌘D 또는 Ctrl+D – 줄 복제
// Alt + Enter - 오류 제안 보기