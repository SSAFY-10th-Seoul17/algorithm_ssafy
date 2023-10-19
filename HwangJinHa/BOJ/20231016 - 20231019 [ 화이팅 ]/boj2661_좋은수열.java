package study10월3주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj2661_좋은수열 {
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new int[Integer.parseInt(br.readLine())];
        makearr(0);
    }

    private static void makearr(int depth) {
        if (depth == arr.length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) { sb.append(arr[i]); }
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            arr[depth] = i;
            if (check(depth)) {
                makearr(depth + 1);
            }
        }
    }

    private static boolean check(int depth) {
        int halfLen = (depth+1) / 2;
        for (int checkLen = 1; checkLen <= halfLen; checkLen++) {
            int start1 = depth - checkLen;
            int end1 = start1 - checkLen;
            int start2 = depth;
            int end2 = start2 - checkLen;
            if (end1 < -1)
                break;
            boolean isSame = true;
            for (int i1 = start1, i2 = start2; i1 > end1; i1--, i2--) {
                if (arr[i1] != arr[i2]) {
                    isSame = false;
                    break;
                }
            }
            if (isSame)
                return false;
        }
        return true;
    }
}