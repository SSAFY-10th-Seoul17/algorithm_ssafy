
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			// 입력
			st = new StringTokenizer(br.readLine());
			int Ano = Integer.parseInt(st.nextToken());  // A 수
			int Bno = Integer.parseInt(st.nextToken());  // B 수
			
			int[] A = new int[Ano];
			int[] B = new int[Bno];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < A.length; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < B.length; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			
			// 정렬
			Arrays.sort(A);
			Arrays.sort(B);
			
			// 투 포인터
			int i = 0;  // A 인덱스
			int j = 0;  // B 인덱스
			int cnt = 0;  // 큰 쌍의 개수
			while (true) {
				if (i == Ano || j == Bno) break;
				if (A[i] > B[j]) {
					cnt += Ano - i;
					j++;
				} else {
					i++;
				}
			}
			System.out.println(cnt);
		}
		
	}
}
