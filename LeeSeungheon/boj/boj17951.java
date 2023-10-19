import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj17951 {

    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        map = new int[N];

        for (int num = 0; num < N; num++) {
            map[num] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve(K, Arrays.stream(map).sum()));
    }

    private static int solve(int k, int sum) {

        int low = 0;
        int high = sum;
        int mid;

        while (low + 1 < high) {

            mid = (high + low) / 2;

            if (count(mid) > k) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return high;
    }

    private static int count(int mid) {
        int count = 1;
        int sum = 0;

        for (int num : map) {
            sum += num;
            if (sum > mid) {
                count++;
                sum = 0;
            }
        }
        return count;
    }


}
