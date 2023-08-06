import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14500 { // 테토로미노

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] arr = new int [n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}		
		tetromino(n, m, arr);		
	}
	
	
	static public void tetromino(int n, int m, int[][] arr) {
		int max = 0;
		
		int v1 = tetromino1(n, m, arr);
		max = (max < v1) ? v1 : max;
		
		int v2 = tetromino2(n, m, arr);
		max = (max < v2) ? v2 : max;
		
		int v3 = tetromino3(n, m, arr);
		max = (max < v3) ? v3 : max;
		
		int v4 = tetromino4(n, m, arr);
		max = (max < v4) ? v4 : max;
		
		int v5 = tetromino5(n, m, arr);
		max = (max < v5) ? v5 : max;
		
		System.out.println(max);		
	}
	
		static public int tetromino1(int n, int m, int[][]arr) {
		int sum = 0;
		int max = 0;
		// 테트로미노 1번 : 가로 길이 4
		// 첫 원소 i 기준 검색, i+4가 배열 가로 길이(m) 이내
		// (0,0) ~ (0, m-5), ..., (n-1, 0), ..., (n-1, m-5)
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(j+3 < m) { // 가로 
					sum = arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i][j+3];
					max = (max < sum) ? sum : max;
				}
				if(i+3 < n) { // 세로
					sum = arr[i][j] + arr[i+1][j] + arr[i+2][j] + arr[i+3][j];
					max = (max < sum) ? sum : max;
				}
			}
		}
		
		return max;
	}

	static public int tetromino2(int n, int m, int[][]arr) {
		int sum = 0;
		int max = 0;
		
//		테트로미노 2번 : 정사각형, 가로+1, 세로+1, 가로+1세로+1
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(i+1 < n && j+1 < m) {
					sum = arr[i][j] + arr[i+1][j] + arr[i][j+1] + arr[i+1][j+1];
					max = (max < sum) ? sum : max;
				}
				
			}
		}
		
		return max;
	}
	
	static public int tetromino3(int n, int m, int[][]arr) {
		int sum = 0;
		int max = 0;
		
//		테트로미노 3번 L자 모양 8가지 모양
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				//1번 ---|위
				if(i-1 >= 0 && j+2 < m) {
					sum = arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i-1][j+2];
					max = (max < sum) ? sum : max;
				}
				//2번---|아래
				if(i+1 < n && j+2 <m) {
					sum = arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i+1][j+2];
					max = (max < sum) ? sum : max;
				}
				//3번|---위
				if(i+1 < n && j+2 < m) {
					sum = arr[i][j] + arr[i+1][j] + arr[i+1][j+1] + arr[i+1][j+2];
					max = (max < sum) ? sum : max;
				}
				//4번|---아래
				if(i-1 >= 0 && j+2 < m) {
					sum = arr[i][j] + arr[i-1][j] + arr[i-1][j+1] + arr[i-1][j+2];
					max = (max < sum) ? sum : max;
				}
				//5번 _|||
				if(i-2 >= 0 && j+1 < m) {
					sum = arr[i][j] + arr[i][j+1] + arr[i-1][j+1] + arr[i-2][j+1];
					max = (max < sum) ? sum : max;
				}
				//6번 |||_
				if(i+2 < n && j+1 < m) {
					sum = arr[i][j] + arr[i+1][j] + arr[i+2][j] + arr[i+2][j+1];
					max = (max < sum) ? sum : max;
				}
				//7번-|||
				if(i+2 < n && j+1 < m) {
					sum = arr[i][j] + arr[i][j+1] + arr[i+1][j+1] + arr[i+2][j+1];
					max = (max < sum) ? sum : max;
				}
				//8번 |||-
				if(i-2 >= 0 && j+1 < m) {
					sum = arr[i][j] + arr[i-1][j] + arr[i-2][j] + arr[i-2][j+1];
					max = (max < sum) ? sum : max;
				}
			}
		}
		return max;
	}
	
	static public int tetromino4(int n, int m, int[][]arr) {
		int sum = 0;
		int max = 0;
		
		//4가지
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				//1번
				if(i+2<n && j+1<m) {
					sum = arr[i][j] + arr[i+1][j] + arr[i+1][j+1] + arr[i+2][j+1];
					max = (max < sum) ? sum : max;
				}
				//2번
				if(i+2<n && j-1>=0) {
					sum = arr[i][j] + arr[i+1][j] + arr[i+1][j-1] + arr[i+2][j-1];
					max = (max < sum) ? sum : max;
				}
				//3번
				if(i-1>=0 && j+2<m) {// _|-
					sum = arr[i][j] + arr[i][j+1] + arr[i-1][j+1] + arr[i-1][j+2];
					max = (max < sum) ? sum : max;
				}
				//4번
				if(i+1<n && j+2<m) {// -|_
					sum = arr[i][j] + arr[i][j+1] + arr[i+1][j+1] + arr[i+1][j+2];
					max = (max < sum) ? sum : max;
				}
			}	
		}
		return max;
	}
	
	static public int tetromino5(int n, int m, int[][]arr) {
		int sum = 0;
		int max = 0;
		//4가지
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(i-1>=0 && j+2<m) { // ㅗ
					sum = arr[i][j] + arr[i][j+1] + arr[i-1][j+1] + arr[i][j+2];
					max = (max < sum) ? sum : max;
				}
				if(i+1<n && j+2<m) { // ㅜ
					sum = arr[i][j] + arr[i][j+1] + arr[i+1][j+1] + arr[i][j+2];
					max = (max < sum) ? sum : max;
				}
				if(i+2<n && j+1<m) { // ㅏ
					sum = arr[i][j] + arr[i+1][j] + arr[i+1][j+1] + arr[i+2][j];
					max = (max < sum) ? sum : max;
				}
				if(i+1<n && i-1>= 0 && j+1<m ) { // ㅓ
					sum = arr[i][j] + arr[i][j+1] + arr[i+1][j+1] + arr[i-1][j+1];
					max = (max < sum) ? sum : max;
				}
			}
		}	
		return max;
	}	
}