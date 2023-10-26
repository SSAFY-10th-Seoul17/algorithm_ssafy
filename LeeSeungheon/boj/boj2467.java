import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] map = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int num = 0; num < N; num++) {
            map[num] = Integer.parseInt(st.nextToken());
        }
        int[] solve = solve(map);

        sb.append(map[solve[0]]).append(" ").append(map[solve[1]]);
        System.out.println(sb);
    }

    private static int[] solve(int[] map) {

        Arrays.sort(map);
        int sum = Integer.MAX_VALUE;
        int left = 0;
        int right = map.length - 1;
        int temp = 0;
        int[] result = new int[2];

        while (left < right) {

            temp = map[left] + map[right];

            if (Math.abs(temp) < sum) {
                sum = Math.abs(temp);
                result[0] = left;
                result[1] = right;
            }

            if (temp < 0) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}