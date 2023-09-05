package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 상어초등학교 {
	private static int[] dx = {-1, 0, 0, 1}; // 상좌우하 -> 3번규칙
	private static int[] dy = {0, -1, 1, 0};
	private static int n;
	private static HashMap<Integer, Integer[]> favorite;
	private static int[][] classroom;
	private static int cntFriend;
	private static int[] cntMax;
	private static int[] seat;
	private static int cntEmpty;
	private static boolean foundFriend;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		favorite = new HashMap<>();
		//입력 순서
		Deque<Integer> dq = new ArrayDeque<>();
		
		//입력
		for(int i = 1; i <= n*n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int key = Integer.parseInt(st.nextToken());
			int friend1 = Integer.parseInt(st.nextToken());
			int friend2 = Integer.parseInt(st.nextToken());
			int friend3 = Integer.parseInt(st.nextToken());
			int friend4 = Integer.parseInt(st.nextToken());
			favorite.put(key, new Integer[] {friend1, friend2, friend3, friend4});
			dq.offer(key);
		}
	
		// 인접한 칸
		cntFriend = 0;
		cntEmpty = 0;
		// 최대 비어있는 칸
		cntMax = new int[2];
		// 자리 위치
		seat = new int[2]; 
		// 마음에 드는 친구 찾음
		foundFriend = false;
		// 1. 비어 있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다
		// 2. 1을 만족하는 칸이 여러개면 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
		classroom = new int[n][n];
		int[] tempSeat;
		
		int size = dq.size();
		for(int k = 0; k < size; k++) { // 아직 자리를 정하지 못한 학생
			int student = dq.poll();
			foundFriend = false;
			cntMax = new int[] {0, 0};
			seat = new int[] {-1, -1};
			tempSeat = new int[] {-1, -1};
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(classroom[i][j] == 0) { // 아직 친구를 못 찾고 빈칸일 경우
						if(seat[0] == -1 && tempSeat[0] == -1) {
							tempSeat = new int[] {i, j};
						}
						findSeat2(student, i, j);
					}
				} // end of for j
			}// end of for i
			if(seat[0] == -1) seat = Arrays.copyOf(tempSeat, 2);
			classroom[seat[0]][seat[1]] = student;	
//			printArr();
		}// end of for k
		System.out.println(satisfaction());		
		
	}

	private static void findSeat2(int student, int i, int j) {
		cntFriend = 0;
		cntEmpty = 0;
		for(int dir = 0; dir < 4; dir++) { // 인접 칸
			int nx = i + dx[dir];
			int ny = j + dy[dir];
			if(nx >= 0 && ny >= 0 && nx < n && ny < n) { // 범위 안
				if(classroom[nx][ny] == 0) { // 빈자리
					cntEmpty++;
				}else if(Arrays.asList(favorite.get(student)).contains(classroom[nx][ny])) {
					cntFriend++;
				}
			}	
		}
		if(cntFriend > cntMax[0]) { // 인접 친구가 많을 경우
			cntMax[0] = cntFriend;
			cntMax[1] = cntEmpty;
			seat = new int[] {i, j};
		}else if(cntFriend == cntMax[0] && cntEmpty > cntMax[1]) {// 친구 수는 같지만 공백이 많을 경우
			cntMax[1] = cntEmpty;
			seat = new int[] {i, j};
		}
	}
	
	// 만족도 합 구하기
	private static int satisfaction() {
		int sum = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int cnt = 0;
				int student = classroom[i][j];
				for(int dir = 0; dir < 4; dir++) { // 인접 칸
					int nx = i + dx[dir];
					int ny = j + dy[dir];
					if(nx >= 0 && ny >= 0 && nx < n && ny < n) { // 범위 안
						if(Arrays.asList(favorite.get(student)).contains(classroom[nx][ny])) {
							cnt++;
						}
					}
				} // end of for dir
				if(cnt > 0) { // 만족도 더하기
					// 만족도 = 10 ^ (인접한 칸에 앉은 좋아하는 학생의 수-1), 0명이면 0
					sum += Math.pow(10, cnt-1);
				}

			}// end of for j
		}// end of for i
		
		return sum;
	}
	
	private static void printArr() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				System.out.print(classroom[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
