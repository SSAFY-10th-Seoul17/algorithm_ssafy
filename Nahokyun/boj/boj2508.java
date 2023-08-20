package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2508 {
    static char[][] arr = new char[400][400];
    static int count = 0;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            init();
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            for (int i = 0; i < r; i++) {
                String s = br.readLine();
                for (int j = 0; j < c; j++) {
                    arr[i][j] = s.charAt(j);
                }
            } // 입력 종료

            find(r,c);
            System.out.println(count);
        }

    }// 메인종료

    private static void init() {
        for (int i = 0; i < 400; i++) {
            Arrays.fill(arr[i], ' ');
        }
        count=0;
    }

    private static void find(int r, int c) {
        // 가로 탐색
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c - 2; j++) {
                if(arr[i][j]=='>'&&arr[i][j+1]=='o'&&arr[i][j+2]=='<')
                    count++;
            }
        }

        // 세로탐색
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r - 2; j++) {
                if(arr[j][i]=='v'&&arr[j+1][i]=='o'&&arr[j+2][i]=='^')
                    count++;
            }
        }
    }
}
