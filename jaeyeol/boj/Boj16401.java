import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj16401 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] size = new int[n];
        st = new StringTokenizer(br.readLine());
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            size[i] = Integer.parseInt(st.nextToken());
            maxSize = Math.max(maxSize, size[i]);
        }

        System.out.println(getSnackSize(size, maxSize, m));
    }

    private static int getSnackSize(int[] size, int maxSize, int m) {
        int left = 1;
        int right = m <= size.length ? maxSize : maxSize / Math.max(m / size.length, 1);

        while (left <= right) {
            int mid = (left + right) >> 1;

            int count = getSnackCount(size, mid);

            if (count < m) { // 다 줄 수 없음
                right = mid - 1;
            } else { // 더 줄 수 있음
                left = mid + 1;
            }
        }

        return right;
    }

    private static int getSnackCount(int[] size, int length) {
        int snacks = 0;
        for (int i : size) {
            snacks += i / length;
        }

        return snacks;
    }

}
