package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj24230 {
    static int[] objColor;// 목표 색깔 배열
    static ArrayList<Integer>[] arr;

    static boolean[] flagForDFS;
    static int n;
    static int count = 0;// dfs 횟수 카운팅

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        objColor = new int[n + 1];
        flagForDFS = new boolean[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            objColor[i] = Integer.parseInt(st.nextToken());
        } // 색 최종본 입력완료

        arr = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        } // 트리 저장할 배열 초기화

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x].add(y);
            arr[y].add(x);
        } // 트리 연결 완료
        flagForDFS[1] = true;
        if (objColor[1] == 0) {
            dfs(1, 0);
        }else {
            dfs(1,objColor[1]);
            count++;
        }

        // 이때 dfs용으로 사용할 boolean배열 선언 필요
        // dfs 들어가야할 상황(색이 다를때)마다 count++

        System.out.println(count);

    }

    private static void dfs(int cur, int parentColor) {
        for (int i : arr[cur]) {
            if (!flagForDFS[i]) {
                flagForDFS[i] = true;
                if (objColor[i] != parentColor) {
                    count++;
                }
                dfs(i, objColor[i]);
            }
        }
    }
}