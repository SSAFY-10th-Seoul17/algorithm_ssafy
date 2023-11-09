import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//23829번
public class 예쁜초콜릿과숫자놀이 {
	private static final int MOD = 100000;
	private static long maxScore = 0;
	private static int N;
	private static int b;
	private static int c;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		//정수a시작, 화이트면 b더하기, 다크면 c곱하기, 10^5 나머지 연산
		
		sol(a, 0, 0);
		
		System.out.println(maxScore);
		
	}
	private static void sol(long result, int countB, int countC) { // 재귀
		if(countB == N && countC == N) {
			if(result > maxScore) {
				maxScore = result;
			}
			return;
		}
		
		//화이트 +b, 다크 *c
		//N개까지만 가능
		if(countB==countC) { // 두 숫자가 같으면
			//B가 많아져야 한다
			sol((result+b)%MOD, countB+1, countC);
		}else if(countB > countC) { // B가 더 많으면
			//B 또는 C 둘다 증가 가능
			if(countB==N) {//B를 더 이상 못 늘리면
				sol((result*c)%MOD, countB, countC+1);
			}else {
				sol((result+b)%MOD, countB+1, countC);
				sol((result*c)%MOD, countB, countC+1);
			}
		}
		
		
	}
}
