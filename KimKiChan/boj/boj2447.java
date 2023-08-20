import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj2447 {
	static char[][] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		// n*n : 정사각형 크기
		arr = new char[n][n];
		
		// 빈칸으로 채우기
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				arr[i][j] = ' ';
			}
		}
		
		// 별 찍기
		star(0, 0, n);
		
		// 출력
		for(int i = 0; i < n; i++) {
			bw.write(arr[i]);
			bw.write("\n");
		}
		bw.flush();
		
	}
	
	public static void star(int a, int b, int n) {		
		if(n==1) { // 최소의 크기
			arr[a][b] = '*';
			return;
		}
		
		//분할해서 채우기
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(!(i==1 && j==1)) { // 가운데 공간 제외
					star(a + i*n/3, b + j*n/3, n/3);
				}
			}
		}
		
	}
	
}
