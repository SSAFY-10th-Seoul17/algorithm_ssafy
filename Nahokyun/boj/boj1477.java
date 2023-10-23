package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1477 {
    static boolean[] map;
    static int maxMin = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        int[] arr = new int[n + 2];
        map = new boolean[l + 1];
        st = new StringTokenizer(br.readLine());

        arr[0] = 0;
        arr[n + 1] = l;
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 입력 종료

        bs(m, l, arr, n);

        System.out.println(maxMin);

    }

    private static void bs(int m, int l, int[] arr, int n) {

        int start = 0;
        int end = l - 1;
        int mid = 0;
        Arrays.sort(arr);
        while (start <= end) {
            mid = (start + end) >> 1;
            int sum = 0;
            if(mid==0) {
                mid=1;
            }

            for (int i = 0; i < n + 1; i++) {
                sum += (arr[i + 1] - arr[i] - 1) / mid;
            }

            if (sum > m) {
                start = mid + 1;
            } else {
                maxMin = Math.min(maxMin, mid);
                end = mid - 1;
                if(end==0)
                    return;
            }
        }

    }
}

