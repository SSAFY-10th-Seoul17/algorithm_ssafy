package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj16719 {
    static StringBuilder sb = new StringBuilder();
    private static char[] arr;
    private static boolean[] isUsed;
    private static String s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = br.readLine();

        arr = s.toCharArray();
        isUsed = new boolean[s.length()];

        make(0, s.length());

        System.out.println(sb);

    }

    private static void make(int startIdx, int cur) {
        if (cur == 0) {
            return;
        }

        for (int j = 0; j < arr.length - startIdx; j++) {
            int min = startIdx;
            boolean flag = false;
            for (int i = startIdx; i < arr.length; i++) {
                if (!isUsed[i] && arr[min] >= arr[i]) {
                    if (arr[min] == arr[i] && min != i) {
                        continue;
                    }
                    min = i;
                    flag = true;
                }
            }
            isUsed[min] = true;

            if (flag) {
                StringBuilder tmp = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (isUsed[i]) {
                        tmp.append(arr[i]);
                    }
                }

                sb.append(tmp).append('\n');
                make(min + 1, cur - 1);
            }
        }

    }

}
