package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
결혼식
 */
public class boj5567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> arr = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        int count = 0;
        boolean[] flag = new boolean[n + 1];
        flag[1] = true;

        for (int i = 0; i <= n; i++) {
            arr.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            arr.get(first).add(second);
            arr.get(second).add(first);

            if (first == 1) {
                q.add(second);
                flag[second] = true;
                count++;
            }
            if (second == 1) {
                q.add(first);
                flag[first] = true;
                count++;
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            int sz = arr.get(cur).size();
            for (int i = 0; i < sz; i++) {
                int a = arr.get(cur).get(i);
                if (!flag[a]) {
                    flag[a] = true;
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
