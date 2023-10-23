import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2004 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		long n = Long.parseLong(st.nextToken());
		long m = Long.parseLong(st.nextToken());
		
		//nCm n! / m!(n-m)!
		//2의 승수, 5의 승수 중 적은 수 
		
		long cnt2 = multOfTwo(n) - multOfTwo(m) - multOfTwo(n-m);
		long cnt5 = multOfFive(n) - multOfFive(m) - multOfFive(n-m);
		
		System.out.println((cnt2 < cnt5) ? cnt2 : cnt5);
		
	}

	public static long multOfTwo(long n) { // 2의 승수 숫자 구하기
		long cnt = 0;
		if(n < 2) {
			return 0;
		}
		while(n >= 2) {
			cnt += n/2;
			n /= 2;
		}
		
		return cnt;
	}
	
	public static long multOfFive(long n) { // 5의 승수 숫자 구하기
		long cnt = 0;
		if(n < 5) {
			return 0;
		}
		while(n >= 5) {
			cnt += n/5;
			n /= 5;
		}
		
		return cnt;
	}


	
}
