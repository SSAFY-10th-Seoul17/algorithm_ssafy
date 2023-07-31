import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * [boj] 4779.칸토어 집합
 */
public class boj4779 {
	static int n, initialLength;
	static char[] cantorArr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = "";
		while((str = br.readLine()) != null) { // 입력이 없을 때까지 반복
			n = Integer.parseInt(str);
			initialLength = (int) Math.pow(3, n); // 3의 n제곱수 구하기
			cantorArr = new char[initialLength];
			for(int i = 0; i < initialLength; i++) {
				cantorArr[i] = '-';
			}
			
			
			dfs(cantorArr, 0, initialLength);
			
			for(int i = 0; i < initialLength; i++) {
				bw.write(cantorArr[i]);
//					bw.flush();
			}
			
			bw.write("\n");
			bw.flush();
		}
		
		bw.close();
	}
	
	public static void dfs(char[] cantorArr, int start, int length) {
		if(length == 1) {
			return;
		}
		
		int newLength = length / 3;
		
		for(int i = start + newLength; i < start + newLength * 2; i++) {
			cantorArr[i] = ' ';
		}
		
		// 3등분 후 1, 3번째 영역만 재귀.
		dfs(cantorArr, start, newLength);
		dfs(cantorArr, start + newLength * 2, newLength);
	}
}
