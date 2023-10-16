package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17265 {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[][] arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = st.nextToken().charAt(0);
            }
        }//입력 종료
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0][0]);
        makeExpression(0, 0, n, arr, sb);


        System.out.println(max + " " + min);


    }

    private static void makeExpression(int curX, int curY, int n, char[][] arr, StringBuilder sb) {
        if (curX == n - 1 && curY == n - 1) {
            calculate(sb, n);
            return;
        } else if (curX == n - 1) {
            makeExpression(curX, curY + 1, n, arr, sb.append(arr[curY + 1][curX]));
            sb.deleteCharAt(sb.length() - 1);
        } else if (curY == n - 1) {
            makeExpression(curX + 1, curY, n, arr, sb.append(arr[curY][curX + 1]));
            sb.deleteCharAt(sb.length() - 1);
        } else {
            makeExpression(curX, curY + 1, n, arr, sb.append(arr[curY + 1][curX]));
            sb.deleteCharAt(sb.length() - 1);

            makeExpression(curX + 1, curY, n, arr, sb.append(arr[curY][curX + 1]));
            sb.deleteCharAt(sb.length() - 1);
        }


    }

    private static void calculate(StringBuilder sb, int n) {
        int result = sb.charAt(0) - '0';
        int size = 2 * n - 2;

        for (int i = 2; i <= size; i += 2) {
            if (sb.charAt(i - 1) == '*') {
                result *= sb.charAt(i) - '0';
            } else if (sb.charAt(i - 1) == '+') {
                result += sb.charAt(i) - '0';
            } else if (sb.charAt(i - 1) == '-') {
                result -= sb.charAt(i) - '0';
            }
        }

        if (max < result) {
            max = result;
        }
        if (min > result) {
            min = result;
        }
    }

}
