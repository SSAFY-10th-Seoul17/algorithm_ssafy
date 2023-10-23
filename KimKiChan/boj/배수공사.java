import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//15817번
public class 배수공사 {
	private static class Pipe{
		int len;
		int quantity;

		public Pipe(int len, int quantity) {
			this.len = len;
			this.quantity = quantity;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//파이프의 종류 N
		int N = Integer.parseInt(st.nextToken());
		//만들고 싶은 파이프의 길이
		int pipeLen = Integer.parseInt(st.nextToken());
		
		Pipe[] pipes = new Pipe[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int len = Integer.parseInt(st.nextToken()); // 길이
			int quantity = Integer.parseInt(st.nextToken()); // 개수
			
			pipes[i] = new Pipe(len, quantity);
		}
		
		br.close();//입력 끝
		
		int[] dp = new int[pipeLen+1];
		dp[0] = 1;
		
		for(int i = 0; i < N; i++) {
			int len = pipes[i].len;
			int quantity = pipes[i].quantity;
			
			for(int j = pipeLen; j > 0; j--) {//파이프 길이
				for(int k = 1; k <= quantity; k++) {
					if(j - len*k >= 0) {
						dp[j] += dp[j - len*k];
					}else break;
				}
			}
		}
		
		System.out.println(dp[pipeLen]);
		
	}
}
