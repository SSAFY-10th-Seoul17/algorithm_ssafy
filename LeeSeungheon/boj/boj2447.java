import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj2447 {

    static int N;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i], ' ');
        }

        solve(N, 0, 0);

        for (int i = 0; i < N; i++) {
            sb.append(map[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve(int N, int x, int y) {

        if (N == 1) {
            map[y][x] = '*';
            return;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (i == 1 && j == 1) {
                    continue;
                }
                solve(N / 3, x + i * (N / 3), y + j * (N / 3));


            }
        }
    }
}
