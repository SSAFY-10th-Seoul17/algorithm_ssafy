package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1436 {
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int count = 1;
        int start = 666;
        while (count != n) {
            if (check(++start))
                count++;
        }
        System.out.println(start);
    }

    private static boolean check(int n) {
        while (n != 0) {
            if (n % 10 == 6 && n / 10 % 10 == 6 && n / 100 % 10 == 6) {
                return true;
            }
            n /= 10;
        }
        return false;

    }


}
