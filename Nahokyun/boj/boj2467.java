package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.awt.Point;

public class boj2467 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int min = 2000000001;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = n - 1;
        Point pair = null;

        while (left < right) {
            int cur = arr[left] + arr[right];
            if (min > Math.abs(cur)) {
                min = Math.abs(cur);
                pair = new Point(arr[left], arr[right]);
            }

            if (cur < 0) {
                left++;
            } else {
                right--;
            }


        }

        System.out.println(pair.x + " " + pair.y);


    }

}
