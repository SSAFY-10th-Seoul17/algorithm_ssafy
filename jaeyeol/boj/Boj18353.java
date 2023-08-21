import java.io.*;
import java.util.*;

public class Boj18353 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int top = -1;
        StringTokenizer st = new StringTokenizer(br.readLine());

        arr[++top] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (arr[top] > num) {
                arr[++top] = num;
            } else {
                arr[binarySearch(arr, top, num)] = num;
            }
        }

        System.out.println(n - (top + 1));
    }

    private static int binarySearch(int[] list, int top, int number) {
        int left = 0;
        int right = top;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (number > list[mid]) {
                right = mid - 1;
            } else if (number < list[mid]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return left;
    }
}

