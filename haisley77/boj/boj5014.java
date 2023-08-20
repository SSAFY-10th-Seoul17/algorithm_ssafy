import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj5014 {
	private static StringBuilder sb = new StringBuilder();
	private static boolean[] arr;
	private static int res;
	private static int G;
	private static int U;
	private static int D;
	private static int F;
	private static int S;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		// 방문처리 배열
		arr = new boolean[F+1];
		
		go(S);
		
		System.out.println(sb.toString());
	}
	
	public static void go(int s) {
		if (arr[s]) {	// 이미 방문했던 층이라면
			sb.append("use the stairs");
			return;
		}
		arr[s] = true;	// 방문 처리
		
		
		if (s == G) {	// 원하는 층에 도달하면
			sb.append(res);
			return;
		} else if (s < G) {		// 원하는 층이 현재 층보다 더 높은 층이면
			res++;
			if (s+U <= F) go(s+U);		// 1UP 했을 때 최고 층수 밑이라먼
			else if (s-D >= 0) go(s-D);
			else go(s);
		} else {				// 원하는 층이 현재 층보다 더 낮은 층이면
			res++;
			if (s-D >= 1) go(s-D);		// 1DOWN 했을 때 최저 층수 위리면
			else if (s+U <= F) go(s+U);
			else go(s);
		}

		
	}

}
