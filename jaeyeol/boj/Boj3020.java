import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3020 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int n = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());

        int[] up = new int[height + 1];
        int[] down = new int[height + 1];

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                down[Integer.parseInt(br.readLine())]++;
            } else {
                up[Integer.parseInt(br.readLine())]++;
            }
        }

        printMaxHeightAndCount(height, up, down);
    }

    private static void printMaxHeightAndCount(int height, int[] up, int[] down) {
        for (int i = 1; i <= height; i++) {
            up[i] += up[i - 1];
            down[i] += down[i - 1];
        }

        int result = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 1; i <= height; i++) {
            int wall = countWall(up, down, height, i);
            if (wall < result) {
                result = wall;
                count = 1;
            } else if (wall == result) {
                count++;
            }
        }

        System.out.println(result + " " + count);
    }

    private static int countWall(int[] up, int[] down, int totalHeight, int curHeight) {
        return up[totalHeight] - up[curHeight - 1] + down[totalHeight] - down[totalHeight - curHeight];
    }

}

