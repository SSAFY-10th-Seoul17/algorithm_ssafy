import java.util.*;
import java.io.*;

class Main {
    public static int[][][] tetDir = {
            {{0,0},{0,-1},{0,1},{-1,0}},
            {{0,0},{0,-1},{0,1},{1,0}},
            {{0,0},{0,-1},{-1,0},{1,0}},
            {{0,0},{0,1},{-1,0},{1,0}}
    };
    public static int[][] map,visited, direction = {{1,0},{-1,0},{0,1},{0,-1}};
    public static int row, col, max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        visited = new int[row][col];

        for(int i=0; i<row; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                visited[i][j] = 1;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = 0;
            }
        }

        // -|- 테트로미노
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) { // i, j가 가운데 칸이라고 생각
                tetSum(i, j);
            }
        }
        System.out.println(max);
        br.close();
    }

    public static void dfs(int r, int c, int cnt, int sum) {
        if(cnt == 4) {
            max = Math.max(max, sum);
            return;
        }

        for(int i=0; i<direction.length; i++) {
            int nextR = r + direction[i][0];
            int nextC = c + direction[i][1];

            if(checkPos(nextR, nextC) && visited[nextR][nextC] == 0) {
                visited[nextR][nextC] = 1;
                dfs(nextR, nextC, cnt+1, sum+map[nextR][nextC]);
                visited[nextR][nextC] = 0;
            }
        }
    }

    public static void tetSum(int r, int c) {
        for(int i=0; i<tetDir.length; i++) {
            int sum = 0;
            for(int j=0; j<tetDir[i].length; j++) {
                int nextR = r + tetDir[i][j][0];
                int nextC = c + tetDir[i][j][1];
                if(checkPos(nextR, nextC)) sum += map[nextR][nextC];
            }
            max = Math.max(max, sum);
        }
    }

    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }
}