import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2503 {
    static class BaseBall {
        int[] number;
        int strike;
        int ball;

        public BaseBall(String number, int strike, int ball) {
            this.number = new int[number.length()];
            for (int i = 0; i < number.length(); i++) {
                this.number[i] = number.charAt(i) - '0';
            }
            this.strike = strike;
            this.ball = ball;
        }

        public boolean isTrue(int[] number) {
            int strike = 0;
            int ball = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (this.number[i] == number[j]) {
                        if (i == j) {
                            strike++;
                        } else {
                            ball++;
                        }
                    }
                }
            }

            return this.strike == strike && this.ball == ball;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        baseBalls = new BaseBall[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String number = st.nextToken();
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            baseBalls[i] = new BaseBall(number, strike, ball);
        }

        dfs(new int[3], new boolean[10], 0);
        System.out.println(count);
    }

    static int count;
    static BaseBall[] baseBalls;

    static void dfs(int[] number, boolean[] visited, int depth) {
        if (depth >= number.length) {
            for (BaseBall baseBall : baseBalls) {
                if (!baseBall.isTrue(number)) {
                    return;
                }
            }
            count++;
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (!visited[i]) {
                number[depth] = i;
                visited[i] = true;
                dfs(number, visited, depth + 1);
                visited[i] = false;
            }
        }
    }
}
