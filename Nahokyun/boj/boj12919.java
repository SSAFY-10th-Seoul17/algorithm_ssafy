package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj12919 {
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final String s = br.readLine();
        final String goal = br.readLine();

        StringBuilder sb = new StringBuilder(goal);
        track(s, sb, goal.length() - 1);

        System.out.println(result);
    }

    private static void track(String s, StringBuilder goal, int idx) {
        if (s.length() == goal.length()) {
            if (s.equals(goal.toString())) {
                result = 1;
            }
            return;
        }
        StringBuilder tmp = new StringBuilder(goal).reverse();


        if (goal.charAt(idx) == 'A') {
            track(s, new StringBuilder(goal.substring(0, idx)), idx - 1);
        }

        if (goal.reverse().charAt(idx) == 'B') {
            track(s, new StringBuilder(tmp.substring(0, idx)), idx - 1);
        }


    }
}
