import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1992 {
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] image = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                image[i][j] = str.charAt(j) - '0';
            }
        }

        parseImage(image, 0, 0, n);
        System.out.println(answer);
    }

    static void parseImage(int[][] image, int x, int y, int l) {
        if (!needParsing(image, x, y, l)) {
            answer.append(image[x][y]);
            return;
        }

        answer.append("(");

        l /= 2;
        for (int i = 0; i < 4; i++) {
            parseImage(image, x + ((i / 2) * l), y + ((i % 2) * l), l);
        }

        answer.append(")");
    }

    static boolean needParsing(int[][] image, int x, int y, int l) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                if (image[x + i][y + j] != image[x][y]) {
                    return true;
                }
            }
        }

        return false;
    }
}

