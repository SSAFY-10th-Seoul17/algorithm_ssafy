import java.io.*;
import java.util.*;

public class Main {
    static int n = 0;
    static int[][] arr;
    static int[] cnt = {0, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++){
                int num = Integer.parseInt(st.nextToken());
                arr[i][j] = (num == -1)? 2 : num;
            }
        }
        check(0, 0, n, n, n);
        System.out.println(cnt[2]);
        System.out.println(cnt[0]);
        System.out.println(cnt[1]);
    }

    private static void check(int y, int x, int yy, int xx, int size) {
        if (size == 1){
//            System.out.println(y + ", " + x + " / " + yy + ", " + xx + " 에서 " + arr[y][x] + " 추가" );
            cnt[arr[y][x]]++;
            return;
        }
        for (int i = y; i < yy; i++){
            for (int j = x; j < xx; j++){
                if (arr[i][j] == arr[y][x]) {
                }
                else {
                    size /= 3;
                    for (int ii = 0; ii < 3; ii++) {
                        for (int jj = 0; jj < 3; jj++) {
                            check(y + size * ii, x + size * jj, y + size * (ii + 1), x + size * (jj + 1), size);
                        }
                    }
                    return;
                }
            }
        }
//        System.out.println(y + ", " + x + " / " + yy + ", " + xx + " 에서 " + arr[y][x] + " 추가" );
        cnt[arr[y][x]]++;
    }
}
