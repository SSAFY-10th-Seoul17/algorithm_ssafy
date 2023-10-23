package study10월2주차목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2792_보석_상자 {
    static int n, m;
    static int[] gems;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        gems = new int[m];
        for (int i = 0; i < m; i++) gems[i] = Integer.parseInt(br.readLine());

        int l = 0;
        int r = Integer.MAX_VALUE / 2;

        while (l + 1 != r) {
            int m = (l+r) / 2;
            if (check(m))
                r = m;
            else
                l = m;
        }
        System.out.println(r);
    }

    private static boolean check(int mid) {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            cnt += gems[i] / mid;
            cnt += (gems[i] % mid == 0)? 0 : 1;
        }
        return cnt <= n;
    }
} // end of class