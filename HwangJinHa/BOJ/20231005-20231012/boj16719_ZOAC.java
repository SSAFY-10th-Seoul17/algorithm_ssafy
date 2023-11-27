package study10월2주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj16719_ZOAC {
    static char[] str;
    static boolean[] on;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine().toCharArray();
        on = new boolean[str.length];

        find(0, str.length);
        System.out.println(sb);
    }

    private static void find(int start, int end) {
        if (start == end)
            return;
        // 현재 가장 작은 것
        int idx = -1;
        char c = 'Z' + 1;
        for (int i = start; i < end; i++) {
            if (c > str[i]) {
                idx = i;
                c = str[i];
            }
        }

        on[idx] = true;
        // 스트링 만들어주기
        for (int i = 0; i < on.length; i++) {
            if (on[i])
                sb.append(str[i]);
        }
        sb.append("\n");

        find(idx + 1, end);
        find(start, idx);
    }
}