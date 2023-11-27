package study10월3주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj17297_MessiGimossi {
    static int n;
    static String space = "Messi Messi Gimossi";
    static ArrayList<Long> length = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        length.add(5L);
        length.add(5L + 1 + 7);

        while (length.get(length.size()-1) < n) {
            length.add(length.get(length.size()-1) + 1 + length.get(length.size()-2));
        }

        int idx = length.size() - 1;
        while (n > 13) {
            long len = length.get(idx-1);
            if (len+1 == n) {
                System.out.println(space);
                return;
            }
            else if (len+1 < n) {
                n -= len + 1;
            } else {
            }
            idx -= 1;
        }
        char ans = "Messi Gimossi".charAt(n-1);
        System.out.println(ans == ' '? space : ans);
    }
}