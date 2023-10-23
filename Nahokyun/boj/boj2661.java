package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj2661 {
    static String min = "";
    static boolean flag = false;

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        find(sb, n, 0, 0);
        System.out.println(min);
    }

    private static void find(StringBuilder sb, int n, int cur, int before) {
        if (flag)
            return;

        if (cur == n) {
            min = sb.toString();
            flag = true;
            return;
        }
        for (int i = 1; i <= 3; i++) {
            if (before == i) {
                continue;
            }
            sb.append(i);
            if (check(sb.toString())) {
                find(sb, n, cur + 1, i);
            }
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    private static boolean check(String num) {
        for (int i = 1; i <= num.length() / 2; i++) {// 다음것과 비교할 길이
            for (int j = 0; j <= num.length() - 2 * i; j++) {// 시작 좌표
                if (num.substring(j, j + i).equals(num.substring(j + i, j + i * 2))) {
                    // System.out.println(num+"에서 "+num.substring(j,i)+"와 "+num.substring(j+i,i*2)+"
                    // 비교했을때 같음");
                    return false;
                }
            }
        }

        return true;
    }

}
