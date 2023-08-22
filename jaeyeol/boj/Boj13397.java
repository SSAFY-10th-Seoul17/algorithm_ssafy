import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj13397 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        int min = MAX_SCORE;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }

        System.out.println(getMinMaxScore(arr, m, max - min));
    }

    private static int getMinMaxScore(int[] arr, int m, int right) {
        int left = 0;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (isPossible(arr, m, mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    static final int MAX_SCORE = 10_000;
    private static boolean isPossible(int[] arr, int m, int score) {
        int max = 0;
        int min = MAX_SCORE;

        for (int i : arr) {
            max = Math.max(max, i);
            min = Math.min(min, i);
            if (max - min > score) {
                if (--m == 0) {
                    return false;
                }
                max = min = i;
            }
        }

        return true;
    }

}

