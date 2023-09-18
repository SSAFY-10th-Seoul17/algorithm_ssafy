package BOJ;

import java.util.*;
import java.io.*;

public class boj02666 {
    private static int[] nums;
    private static int t;
    private static int minMove = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int door1 = Integer.parseInt(st.nextToken());
        int door2 = Integer.parseInt(st.nextToken());

        t = Integer.parseInt(br.readLine());

        nums = new int[t];
        for (int i = 0; i < t; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        dfs(0, 0, door1, door2);
        System.out.println(minMove);
    } // end of main

    private static void dfs(int i, int cnt, int door1, int door2) {
        if (i == t) {
            minMove = Math.min(minMove, cnt);
            return;
        }
        if (nums[i] <= door1) {
            dfs(i+1, cnt + door1 - nums[i], nums[i], door2);
        } else if (nums[i] >= door2) {
            dfs(i+1, cnt + nums[i] - door2, door1, nums[i]);
        } else {
            dfs(i+1, cnt + nums[i] - door1, nums[i], door2);
            dfs(i+1, cnt + door2 - nums[i], door1, nums[i]);
        }
    }
} // end of class