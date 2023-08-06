import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 사탕 박사 고창영
public class BOJ2508 {
	public static void main(String[] args) throws IOException{
		// 가득찬 박스를 연다 
		// 사탕의 개수와 사탕이 없는 곳의 개수를 센다
		// r행 c열 
		// . : 빈곳 , o : 사탕의 먹을 수 있는 , <>v^ : 껍질
		// 사탕의 개수 출력
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		
		for (int tc=0; tc<t; tc++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			char[][] input = new char[r][c];
			int result = 0;
			
			for (int i=0; i<r; i++) {
				String str = br.readLine();

				for (int j=0; j<c; j++) {
					// 세로 체킹을 위해서 배열에 저장해준다.
					input[i][j] = str.charAt(j);
					
					// 범위 체크 && 가로로 3개씩 끊어서 사탕인지 확인한다.
					if (j+3 <= c && str.substring(j,j+3).equals(">o<")) {
						result++;
					}
				}
			}
			
			// 세로 확인			
			for (int j=0; j<c; j++) {
				String str ="";
				for (int i=0; i<r; i++) {
					str += input[i][j]; // 세로줄을 만들어준다.
				}
				
				for (int i=0; i<r; i++) {
					if (i+3 <= r && str.substring(i, i+3).equals("vo^")) {
						result++;
					}
				}
			}
			
			System.out.println(result);
		}

	}
}
