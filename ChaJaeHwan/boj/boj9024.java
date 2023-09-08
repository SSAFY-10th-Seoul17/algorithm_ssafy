import java.util.*;
import java.io.*;

public class boj9024 {

    static int T, N, K;
    static int[] nums;

    static int diff = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            nums = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(solve()).append("\n");
        }
        System.out.println(sb);
    }

    static int solve() {
        Arrays.sort(nums);

        int left = 0;
        int right = N - 1;
        int diff = Integer.MAX_VALUE;
        int answer = 0;
        while (left < right) {
            int sum = nums[left] + nums[right];
            int d = Math.abs(sum - K);
            if (d < diff) {
                diff = d;
                answer =1;
            } else if (d == diff) {
                answer += 1;
            }
            if (sum <= K) {
                left += 1;
            } else if (sum > K) {
                right--;
            }
        }

        return answer;
    }


}
