import java.io.*;
import java.util.*;

public class boj1780 {
    static int[][] paper;
    static int n, one = 0, zero = 0, minus = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        paper = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divide(0, 0, n);
        System.out.println(minus);
        System.out.println(zero);
        System.out.println(one);
    }

    public static void divide(int startX, int startY, int length) {

        if (isCheck(startX, startY, length)) {
            if (paper[startX][startY] == -1) {
                minus++;
            } else if (paper[startX][startY] == 0) {
                zero++;
            } else {
                one++;
            }
            return;
        }

        length = length / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                divide(startX + i * length, startY + j * length, length);
            }
        }
    }

    public static boolean isCheck(int x, int y, int length) {
        int first = paper[x][y];
        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + length; j++) {
                if (paper[i][j] != first) {
                    return false;
                }
            }
        }
        return true;
    }
}
