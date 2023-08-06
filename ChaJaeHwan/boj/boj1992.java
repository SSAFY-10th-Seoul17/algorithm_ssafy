import java.io.*;

public class boj1992 {

    public static int n;
    public static int[][] arr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        quadTree(0, 0, n);
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
    }

    public static void quadTree(int r, int c, int w) {
        if (isSame(r, c, w)) {
            sb.append(arr[r][c]);
            return;
        }

        sb.append('(');
        w /= 2;
        quadTree(r, c, w);
        quadTree(r, c + w, w);
        quadTree(r+w, c, w);
        quadTree(r+w, c + w, w);
        sb.append(')');

    }

    public static boolean isSame(int r, int c, int w) {
        int p = arr[r][c];

        for (int i = r; i < r+w; i++) {
            for (int j = c; j < c + w; j++) {
                if (p != arr[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
