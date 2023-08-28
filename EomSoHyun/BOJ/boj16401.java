
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());  // 조카 수
		int N = Integer.parseInt(st.nextToken());  // 과자 수
		int[] stick = new int[N];  // N개의 과자 길이
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < stick.length; i++) {
			stick[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(stick);
		int start = 1;
		int end = stick[N-1];
		int maxLen = 0;
		
		while (true) {
			int cnt = 0;
			if (start > end) {
				break;
			}
			int mid = (start + end) / 2;
			
			for (int i = 0; i < stick.length; i++) {
				cnt += stick[i] / mid;
			}
			
			if (cnt >= M) {
				start = mid+1;
				maxLen = Math.max(maxLen, mid);
			} else {
				end = mid-1;
			}
		}
		System.out.println(maxLen);
		
		
	}
}
