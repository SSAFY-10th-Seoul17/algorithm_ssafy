import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 14502. 연구소
 */
public class boj14502 {
	static int n, m, maxSafetyAreaSize, labSize;
	static int[][] lab, delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] copyLab;
	static boolean possibleNextStep;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		lab = new int[n][m];
		labSize = n * m;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				lab[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0, fSize = labSize - 2; i < fSize ; i++) {
			for(int j = i + 1, sSize = labSize - 1; j < sSize; j++) {
				for(int k = j + 1; k < labSize; k++) {
					copyLab = new int[n][m];
					copyLabArr();
					possibleNextStep = true;
					buildWall(i, j, k);
					if(possibleNextStep) {
						activeVirus();
						int safetyAreaSize = getSafetyAreaSize();
						maxSafetyAreaSize = maxSafetyAreaSize < safetyAreaSize ? safetyAreaSize : maxSafetyAreaSize;
					}
				}
			}
		}
		System.out.println(maxSafetyAreaSize);		
	}
	
	/**
	 * 벽 세우기.
	 */
	public static void buildWall(int i, int j, int k) {
		int ix, iy, jx, jy, kx, ky;
		ix = i / m;
		iy = i % m;
		jx = j / m;
		jy = j % m;
		kx = k / m;
		ky = k % m;
		
		if(!(copyLab[ix][iy] == 0 && copyLab[jx][jy] == 0 && copyLab[kx][ky] == 0)) {
			possibleNextStep = false;
			return;
		}
		
		copyLab[ix][iy] = 1;
		copyLab[jx][jy] = 1;
		copyLab[kx][ky] = 1;
	}
	
	/**
	 * 바이러스 확산 활동.
	 */
	public static void activeVirus() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(copyLab[i][j] == 2) { // 바이러스 칸이면
					// 큐 활용
					Queue<Integer> queueX = new LinkedList<Integer>();
					Queue<Integer> queueY = new LinkedList<Integer>();
					queueX.add(i);
					queueY.add(j);
					
					while(!queueX.isEmpty()) {
						int x = queueX.poll();
						int y = queueY.poll();
						
						for(int k = 0; k < 4; k++) {
							int nextX = x + delta[k][0];
							int nextY = y + delta[k][1];
							
							if(!isInLab(nextX, nextY) || copyLab[nextX][nextY] != 0) {
								continue;
							}
							
							copyLab[nextX][nextY] = 2;
							queueX.add(nextX);
							queueY.add(nextY);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 안전 영역 크기 구하기.
	 */
	public static int getSafetyAreaSize() {
		int size = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(copyLab[i][j] == 0) {
					size++;
				}
			}
		}
		return size;
	}
	
	/**
	 * lab 배열 복사.
	 */
	public static void copyLabArr() {
		for(int i = 0; i < n; i++) {
			System.arraycopy(lab[i], 0, copyLab[i], 0, m);
		}
	}
	
	/**
	 * 해당 좌표가 연구실 범위 내에 있는지 확인.
	 */
	public static boolean isInLab(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}
}
