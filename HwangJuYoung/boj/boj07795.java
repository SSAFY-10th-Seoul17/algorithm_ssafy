package BOJ;

import java.io.*;
import java.util.*;

public class BOJ07795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int[] arr = new int[A];
            int[] brr = new int[B];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < brr.length; i++) {
                brr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);
            Arrays.sort(brr);

            int cnt = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] <= brr[0]) break;
                for (int j = brr.length - 1; j >= 0; j--) {
                    if (arr[i] > brr[j]) {
                        cnt += j + 1;
                        break;
                    }
                }
            }

            System.out.println(cnt);
        }
    } // end of main
} // end of class