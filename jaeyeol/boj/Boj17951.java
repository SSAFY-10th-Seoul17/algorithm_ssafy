import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17951 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] scores = new int[n];
        
        int sum = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            sum += scores[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(binarySearch(scores, k, sum));
    }

    private static int binarySearch(int[] scores, int k, int right) {
        int left = 0;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (isPossible(scores, mid, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    private static boolean isPossible(int[] scores, int minScore, int k) {
        int sum = 0;

        for (int score : scores) {
            if ((sum += score) >= minScore) {
                k--;
                sum = 0;
            }
        }

        return k <= 0;
    }

}

