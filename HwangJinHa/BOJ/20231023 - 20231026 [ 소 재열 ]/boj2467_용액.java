package study10월4주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2467_용액 {
    static int n;
    static int[] liquids;
    static int stat = 0;
    static int ans = Integer.MAX_VALUE;
    static int[] arr = new int[2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        liquids = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            liquids[i] = Integer.parseInt(st.nextToken());

        int l = 0;
        int r = n - 1;
        while (l < r) {
            int stat = liquids[l] + liquids[r];
            if (Math.abs(stat) < ans) {
                ans = Math.abs(stat);
                arr[0] = liquids[l];
                arr[1] = liquids[r];
            }
            if (stat < 0) {
                l++;
            } else {
                r--;
            }
        }
        System.out.println(arr[0] + " " + arr[1]);
    }
}