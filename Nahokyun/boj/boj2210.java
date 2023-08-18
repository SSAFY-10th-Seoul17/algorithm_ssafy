package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class boj2210{
    static int[][] arr;
    static int[] dx = new int[] { -1, 0, 1, 0 };
    static int[] dy = new int[] { 0, 1, 0, -1 };
    static Set<Integer> s = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new int[5][5];

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 입력 종료
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                StringBuilder sb = new StringBuilder();
                dfs(i, j, 0, sb);
            }
        }
        System.out.println(s.size());

    }// end of main

    private static void dfs(int startX, int startY, int cur, StringBuilder sb) {
        if (cur == 6) {
            s.add(Integer.valueOf(sb.toString()));
            return;
        }

        for (int i = 0; i < 4; i++) {
            int cmpX = startX + dx[i];
            int cmpY = startY + dy[i];
            if (cmpX >= 0 && cmpX < 5 && cmpY >= 0 && cmpY < 5) {
                sb.append(arr[cmpY][cmpX]);
                dfs(cmpX, cmpY, cur + 1, sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }

    }

}

