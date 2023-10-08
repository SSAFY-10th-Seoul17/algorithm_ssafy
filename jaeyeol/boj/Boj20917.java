package problem.baekjoon.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj20917 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            result.append(getMaxDistance(arr, s - 1)).append("\n");
        }

        System.out.print(result);
    }

    private static int getMaxDistance(int[] arr, int amount) {
        Arrays.sort(arr);
        int right = arr[arr.length - 1] - arr[0];
        int left = getLeft(arr);

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (isPossible(arr, mid, amount)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    private static int getLeft(int[] arr) {
        int left = Integer.MAX_VALUE;
        int prev = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            arr[i] -= prev;
            left = Math.min(left, arr[i]);
            prev = temp;
        }

        return left;
    }

    private static boolean isPossible(int[] arr, int minDistance, int amount) {
        int dist = 0;
        for (int i = 1; i < arr.length; i++) {
            dist += arr[i];
            if (dist >= minDistance) {
                if (--amount <= 0) {
                    return true;
                }
                dist = 0;
            }
        }

        return false;
    }

}

