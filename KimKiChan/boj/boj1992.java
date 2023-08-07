import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1992 {
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// n: 영상의 크기
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		
		// 배열 값 받기
		for (int i = 0; i < arr.length; i++) {
			String str = br.readLine();
			for(int j = 0; j < str.length(); j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		
		quadTree(0, 0, n);
		System.out.println(sb);
		
	}// end of main

	
	public static void quadTree(int a, int b, int len) {
		if(isSameColor(a, b, len)) { // 공간 내 색이 같으면 색을 출력
			sb.append(arr[a][b]);
		} else{
			int half = len/2;
			
			//분할하기
			sb.append("(");
			quadTree(a, b, half); // 왼쪽 위
			quadTree(a, b + half, half); // 오른쪽 위
			quadTree(a + half, b, half); // 왼쪽 아래
			quadTree(a + half, b + half, half); // 오른쪽 아래
			sb.append(")");
		}
	}
	
	public static boolean isSameColor(int a, int b, int len) {
		int color = arr[a][b];
		
		for(int i = a; i < a + len; i++) {
			for(int j = b; j < b + len; j++) {
				if(arr[i][j] != color) { // 공간 내 색이 다르면 false 반환
					return false;
				}
			}
		}
	
		return true;
	}
	
}// end of class
