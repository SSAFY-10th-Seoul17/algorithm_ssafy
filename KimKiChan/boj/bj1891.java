import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj1891 {

	private static long x;
	private static long y;
	private static int[] num;

	//입력 : 사분면 조각 자릿수 d, 사분면 조각
	// 		2차원 배열 길이 [2^d][2^d]
	// 	       이동 x(우: +, 좌: -), y(위: +, 아래: -)
	//출력 : 도착 사분면 번호
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 자릿수
		int d = Integer.parseInt(st.nextToken());
		
		//사분면 조각
		num = new int[d];
		
		String str = st.nextToken();
		for(int i = 0; i < num.length; i++) {
			num[i] = str.charAt(i) - '0';
		}
		
		// 이동 방향, 횟수
		// |dy|, |dx| <= 2^50
		// 2^31-1보다 커서 Integer의 범위를 벗어나기 때문에, java.lang.NumberFormatException이 발생
		st = new StringTokenizer(br.readLine(), " ");
		long dy = Long.parseLong(st.nextToken()); // 좌우, 우:+ 좌:-
		long dx = Long.parseLong(st.nextToken()); // 상하, 상:+ 하:-
		
		// 배열의 길이 len
		long len = (long)Math.pow(2, d);

		// 사분면 번호 -> 배열 위치
		findIdx(0, 0, 0, len);
		
		// 이동
		x-=dx;
		y+=dy;
		
		if(x >= 0 && x < len && y >= 0 && y < len) {
			// 배열 위치 -> 사분면 번호
			findPos(0, x, y, len, "");
		}else {
			System.out.println("-1");
		}
		
		
	}// end of main

	// 사분면 번호 찾기
	private static void findPos(int idx, long i, long j, long len, String newPos) {
		if(idx == num.length) {
			System.out.println(newPos);
			return;
		}
		boolean up = i < len/2;
		boolean left = j < len/2;
		
		if(up && !left) { // 오른쪽 위
			findPos(idx+1, i, j - len/2, len/2, newPos + "1");
		}else if(up && left) { // 왼쪽 위 
			findPos(idx+1, i, j, len/2, newPos + "2");
		}else if(!up && left) { // 왼쪽 아래
			findPos(idx+1, i - len/2, j, len/2, newPos + "3");
		}else if(!up && !left) { // 오른쪽 아래
			findPos(idx+1, i - len/2, j - len/2, len/2, newPos+"4");
		}
		
	}

	// 사분면 조각-> 배열 위치 x, y
	private static void findIdx(int idx, long i, long j, long len) {
		if(idx == num.length) {
			x = i;
			y = j;
			return;
		}
		switch (num[idx]) {
		case 1: // 오른쪽 위
			findIdx(idx + 1, i, j + len/2, len/2);
			break;
		case 2: // 왼쪽 위
			findIdx(idx + 1, i, j, len/2);
			break;
		case 3: // 왼쪽 아래
			findIdx(idx + 1, i + len/2, j, len/2);
			break;
		case 4: // 오른쪽 아래
			findIdx(idx + 1, i + len/2, j + len/2, len/2);
			break;

		default:
			System.out.println("Error");
			break;
		}

	}

}// end of class
