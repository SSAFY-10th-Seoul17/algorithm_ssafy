import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int sixCnt = 0;
        int cnt = 0;
        int num = 666;
        while (true) {
            int tmp = num;
            sixCnt = 0;
            while (true) {
                if (tmp == 0) break;
                if (tmp % 10 == 6) {
                    if (tmp / 10 % 10 == 6 && tmp / 100 % 10 == 6) {
                        cnt++;
                        break;
                    }
                }
                tmp /= 10;
            }
            if (cnt == N) {
                System.out.println(num);
                break;
            } else num++;
        }
    } // end of main
} // end of class