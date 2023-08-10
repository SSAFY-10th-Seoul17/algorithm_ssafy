import java.util.*;
import java.io.*;

class Main{
    public static int[][][] map;
    public static int[][] direction = {{0,1,0}, {0,-1,0}, {0,0,1}, {0,0,-1}, {1,0,0}, {-1,0,0}};
    public static Queue<int[]> pos;
    public static int L, R, C;
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            if((L = Integer.parseInt(st.nextToken())) == 0) break;
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            map = new int[L][R][C];
            pos = new LinkedList<>();
            // init
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    char[] input = br.readLine().toCharArray();
                    for (int k = 0; k < C; k++) {
                        if(input[k] == 'S') pos.add(new int[]{i, j, k, 0});
                        else if(input[k] == 'E') map[i][j][k] = 2;
                        else if(input[k] == '#') map[i][j][k] = 1;
                    }
                }
                br.readLine();
            }
            escape();
        }
        System.out.print(sb);
        br.close();
    }

    public static void escape() {
        while(!pos.isEmpty()) {
            int[] p = pos.poll();

            for(int i=0; i<direction.length; i++) {
                int nextL = p[0] + direction[i][0];
                int nextR = p[1] + direction[i][1];
                int nextC = p[2] + direction[i][2];

                if(checkPos(nextL, nextR, nextC)) {
                    if(map[nextL][nextR][nextC] == 2) {
                        sb.append("Escaped in ").append(p[3]+1).append(" minute(s).").append("\n");
                        return;
                    }
                    if(map[nextL][nextR][nextC] == 0) {
                        map[nextL][nextR][nextC] = 1;
                        pos.offer(new int[]{nextL, nextR, nextC, p[3] + 1});
                    }
                }
            }
        }
        sb.append("Trapped!").append("\n");
    }
    public static boolean checkPos(int l, int r, int c) {
        return (l<0 || l>=L || r<0 || r>=R || c<0 || c>=C) ? false : true;
    }

}