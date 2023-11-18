package study10월1주차목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj18427_함께_블록_쌓기 {
    // 학생 수, 인당 최대 블록 수, 만들 높이
    static int n, m, h;
    static int[] dp;
    static int[] dpNext;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        dp = new int[h+1];
        dpNext = new int[h+1];
        dpNext[0] = 1;

        Set<Integer> duplicationRemover = new HashSet<>();

        while (n-- > 0) {
            dp = Arrays.copyOf(dpNext, h+1);
            duplicationRemover.clear();
            st = new StringTokenizer(br.readLine(), " ");

            while (st.hasMoreTokens())
                duplicationRemover.add(Integer.parseInt(st.nextToken()));

            for (int block : duplicationRemover) {
                for (int i = h - block; i >= 0; i--) {
                    dpNext[i + block] += dp[i];
                    dpNext[i + block] %= 10007;
                }
            }
        }
        System.out.println(dpNext[h]);

    }
}
