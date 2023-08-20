import java.io.*;
import java.util.*;

import javax.naming.directory.DirContext;

public class Main {
    static int[][] map = new int[5][5];
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static Set<String> sumCnt = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < map.length; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine(), " ");
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String sum = new String();
                DFS(i, j, 0, sum);
            }
        }

        System.out.println(sumCnt.size());
    } // end of main

    private static void DFS(int x, int y, int depth, String sum) {
        if (depth == 6) {
            sumCnt.add(sum);
            return;
        }

        sum += (map[x][y]);
        for (int i = 0; i < dir.length; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];

            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                DFS(nx, ny, depth+1, sum);
            }
        }
    }
} // end of class