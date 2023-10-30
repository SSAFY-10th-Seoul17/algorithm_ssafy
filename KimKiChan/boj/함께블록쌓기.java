import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//18427번
public class 함께블록쌓기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 학생의 수 N
		int N = Integer.parseInt(st.nextToken());
		// 최대 블록의 개수 M
		int M = Integer.parseInt(st.nextToken());
		// 높이 H
		int H = Integer.parseInt(st.nextToken());
		
		// 학생이 갖는 블록들의 높이
		ArrayList<Integer>[] block = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			block[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for(int j = 0; j < input.length; j++) {
				block[i].add(Integer.parseInt(input[j]));
			}
		}
		
		br.close(); // 입력 끝
		
		int[] height = new int [H+1]; // 높이의 경우의 수 % 10_007
		height[0] = 1;
		
		
		// 각 학생들은 최대 1개의 블록만 사용 가능
		for(int i = 0; i < N; i++) { // 학생
			for(int k = H; k >= 0; k--) { // 블록의 높이
				for(int j = 0; j < block[i].size(); j++) { // 블록의 높이
					int blockH = block[i].get(j); // 학생 i의 j번째 블록의 높이
					if(k-blockH >= 0) {
						height[k] += height[k-blockH];
						height[k] %= 10007;
					}else break;
				}
			}
		}
		
		System.out.println(height[H]);
		
	}// end of main
}// end of class
