package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1780 {

    private static int[][] arr;
    private static int n;
    private static int[] paper = new int[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        divAndCon(0, 0, n);


        for (int i = 0; i < 3; i++) {
            System.out.println(paper[i]);
        }
    }

    private static void divAndCon(int startX, int startY, int size) {

        int cur = arr[startY][startX];
        if (size == 1) {
            paper[cur + 1]++;
            return;
        }
        int ySize = startY + size;
        int xSize = startX + size;
        int nextSize = size / 3;
        boolean flag = false;

        for (int i = startY; i < ySize; i++) {
            for (int j = startX; j < xSize; j++) {
                if (arr[i][j] != cur) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }

        if (flag) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    divAndCon(startX + i * nextSize, startY + j * nextSize, nextSize);
                }
            }
        } else {
            paper[cur + 1]++;
        }
    }
}
