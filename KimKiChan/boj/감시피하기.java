package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 감시피하기 {
	
	private static int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상하좌우
	private static int n;
	private static char[][] map;
	private static ArrayList<int[]> teacher;
	private static boolean possible;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//T: 선생님 O:장애물 S:학생 X:빈칸
		n = Integer.parseInt(br.readLine());
		
		teacher = new ArrayList<>();
		map = new char[n][n];
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0, index = 0; j < n; j++, index+=2) {
				map[i][j] = str.charAt(index);
				if(map[i][j] == 'T') {
					teacher.add(new int[] {i, j});
				}
			}
		}
		// 입력 완료
		boolean flag = true;
		possible = false;
		//3개의 장애물을 설치하여 모든 학생들을 감시로부터 피하도록 할 수 있는지의 여부
		//학생과 선생이 인접하면 불가능
		for(int i = 0; i < teacher.size(); i++) {
			for(int j = 0; j < 4; j++) {
				int nx = teacher.get(i)[0] + dxdy[j][0];
				int ny = teacher.get(i)[1] + dxdy[j][1];
				if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if(map[nx][ny] == 'S') {
						flag = false;
						break;
					}
				}
			}
		}
		
		if(flag) {
			dfs(0);		
		}

		if(possible) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		
		
	}// end of main

	private static void dfs(int count) {
		if(possible) return;
		if(count == 3) {
			int watch = 0;
			for(int i = 0; i < teacher.size(); i++) {
				if(!isWatching(teacher.get(i)[0], teacher.get(i)[1])) { // 학생이 없으면
					watch++;
				}
			}
			if(watch == teacher.size()) {
				possible = true;
			}
			return;
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 'X') {
					map[i][j] = 'O';
					dfs(count+1);
					map[i][j] = 'X';
				}
			}
		}
		
	}

	private static boolean isWatching(int x, int y) { // 선생님의 시선에 학생이 있는가
		for(int i = 0; i < 4; i++) {
			int nx = x + dxdy[i][0];
			int ny = y + dxdy[i][1];
			while(nx >= 0 && ny >= 0 && nx < n && ny < n) {
				if(map[nx][ny] == 'X') { // 빈칸
					nx += dxdy[i][0];
					ny += dxdy[i][1];
				}else if(map[nx][ny] == 'O' || map[nx][ny] == 'T'){ // 장애물
					break;
				}else { // 학생
					return true;
				}
			}
		}
		return false;
	}
}// end of class
