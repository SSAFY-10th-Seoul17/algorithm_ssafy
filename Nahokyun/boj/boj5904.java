package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj5904 {

    static boolean flag = false;
    static int sum = 1;
    private static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());


        moo(30);
        if (n == 1) {
            System.out.println('m');
        } else
            System.out.println(flag ? 'm' : 'o');
    }


    private static void moo(int cur) {

        if (sum > n) {
            return;
        }
        if (cur == 0) {
            sum += 3;
            if (sum == n) {
                flag = true;
                return;
            }
            return;
        }
        moo(cur - 1);
        sum += cur + 3;
        if (sum == n) {
            flag = true;
            return;
        }
        moo(cur - 1);

    }

}
