import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1780 {
    static int[][] board;
    static int[] papers = new int[3];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        countPaper(0, 0, n);
        System.out.println(papers[0] + "\n" + papers[1] + "\n" + papers[2]);
    }

    static void countPaper(int x, int y, int l) {
        if (equalAll(x, y, l)) {
            papers[board[x][y] + 1]++;
            return;
        }

        l /= 3;

        for (int i = 0; i < 9; i++) {
            countPaper(x + l * (i / 3), y + l * (i % 3), l);
        }
    }

    static boolean equalAll(int x, int y, int l) {
        int value = board[x][y];

        for (int i = x; i < x + l; i++) {
            for (int j = y; j < y + l; j++) {
                if (board[i][j] != value) {
                    return false;
                }
            }
        }

        return true;
    }

}
