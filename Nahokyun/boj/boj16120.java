package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj16120 {

    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        if (!s.equals("P"))
            transform(s);

        System.out.println(!flag ? "PPAP" : "NP");

    }

    private static void transform(String s) {
        if (s.length() < 4) {
            flag = true;
            return;
        }

        if (s.charAt(0) != 'P' || s.charAt(1) != 'P') {
            flag = true;
            return;
        }
        int pCount = 2;
        for (int i = 2; i < s.length() - 1; i++) {
            if (s.charAt(i) == 'P') {
                pCount++;
                continue;
            }
            if (s.charAt(i) == 'A' && pCount >= 2) {
                if (s.charAt(i + 1) == 'P') {
                    pCount -= 1;
                    i++;
                } else {
                    flag = true;
                    return;
                }
            }


            if (pCount < 0) {
                flag = true;
                return;
            }
        }
        if (pCount == 1 && s.charAt(s.length() - 2) == 'A' && s.charAt(s.length() - 1) == 'P') {
            return;
        }
        flag = true;

    }

}
//