import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj16401 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()," ");
		int[] arr = new int[N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] > max) {
				max = arr[i];
			}
		}

		int s = 1;
		int e = max;
		int mid = (s + e) / 2;
		int res = 0;
		
		while (s <= e) {
			int cnt = 0;
			mid = (s + e) / 2;	// mid 갱신
			for (int i = 0; i < N; i++) {
				cnt += arr[i] / mid;
			}
			
			if (cnt >= M) {
				res = mid;
				s = mid + 1;			// s 갱신
			} else {
				e = mid - 1;		// e 갱신
			}
			
		}
		System.out.println(res);
		
	}

}
