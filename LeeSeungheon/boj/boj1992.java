import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class boj1992 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        for (int row = 0; row < N; row++) {

            String[] input = br.readLine().split("");

            for (int column = 0; column < N; column++) {
                map[row][column] = Integer.parseInt(input[column]);
            }
        }

        solve(map);
    }

    public static void solve(int[][] map) {

        int standard = map[0][0];
        boolean check = true;

        for (int row = 0; row < map.length; row++) {
            for (int column = 0; column < map.length; column++) {
                if (map[row][column] != standard) {
                    check = false;
                    break;
                }
            }
        }

        if (check) {
            System.out.print(standard);
        } else {
            int[][] map1 = new int[map.length / 2][map.length / 2];
            int[][] map2 = new int[map.length / 2][map.length / 2];
            int[][] map3 = new int[map.length / 2][map.length / 2];
            int[][] map4 = new int[map.length / 2][map.length / 2];

            for (int i = 0; i < map.length / 2; i++) {
                map1[i] = Arrays.copyOfRange(map[i], 0, map.length / 2);
                map2[i] = Arrays.copyOfRange(map[i], map.length / 2, map.length);
                map3[i] = Arrays.copyOfRange(map[i + map.length / 2], 0, map.length / 2);
                map4[i] = Arrays.copyOfRange(map[i + map.length / 2], map.length / 2, map.length);

            }

            System.out.print("(");
            solve(map1);
            solve(map2);
            solve(map3);
            solve(map4);
            System.out.print(")");

        }
    }
}
