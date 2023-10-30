import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BOJ1780_종이의개수 {
    static int N;
    static int[][] map;
    static int[] result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        result = new int[3]; // 0 : -1, 1 : 0 , 2 : 1

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int c = N;
        int r = N;
        divide(0, 0, N);

        for (int i = 0; i < 3; i++) {
            System.out.println(result[i]);
        }

    }

    private static void divide(int r, int c, int n) {
        if (n == 1) {
            result[map[r][c] + 1] += 1;
            return;
        }

        if (!isFull(r, c, n)) { // 다 같지 않으면

            for (int i = r; i < r + n; i += n / 3) {
                for (int j = c; j < c + n; j += n / 3) {
                    divide(i, j, n / 3);
                }
            }
        }
    }

    private static boolean isFull(int startr, int startc, int n) {
        int temp = map[startr][startc];

        for (int i = startr; i < startr + n; i++) {
            for (int j = startc; j < startc + n; j++) {
                if (temp != map[i][j]) {
                    return false;
                }
                temp = map[i][j];
            }
        }
        result[temp + 1] += 1;
        return true;
    }


}

