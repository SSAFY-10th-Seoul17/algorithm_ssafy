import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int fiveCnt = count5(N) - count5(M) - count5(N-M);
        int twoCnt = count2(N) - count2(M) - count2(N-M);

        System.out.println(Math.min(fiveCnt, twoCnt));

    } // end of main

    private static int count5(int num) {
        int fiveCnt = 0;

        while (num > 0) {
            num /= 5;
            fiveCnt += num;
        }
        return fiveCnt;
    }

    private static int count2(int num) {
        int twoCnt = 0;

        while (num > 0) {
            num /= 2;
            twoCnt += num;
        }
        return twoCnt;
    }
} // end of class