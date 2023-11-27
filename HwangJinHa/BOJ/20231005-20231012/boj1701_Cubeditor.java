package study10월2주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1701_Cubeditor {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String patternstr = br.readLine();
        char[] pattern;

        int[] pi = new int[patternstr.length()];
        int ans = 0;
        for (int start = 0; start < patternstr.length(); start++) {
            pattern = patternstr.substring(start).toCharArray();
            for (int i = 1, j = 0; i < pattern.length; i++) {
                while (j > 0 && pattern[i] != pattern[j]) {
                    j = pi[j - 1];
                }
                if (pattern[i] == pattern[j]) {
                    pi[i] = ++j;
                    ans = Math.max(ans, pi[i]);
                } else {
                    pi[i] = 0;
                }
            }
        }
        System.out.println(ans);

    }
}