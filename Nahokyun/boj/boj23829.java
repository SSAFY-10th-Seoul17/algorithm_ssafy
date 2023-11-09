package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj23829 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        long[] sum = new long[n];
        st = new StringTokenizer(br.readLine());


        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        int max = arr[n - 1];
        int min = arr[0];

        sum[0] = arr[0];
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            long input = Integer.parseInt(br.readLine());
            long value;

            if (max < input) {
                value = n * input - sum[n - 1];
                sb.append(value).append('\n');
                continue;
            }
            if (min > input) {
                value = sum[n - 1] - n * input;
                sb.append(value).append('\n');
                continue;
            }

            int left = 0;
            int right = n - 1;
            int mid;
            while (left + 1 < right) {
                mid = (left + right) >> 1;
                if (arr[mid] <= input) {
                    left = mid;
                } else {
                    right = mid;
                }
            }

            value = right * input - sum[left] + sum[n - 1] - sum[left] - (n - right) * input;
            sb.append(value).append('\n');
        }
        System.out.println(sb);
    }


}

