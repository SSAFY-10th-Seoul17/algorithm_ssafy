import java.io.*;
import java.util.*;

public class Boj3079 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] times = new int[n];

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, times[i] = Integer.parseInt(br.readLine()));
        }

        System.out.println(getShortestTime(times, n, m, max));
    }

    private static long getShortestTime(int[] times, int n, int m, int max) {
        long left = 0;
        long right = (long) (m + n) / n * max;

        while (left <= right) {
            long mid = (left + right) >> 1;

            if (Arrays.stream(times).mapToLong(time -> mid / time).sum() < m) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

}

