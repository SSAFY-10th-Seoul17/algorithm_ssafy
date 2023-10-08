import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2096 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] prev = new int[2][3];
        int[][] cur = new int[2][3];
        int[][] temp;

        for (int i = 0; i < n; i++) {
            cur[0][0] = Math.max(prev[0][0], prev[0][1]);
            cur[1][0] = Math.min(prev[1][0], prev[1][1]);

            cur[0][2] = Math.max(prev[0][1], prev[0][2]);
            cur[1][2] = Math.min(prev[1][1], prev[1][2]);

            cur[0][1] = Math.max(cur[0][0], cur[0][2]);
            cur[1][1] = Math.min(cur[1][0], cur[1][2]);

            String strNumber = br.readLine();
            for (int j = 0; j < 3; j++) {
                int number = strNumber.charAt(j << 1) - '0';
                cur[0][j] += number;
                cur[1][j] += number;
            }

            temp = prev;
            prev = cur;
            cur = temp;
        }

        System.out.println(Arrays.stream(prev[0]).reduce(Math::max).getAsInt() + " " 
                + Arrays.stream(prev[1]).reduce(Math::min).getAsInt());
    }
}

