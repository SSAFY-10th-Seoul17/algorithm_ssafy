package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj1701 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] sub = new int[s.length()];
        int max = 0;
        for (int k = 0; k < s.length(); k++) {
            String tmp = s.substring(k);
            char[] pattern = tmp.toCharArray();

            for (int i = 1, j = 0; i < pattern.length; i++) {
                while (j > 0 && pattern[i] != pattern[j]) {
                    j = sub[j - 1];
                }
                if (pattern[i] == pattern[j]) {
                    sub[i] = ++j;
                } else {
                    sub[i] = 0;
                }
            }

            for (int i = 0; i < s.length(); i++) {
                max = Math.max(max, sub[i]);
            }

        }

        System.out.println(max);

    }
}