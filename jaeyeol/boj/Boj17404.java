import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17404 {
    static final int MAX = 10_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] cur = new int[3][3];
        int[][] prev = new int[3][3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            Arrays.fill(prev[i], MAX);
            prev[i][i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 3; j++) {
                cur[j][0] = Integer.min(prev[j][1], prev[j][2]) + a;
                cur[j][1] = Integer.min(prev[j][0], prev[j][2]) + b;
                cur[j][2] = Integer.min(prev[j][0], prev[j][1]) + c;
            }

            int[][] temp = cur;
            cur = prev;
            prev = temp;
        }

        System.out.println(getResult(prev));
    }

    private static int getResult(int[][] prev) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < prev.length; i++) {
            for (int j = 0; j < prev[i].length; j++) {
                if (i != j) {
                    result = Math.min(result, prev[i][j]);
                }
            }
        }

        return result;
    }

}
