import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj10973 {
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
			if(prevPermutation()) {
				for(int i = 0; i < n; i++) {
					sb.append(numList[i]).append(" ");
				}
			}else {
				sb.append(-1);
			}
		}
		else {
			sb.append(-1);
		}

		System.out.println(sb.toString());
		
	}// end of main
	
	public static boolean prevPermutation() {
		int idxLeft = n-1;
		int idxRight = n-1;
		
		while(numList[idxLeft-1] < numList[idxLeft]) { // 3 1 2, idxLeft = 1
			idxLeft--;
			if(idxLeft <= 0) {
				return false;
			}
		}
		
		while(numList[idxRight] > numList[idxLeft - 1]) {// 3 1 2, idxLeft=1, idxRight=2 
			idxRight--;
		}
		// 2 1 3
		swap(idxLeft-1, idxRight);
		
		// 부분 내림차순 정렬
		idxRight = n-1;
		while(idxLeft < idxRight) {
			swap(idxLeft, idxRight);
			idxLeft++;
			idxRight--;
		}
        
		return true;		
	}
	
	public static void swap(int i, int j) {
		int temp = numList[i];
		numList[i] = numList[j];
		numList[j] = temp;
		
	}
	
	
}// end of class
