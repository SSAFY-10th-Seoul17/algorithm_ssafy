package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj16198 {

    private static int[] arr;
    private static int n;
    static boolean[] flag;
    static int maxScore = 0;


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> al = new ArrayList<>();
        arr = new int[n];
        flag = new boolean[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        perm(0, al);

        System.out.println(maxScore);

    }

    private static void perm(int cur, ArrayList<Integer> al) {
        if (cur == n - 2) {
            maxScore = maxScore < cal(al) ? cal(al) : maxScore;
            return;
        }

        for (int i = 1; i < n - 1; i++) {
            if (!flag[i]) {
                flag[i] = true;
                al.add(i);
                perm(cur + 1, al);
                al.remove(al.size() - 1);
                flag[i] = false;
            }
        }
    }

    private static int cal(ArrayList<Integer> al) {

        int score = 0;
        boolean[] used = new boolean[n];
        for (int i = 0; i < al.size(); i++) {
            int tmp = al.get(i);
            used[tmp] = true;
            int left = tmp - 1;
            int right = tmp + 1;

            while (used[left]) {
                left--;
            }
            while (used[right]) {
                right++;
            }

            score += arr[left] * arr[right];
        }


        return score;
    }

}
