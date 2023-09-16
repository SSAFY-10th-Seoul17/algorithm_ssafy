import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] cnt;
    static int[][] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        nums = new int[n][n];
        cnt = new int[3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Calc(0, 0, n);
        System.out.println(cnt[0]);
        System.out.println(cnt[1]);
        System.out.println(cnt[2]);


    }

    public static void Calc(int x, int y, int size) {
        if (isSame(x, y, size)) {
            return;
        }

        size /= 3;
        Calc(x, y, size);
        Calc(x, y+size, size);
        Calc(x, y+2*size, size);
        Calc(x+size, y, size);
        Calc(x+size, y+size, size);
        Calc(x+size, y+2*size, size);
        Calc(x+2*size, y, size);
        Calc(x+2*size, y+size, size);
        Calc(x+2*size, y+2*size, size);


    }

    public static boolean isSame(int x, int y, int size) {
        int type = nums[x][y];
        for (int i = x; i < x+size; i++) {
            for (int j = y; j < y+size; j++) {
                if (nums[i][j] != type) {
                    return false;
                }
            }
        }

        cnt[type+1] += 1;
        return true;
    }


}
