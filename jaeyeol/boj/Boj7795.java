import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj7795 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int[] a = new int[n];
            int[] b = new int[m];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            result.append(getPairs(a, b)).append("\n");
        }

        System.out.println(result);
    }

    private static long getPairs(int[] a, int[] b) {
        Arrays.sort(b);
        long pairs = 0;

        for (int i : a) {
            int count = binarySearch(b, i);
            if (count < 0) {
                count = -count - 1;
            }

            pairs += count;
        }

        return pairs;
    }

    private static int binarySearch(int[] b, int target) {
        int left = 0;
        int right = b.length - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (b[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

}
