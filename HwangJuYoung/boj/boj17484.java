import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] fuel = new int[N][M];

        for (int i = 0; i < fuel.length; i++) {
            for (int j = 0; j < fuel[i].length; j++) {
                fuel[i][j] = sc.nextInt();
            }
        }

        int[][][] tmp = new int[N][M][3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 3; k++) {
                    tmp[i][j][k] = 1000;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 3; j++) {
                tmp[0][i][j] = fuel[0][i];
            }
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i+1][j][1] = Math.min(tmp[i+1][j][1], tmp[i][j][0] + fuel[i+1][j]);
                tmp[i+1][j][1] = Math.min(tmp[i+1][j][1], tmp[i][j][2] + fuel[i+1][j]);

                if (j - 1 >= 0) {
                    tmp[i+1][j-1][0] = Math.min(tmp[i+1][j-1][0], tmp[i][j][1] + fuel[i+1][j-1]);
                    tmp[i+1][j-1][0] = Math.min(tmp[i+1][j-1][0], tmp[i][j][2] + fuel[i+1][j-1]);
                }

                if (j + 1 < M) {
                    tmp[i+1][j+1][2] = Math.min(tmp[i+1][j+1][2], tmp[i][j][1] + fuel[i+1][j+1]);
                    tmp[i+1][j+1][2] = Math.min(tmp[i+1][j+1][2], tmp[i][j][0] + fuel[i+1][j+1]);
                }
            }
        }

        int min = 1000;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 3; j++) {
                min = Math.min(min, tmp[N - 1][i][j]);
            }
        }

        System.out.println(min);

    }
}