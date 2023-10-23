import java.util.*;
import java.io.*;

public class Main {
    public static int[][] map, visited, direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static Queue<int[]> sheep = new ArrayDeque<>();
    public static int resSheepCnt, resWolfCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken()), col = Integer.parseInt(st.nextToken());
        map = new int[row][col];
        visited = new int[row][col];
        ArrayList<int[]> wolf = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < col; j++) {
                if(input[j] == '.') map[i][j] = 0;
                else if(input[j] == '#') map[i][j] = 1;
                else if(input[j] == 'o') {
                    map[i][j] = 2;
                    sheep.offer(new int[]{i, j});
                }
                else if(input[j] == 'v') {
                    map[i][j] = 3;
                    wolf.add(new int[]{i, j});
                }
            }
        }
        for (int[] pos : sheep) {
            if(visited[pos[0]][pos[1]] == 1) continue;
            visited[pos[0]][pos[1]] = 1;
            bfs(pos);
        }
        for (int[] pos : wolf) {
            if(visited[pos[0]][pos[1]] == 0) resWolfCnt++;
        }
        System.out.println(resSheepCnt + " " + resWolfCnt);
        br.close();
    }

    public static void bfs(int[] pos) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(pos);
        int sheepCnt = 1, wolfCnt = 0;
        while (!queue.isEmpty()) {
            int[] p = queue.poll();

            for (int i = 0; i < direction.length; i++) {
                int nextR = p[0] + direction[i][0];
                int nextC = p[1] + direction[i][1];

                if(visited[nextR][nextC] == 0 && map[nextR][nextC] != 1) {
                    visited[nextR][nextC] = 1;
                    sheepCnt += map[nextR][nextC] == 2 ? 1 : 0;
                    wolfCnt += map[nextR][nextC] == 3 ? 1 : 0;
                    queue.offer(new int[]{nextR, nextC});
                }
            }
        }
        resSheepCnt += sheepCnt > wolfCnt ? sheepCnt : 0;
        resWolfCnt += sheepCnt <= wolfCnt ? wolfCnt : 0;
    }
}