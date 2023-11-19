package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj25795 {
    private static int n;
    private static int a;
    private static int b;
    private static int c;
    private static int sz;
    private static long max = Long.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sz = 2 * n;
        track(1, 1, 0, 1);

        System.out.println(max);
    }

    private static void track(int cur, int w, int b, long now) {
        if (cur == sz) {
            if (w == b) {
                check(now);
            }
            return;
        }
        now = now << 1;

        if (w > b) {
            track(cur + 1, w + 1, b, now | 1);
            track(cur + 1, w, b + 1, now);

        } else if (w == b) {
            track(cur + 1, w + 1, b, now | 1);
        }
    }

    private static void check(long now) {

        long cur = a;

        for (int i = sz; i >= 1; i--) {
            if ((now & (1 << (i - 1))) == 0) {
                cur = (cur * c) % 100000;
            } else {
                cur = (cur + b) % 100000;
            }
        }
        max = Math.max(cur, max);
    }

}
