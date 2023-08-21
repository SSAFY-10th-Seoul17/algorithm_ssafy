import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj10972 {
	static int n;
	static int[] numList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder("");
		n = Integer.parseInt(br.readLine());
				
		numList = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			numList[i] = Integer.parseInt(st.nextToken());
		}
		
		if(n > 1) {
			if(nextPermutation()) {
				for(int i = 0; i < n; i++) {
					sb.append(numList[i]).append(" ");
				}
			}else {
				sb.append(-1);
			}
		}else {
			sb.append(-1);
		}

		System.out.println(sb.toString());
		
	}// end of main

	public static boolean nextPermutation() {
		int idxLeft = n-1;
		int idxRight = n-1;
		
		// 순열의 숫자를 바꿀 idx 찾기
		while(numList[idxLeft-1] > numList[idxLeft]) { // 앞 값 < 뒷 값 
			idxLeft--;
			if(idxLeft <= 0) {
				return false;
			}
		}
		while(numList[idxRight] <= numList[idxLeft - 1]) { // 찾은 값  < 뒷 값
			idxRight--;
		}		
		
		int temp = numList[idxLeft-1];
		numList[idxLeft-1] = numList[idxRight];
		numList[idxRight] = temp;
		
		Arrays.sort(numList, idxLeft, n);

		return true;
	}
	
	
}// end of class
