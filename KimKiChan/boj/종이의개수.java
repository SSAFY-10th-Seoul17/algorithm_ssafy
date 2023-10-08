package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1780번
public class 종이의개수 {
	private static int[] count;
	private static int[][] matrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//3^7 = 2187
		int n = Integer.parseInt(br.readLine());
		
		matrix = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 입력 끝
		count = new int[3];
		
		sol(0, 0, n); // 시작 x, y, 길이
		
		for(int i = 0; i < 3; i++) {
			System.out.println(count[i]);
		}
		
	}

	private static void sol(int x, int y, int len) {
		int trim = len/3;
		
		int sum = 0; // -1, 0, 1 확인 
		// 시작지점
		boolean flag = true; // 숫자가 달라질 경우
		int temp = matrix[x][y]; // 확인용 숫자
		for(int i = x; i < x + len; i++) {
			for(int j = y; j < y + len; j++) {
				if(matrix[i][j] != temp) {
					flag = false;
					break;
				}
				sum += matrix[i][j];
			}
			if(!flag) break;
		}
		if(flag) {
			if(sum == 0) {
				count[1]++;
			}else if(sum == -1 * len * len) {
				count[0]++;
			}else if(sum == len * len){
				count[2]++;
			}
		}
		else {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					sol(x + trim*i, y + trim*j, trim);
				}
			}
			
		}
		
	}
	
}
