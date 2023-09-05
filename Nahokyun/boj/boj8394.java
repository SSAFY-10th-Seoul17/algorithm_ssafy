package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj8394 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long before1 = 1;// n==2일때 값
        long before2 = 2;// n==3일때 값

        long tmp;

        if (n == 1)
            System.out.println(0);
        else {
            n = n - 2;

            while (n-- > 0) {
                tmp = (before1 + before2) % 10;
                before1 = before2 % 10;
                before2 = tmp;
            }

            System.out.println(before2);
        }
    }
}
