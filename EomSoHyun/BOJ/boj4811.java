import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int cnt, n;
    static long[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            nums = new long[n+1];
            nums[0] = 1;
            nums[1] = 1;

            for (int i = 2; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    nums[i] += nums[j] * nums[i-j-1];
                }
            }
            sb.append(nums[n]).append('\n');

        }

        System.out.println(sb);

    }


}


