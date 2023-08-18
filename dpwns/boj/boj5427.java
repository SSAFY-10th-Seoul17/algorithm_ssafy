import java.util.*;
import java.io.*;

class Main{
    public static int[][] map, visited, direction = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static Queue<int[]> fire, pos;
    public static int row, col;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int T=0; T<t; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());

            map = new int[row][col];
            visited = new int[row][col];
            fire = new LinkedList<>();
            pos = new LinkedList<>();
            for (int i = 0; i < row; i++) {
                char[] input = br.readLine().toCharArray();
                for (int j = 0; j < col; j++) {
                    // 0은 빈 공간, 1은 불, 2는 벽
                    if(input[j] == '#') map[i][j] = 2;
                    else if(input[j] == '@') {
                        pos.offer(new int[]{i, j, 0});
                        visited[i][j] = 1;
                    }
                    else if(input[j] == '*') {
                        map[i][j] = 1;
                        fire.offer(new int[]{i, j});
                    }
                }
            }
            escape();
        }
        System.out.print(sb);
        br.close();
    }

    public static void escape() {
        while(!pos.isEmpty()) {
            Queue<int[]> nextFire = new LinkedList<>();
            while(!fire.isEmpty()) {
                int[] f = fire.poll();
                for(int i=0; i<direction.length; i++) {
                    int nextR = f[0] + direction[i][0];
                    int nextC = f[1] + direction[i][1];

                    if(checkPos(nextR, nextC) && map[nextR][nextC] == 0) {
                        map[nextR][nextC] = 1;
                        nextFire.offer(new int[]{nextR, nextC});
                    }
                }
            }
            fire = nextFire;
            // 상근이 이동
            Queue<int[]> nextPos = new LinkedList<>();
            while(!pos.isEmpty()) {
                int[] p = pos.poll();
                for (int i = 0; i < direction.length; i++) {
                    int nextR = p[0] + direction[i][0];
                    int nextC = p[1] + direction[i][1];
                    if (!checkPos(nextR, nextC)) {
                        sb.append(p[2] + 1).append("\n");
                        return;
                    } else {
                        if (map[nextR][nextC] == 0 && visited[nextR][nextC] == 0) {
                            visited[nextR][nextC] = 1;
                            nextPos.offer(new int[]{nextR, nextC, p[2] + 1});
                        }
                    }
                }
            }
            pos = nextPos;
        }
        sb.append("IMPOSSIBLE").append("\n");
    }
    public static boolean checkPos(int r, int c) {
        return (r<0 || r>=row || c<0 || c>=col) ? false : true;
    }

}