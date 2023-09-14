import java.io.*;
import java.util.StringTokenizer;

public class Boj11049 {

    static class Matrix {
        int row;
        int col;

        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Matrix[] matrices = new Matrix[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            matrices[i] = new Matrix(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        dp = new int[n][n];

        System.out.print(getMinMultiple(matrices, 0, n - 1));
    }

    static int getMinMultiple(Matrix[] matrices, int left, int right) {
        if (left == right) {
            return 0;
        } else if (dp[left][right] > 0) {
            return dp[left][right];
        }

        int count = Integer.MAX_VALUE;

        for (int i = left; i < right; i++) {
            count = Math.min(count, matrices[left].row * matrices[right].col * matrices[i].col  // 둘을 곱하는 비용
                            + getMinMultiple(matrices, left, i) + getMinMultiple(matrices, i + 1, right)); // 재료 둘을 만드는 비용
        }

        return dp[left][right] = count;
    }

}

