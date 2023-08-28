import java.util.*;
import java.io.*;

class boj2508 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        for(int T=0; T<t; T++) {
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            int[][] map = new int[r][c];
            for(int i=0; i<r; i++) {
                map[i] = br.readLine().chars().toArray();
            }
            System.out.println(countCandy(map, r, c));
        }
    }
    public static int countCandy(int[][] map, int r, int c) {
        int cnt = 0;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(map[i][j] == 62 && j + 2 < c) { // >
                    cnt += map[i][j+1] == 111 && map[i][j+2] == 60 ? 1 : 0;
                }
                else if(map[i][j] == 118 && i + 2 < r) {    // v
                    cnt += map[i+1][j] == 111 && map[i+2][j] == 94 ? 1 : 0;
                }
            }
        }
        return cnt;
    }
}
