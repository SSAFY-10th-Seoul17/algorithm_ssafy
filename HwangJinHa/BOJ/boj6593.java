import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static char[][][] building;
	static int[][][] length;
	
	static int[] dy = {0, 0, 0, 0, 1, -1};
	static int[] dx = {0, 0, 1, -1, 0, 0};
	static int[] dz = {1, -1, 0, 0, 0, 0};

	public static void main(String[] args) throws IOException {
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.valueOf(st.nextToken());
			int r = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());
			
			building = new char[l][r][c];
			length = new int[l][r][c];
			
			if (l == 0 && r == 0 && c == 0)
				break; // 종료
			
			Queue<Integer> qy = new LinkedList<Integer>();
			Queue<Integer> qx = new LinkedList<Integer>();
			Queue<Integer> qz = new LinkedList<Integer>();
			
			for(int i = 0; i < l; i++) {
				for(int j = 0; j < r; j++) {
					String line = br.readLine();
					for(int k = 0; k < c; k++) {
						building[i][j][k] = line.charAt(k);
						length[i][j][k] = Integer.MAX_VALUE;
						if (building[i][j][k] == 'S') {
							length[i][j][k] = 0;
							qz.add(i);
							qy.add(j);
							qx.add(k);
						}
					}
				}
				br.readLine();
			}
			
			int ans = -1;
			while (!qy.isEmpty()) {
				int x = qx.poll();
				int y = qy.poll();
				int z = qz.poll();
				
				for(int i = 0; i < 6; i++) {
					int xx = x + dx[i];
					int yy = y + dy[i];
					int zz = z + dz[i];
					
					if (xx < 0 || yy < 0 || zz < 0 || c <= xx || r <= yy || l <= zz)
						continue;
					if (length[zz][yy][xx] != Integer.MAX_VALUE|| building[zz][yy][xx] == '#') 
						continue;
					
					if(building[zz][yy][xx] == 'E') { // 이거보단 다돌고 한번에 검사하는게 빠르긴 할텐데..
						ans = length[z][y][x] + 1;
						break;
					}
					
					length[zz][yy][xx] = length[z][y][x] + 1;
					qz.add(zz);
					qy.add(yy);
					qx.add(xx);
				}
			}
			if (ans == -1) {
				sb.append("Trapped!\n");
			}else {
				sb.append("Escaped in " + ans + " minute(s).\n");
			}
		}
		System.out.println(sb);
	}
}
