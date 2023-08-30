import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.IntBinaryOperator;

public class Boj17265 {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j += 2) {
                map[i][j >> 1] = str.charAt(j);
            }
        }

        System.out.print(dfs(new int[n][n], Math::max, n - 1, n - 1, 0) + " " +
                dfs(new int[n][n], Math::min, n - 1, n - 1, 0));
    }

    private static int dfs(int[][] dp, IntBinaryOperator operator, int x, int y, int next) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        if (!Character.isDigit(map[x][y])) { // 연산자라면
            if (x > 0 && y > 0) {
                return calculate(operator.applyAsInt(dfs(dp, operator, x - 1, y, 0),
                        dfs(dp, operator, x, y - 1, next)), map[x][y], next);
            }

            return calculate(dfs(dp, operator, Math.max(0, x - 1), Math.max(0, y - 1), 0), map[x][y], next);
        }

        int number = map[x][y] - '0';
        if (x + y == 0) {
            return number;
        }

        if (x > 0 && y > 0) {
            return dp[x][y] = operator.applyAsInt(dfs(dp, operator, x, y - 1, number),
                    dfs(dp, operator, x - 1, y, number));
        }
        return dp[x][y] = dfs(dp, operator, Math.max(0, x - 1), Math.max(0, y - 1), number);
    }

    private static int calculate(int number1, char operator, int number2) {
        switch (operator) {
            case '+':
                return number1 + number2;
            case '-':
                return number1 - number2;
            default: // *
                return number1 * number2;
        }
    }

}
