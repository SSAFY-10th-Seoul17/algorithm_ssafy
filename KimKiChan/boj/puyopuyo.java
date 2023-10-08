package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

//11559번
public class puyopuyo {
	private static int numPuyo = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[][] field = new char[12][6];
		for(int i = 0; i < 12; i++) {
			String str = br.readLine();
			for(int j = 0; j < 6; j++) {
				field[i][j] = str.charAt(j);
				if(field[i][j] == 'R' || field[i][j] == 'G' || field[i][j] == 'B' || field[i][j] == 'P' || field[i][j] == 'Y') {
					numPuyo++;
				}
				
			}
		}
		//입력 끝
	
		puyo(field);//연쇄반응
		
	}// end of main

	private static void puyo(char[][] field) { 
	
		int explode = 0;
		int count = 0;
		do {
			explode = 0;
			boolean[][] isVisited = new boolean[12][6];
			// 1. field 전체를 돈다
			for (int i = 0; i < 12; i++) {
				for(int j = 0; j < 6; j++) {
					if(!isVisited[i][j]) {
						char c = field[i][j];
						isVisited[i][j] = true;
						// 2. R G B P Y 중 하나를 찾으면 bfs를 돌린다
						if(c == 'R' || c == 'G' || c == 'B' || c == 'P' || c == 'Y') {
							if(bfs(i, j, isVisited, field)) {
								explode++;
							}
						}
					}
				}
				
			}
			if(explode > 0) {
				count++;
			}
			clearField(field);
			
			if(numPuyo < 4) {
				break;
			}
		
		} while (explode > 0);
		
		System.out.println(count);
	}

	private static void clearField(char[][] field) { // 필드 정리
		for(int j = 0; j < 6; j++) { // 왼쪽에서 오른쪽으로
			//아래에서 위로
			for(int i = 11; i >= 0; i--) {
				//현재 위치에 뿌요가 있다면 위에서 가지고 내려온다
				char c = field[i][j];
				if(c != '.') { // 내려가다가 경계선 혹은 뿌요가 있을 경우 그 위에 올린다
					for(int k = i+1; k < 12; k++) {
						if(k == 11 && field[k][j] == '.') { // 바닥에 도착
							field[i][j] = '.';
							field[k][j] = c;
							break;
						}
						if(field[k][j] != '.') { // 뿌요와 충돌
							field[i][j] = '.';
							field[k-1][j] = c;
							break;
						}
					}
				}
			}
			
		}
		
	}

	private static boolean bfs(int x, int y, boolean[][] isVisited, char[][] field) {
		int[][] dxdy = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};//상하좌우
		ArrayList<int[]> sameColor = new ArrayList<>(); // 좌표값
		sameColor.add(new int[] {x, y});
		Deque<int[]> dq = new ArrayDeque<>();
		dq.add(new int[] {x, y});
		while(!dq.isEmpty()) {
			int[] current = dq.poll();
			// 3. 탐색된 같은 색의 뿌요들을 arraylist에 넣는다
			for(int i = 0; i < 4; i++) {
				int nx = current[0] + dxdy[i][0];
				int ny = current[1] + dxdy[i][1];
				if(nx >= 0 && ny >= 0 && nx < 12 && ny < 6) {
					if(!isVisited[nx][ny] && field[nx][ny] == field[x][y]) {
						isVisited[nx][ny] = true;
						sameColor.add(new int[] {nx, ny});
						dq.add(new int[] {nx, ny});
					}
				}
				
			}
		}
		if(sameColor.size() >= 4) { //같은 색의 뿌요가 4개 이상이면 폭파한다
			for(int i = 0; i < sameColor.size(); i++) {
				field[sameColor.get(i)[0]][sameColor.get(i)[1]] = '.';
			}
			numPuyo -= sameColor.size();
			return true;
		}
		
		return false;		
	}
	
	
	
}//end of class
