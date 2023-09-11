package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//2666번
public class 벽장문의이동 {
	private static int[][][] move;
	private static int[] nextDoor;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//벽장의 개수
		int n = Integer.parseInt(br.readLine());
		//열려 있는 벽장
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] openDoor = new int[2];
		openDoor[0] = Integer.parseInt(st.nextToken());
		openDoor[1] = Integer.parseInt(st.nextToken());
		
		//사용할 벽장들의 순서
		int use = Integer.parseInt(br.readLine());
		//이동횟수
		move = new int[use][n+1][n+1]; // move[i번째 여는 문][열린문1][열린문2] 이동횟수
		for(int i = 0; i < use; i++) { //이동횟수 초기화
			for(int j = 0; j < n+1; j++) {
				Arrays.fill(move[i][j], -1);
			}
		}

		//열어야 할 문들
		nextDoor = new int[use];
		for(int i = 0; i < use; i++) {
			//열어야 할 문
			nextDoor[i] = Integer.parseInt(br.readLine());
		}
		//문을 이동
		int result = sol(0, openDoor[0], openDoor[1]);
		System.out.println(result);
		
	}

	private static int sol(int idx, int open1, int open2) {
		if(idx == nextDoor.length) { // 다음으로 열어야 할 문이 없다
			return 0;
		}
		if(move[idx][open1][open2] == -1) { // 움직인 적이 없다면
			//1번문 이동
			int door1 = sol(idx+1, nextDoor[idx], open2) + Math.abs(nextDoor[idx] - open1);
			//2번문 이동
			int door2 = sol(idx+1, open1, nextDoor[idx]) + Math.abs(nextDoor[idx] - open2);
			//최소 이동 횟수
			move[idx][open1][open2] = Math.min(door1, door2);			
		}	
		
		return move[idx][open1][open2];
		
	}
}

