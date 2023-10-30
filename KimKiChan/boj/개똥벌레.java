package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 개똥벌레 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 길이 n미터, 짝수
		int n = Integer.parseInt(st.nextToken());
		// 높이 h미터
		int h = Integer.parseInt(st.nextToken());
		// 석순 높이의 개수
		int[] obstacleDown = new int[h+1];
		// 종유석 높이의 개수
		int[] obstacleUp = new int[h+1];
		
		for(int i = 0; i < n; i++) {
			int height = Integer.parseInt(br.readLine());
			switch (i%2) {
			case 0:
				obstacleDown[height]++;
				break;
			case 1:
				obstacleUp[height]++;
				break;
			}
		}
		
		//높이 i일 때 부딪히는 횟수
		for(int i = h; i > 0; i--) {
			obstacleDown[i-1] += obstacleDown[i];
			obstacleUp[i-1] += obstacleUp[i];
		}
		
		int minObstacle = n; // 부딪히는 장애물의 최소값
		int minHit = 0; // 최소값의 구간의 수
		
		for(int i = 1; i <= h; i++) { // 높이는 항상 1 이상 h이하
			if(minObstacle > obstacleDown[i] + obstacleUp[h-i+1]) {
				minObstacle = obstacleDown[i] + obstacleUp[h-i+1];
				minHit = 1;
			}
			else if(minObstacle == obstacleDown[i] + obstacleUp[h-i+1]) {
				minHit++;
			}
		}
		
		System.out.println(minObstacle + " " + minHit);
		
	}
	
}

