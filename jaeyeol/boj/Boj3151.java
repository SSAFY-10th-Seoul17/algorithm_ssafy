import java.io.*;
import java.util.*;

public class Boj3151 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getZeroSumCount(arr, n));
    }

    private static long getZeroSumCount(int[] arr, int n) {
        Arrays.sort(arr);
        long count = 0;

        for (int cur = 0; cur < n; cur++) {
            int left = cur + 1;
            int right = n - 1;

            while (left < right) {
                int sum = arr[cur] + arr[left] + arr[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    int leftValue = arr[left];
                    int rightValue = arr[right];
                    int leftCount = 1;
                    int rightCount = 1;
                    while (++left < arr.length) {
                        if (arr[left] == leftValue) {
                            leftCount++;
                        } else {
                            break;
                        }
                    }

                    if (leftValue == rightValue) {
                        count += combination(leftCount);
                        break;
                    }

                    while (left <= --right) {
                        if (arr[right] == rightValue) {
                            rightCount++;
                        } else {
                            break;
                        }
                    }

                    count += (long) leftCount * rightCount;
                }
            }
        }

        return count;
    }

    private static long combination(int r) {
        return (long) r * (r - 1) >> 1;
    }

}

