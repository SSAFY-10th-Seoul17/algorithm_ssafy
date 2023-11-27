package study10월4주차월;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj12919_A와B2 {
    static String s;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();
        String t = br.readLine();

        System.out.println(check(t)? 1 : 0);
    }

    private static boolean check(String t) {
        if (s.length() >= t.length()) {
            if (s.equals(t))
                return true;
            return false;
        }
        if (t.charAt(t.length()-1) == 'A')
            if (check(t.substring(0, t.length() - 1)))
                return true;
        if (t.charAt(0) == 'B')
            if (check(new StringBuilder(t).reverse().substring(0, t.length()-1).toString()))
                return true;
        return false;
    }
}
