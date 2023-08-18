package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.pow;

public class boj1891 {

    private static String num;

    private static int D;
    private static long inputX;
    private static long inputY;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        num = st.nextToken();
        st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());
        long n = (long) pow(2, D);
        find1(0, 0, 0, n, n);//숫자보고 위치좌표

        inputX += x;
        inputY += y;
        if (inputX < n && inputX >= 0 && inputY >= 0 && inputY < n) {
            find2(D, inputX, inputY);//위치보고 숫자찾기
            System.out.println(sb.toString());
        } else {
            System.out.println(-1);
        }

    }

    private static void find2(int d, long inX, long inY) {
        long curN = (long) pow(2, d - 1);
        if (d == 0) {
            return;
        }
        if (inX < curN && inY < curN) {
            sb.append(3);
            find2(d - 1, inX, inY);
        } else if (inX < curN && inY >= curN) {
            sb.append(2);
            find2(d - 1, inX, inY - curN);
        } else if (inX >= curN && inY >= curN) {
            sb.append(1);
            find2(d - 1, inX - curN, inY - curN);
        } else if (inX >= curN && inY < curN) {
            sb.append(4);
            find2(d - 1, inX - curN, inY);
        }

    }

    private static void find1(int d, long stX, long stY, long endX, long endY) {
        if (d == D) {
            inputX = stX;
            inputY = stY;
            return;
        }
        int cur = num.charAt(d) - '0';

        switch (cur) {
            case 1:
                find1(d + 1, (stX + endX) / 2, (stY + endY) / 2, endX, endY);
                break;
            case 2:
                find1(d + 1, stX, (stY + endY) / 2, (stX + endX) / 2, endY);
                break;
            case 3:
                find1(d + 1, stX, stY, (stX + endX) / 2, (stY + endY) / 2);
                break;
            case 4:
                find1(d + 1, (stX + endX) / 2, stY, endX, (stY + endY) / 2);
                break;
        }
    }

}
