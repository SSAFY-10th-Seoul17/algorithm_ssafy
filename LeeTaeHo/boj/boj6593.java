import java.io.*;
import java.util.*;

public class boj6593 {
    static int[][][] arr;
    static int L, R, C;
    static int[] end;
    static int[][] dir = {{0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}, {-1, 0, 0}, {1, 0, 0}};
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            
            if(L == 0 && R == 0 && C == 0) break;
            
            arr = new int[L][R][C];
            int[] start = new int[3]; 
            end = new int[3];
            for(int i = 0; i < L; i++) {
                for(int j = 0; j < R; j++) {
                    char[] chArr = br.readLine().toCharArray();
                    for(int k = 0; k < C; k++) {
                        char token = chArr[k];
                        if(token == 'S') {
                            start[0] = i;
                            start[1] = j;
                            start[2] = k;
                            arr[i][j][k] = -1;
                        }else if(token == 'E') {
                            end[0] = i;
                            end[1] = j;
                            end[2] = k;
                            arr[i][j][k] = -1;
                        } else if(token == '.') {
                            arr[i][j][k] = -1;
                        }
                    }
                }
                br.readLine();
            }
            bfs(start);
            if(arr[end[0]][end[1]][end[2]] == 0) {
            	sb.append("Trapped!");
            }else {
            	sb.append("Escaped in ").append(arr[end[0]][end[1]][end[2]]).append(" minute(s).");
            }
        	System.out.println(sb.toString());
        	sb.setLength(0);
        }
    }
    
    public static void bfs(int[] start) {
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(start);
        Loop:
        while(!q.isEmpty()) {
            int[] nextXYZ = q.poll();
            int nextX = nextXYZ[0];
            int nextY = nextXYZ[1];
            int nextZ = nextXYZ[2];
            for(int[] dXYZ : dir) {
                int dx = nextX + dXYZ[0];
                int dy = nextY + dXYZ[1];
                int dz = nextZ + dXYZ[2];
                if(0 <= dx && dx < L && 0 <= dy && dy < R && 0 <= dz && dz < C && arr[dx][dy][dz] == -1) {
                	if(dx == end[0] && dy == end[1] && dz == end[2]) {
                    	arr[dx][dy][dz] = arr[nextX][nextY][nextZ] + 1;
                        break Loop;
                    }
                    arr[dx][dy][dz] = arr[nextX][nextY][nextZ] + 1;
                    q.add(new int[] {dx, dy, dz});
                }
            }
            arr[nextX][nextY][nextZ] = 0;
        }
        arr[end[0]][end[1]][end[2]] += 1;
    } 
}
