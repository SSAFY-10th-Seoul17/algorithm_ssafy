import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] video = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] strArr = sc.next().split("");
            for (int j = 0; j < N; j++) {
                video[i][j] = Integer.parseInt(strArr[j]);
            }
        }

        Main main = new Main();
        main.Capsule(video, N);
    }

    void Capsule (int[][] video, int N) {
        int num = video[0][0];
        if (N == 1) {
            System.out.printf("%d", num);
            return;
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (video[i][j] != num) {
                    cnt = 1;
                }
            }
        }

        if (cnt == 0) {
            System.out.printf("%d", num);
            return;
        }
        System.out.printf("(");
        int[][] tmp1, tmp2, tmp3, tmp4;
        tmp1 = new int[N/2][N/2];
        tmp2 = new int[N/2][N/2];
        tmp3 = new int[N/2][N/2];
        tmp4 = new int[N/2][N/2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i < N/2 && j < N/2) {
                    tmp1[i][j] = video[i][j];
                } else if (i < N/2 && j >= N/2) {
                    tmp2[i][j-N/2] = video[i][j];
                } else if (i >= N/2 && j < N/2) {
                    tmp3[i-N/2][j] = video[i][j];
                } else if (i >= N/2 && j >= N/2) {
                    tmp4[i-N/2][j-N/2] = video[i][j];
                }

            }
        }
        Capsule(tmp1, N/2);
        Capsule(tmp2, N/2);
        Capsule(tmp3, N/2);
        Capsule(tmp4, N/2);
        System.out.printf(")");
        return;
    }
}
