import java.io.*;

// 수열만들기
// 부분수열 검사
	// 그 전 숫자와 같지 않을것
	// len 4넘을 경우 뒤에서부터 4n+2단위로 좋은 수열인지 검사
	// 좋은 수열 검사 (투포인터로) i==j cnt++ == len/2일경우 return false
public class Main {
	private static int N;
	private static int[] arr;
	private static final int[] ELEMENTS = {1,2,3};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		// 수열만들기
		makeArr(0);
	}

	private static void makeArr(int inx) {
		// 길이가 4 넘을 경우 부분수열검사
		if(inx>=4 && !isGoodArr(inx-1,4)) {
			return;
		}
		if(inx==N) { // 길이N의 수열 완성
			for (int i = 0; i < N; i++) {
				System.out.print(arr[i]);
			}
			System.exit(0);
		}
		for (int i = 0; i < 3; i++) {
			if(inx>0&&arr[inx-1]==ELEMENTS[i])continue;
			arr[inx]=ELEMENTS[i];
			makeArr(inx+1);
		}
		
	}

	private static boolean isGoodArr(int inx, int chkLen) {
		// 좋은 수열 검사 (투포인터로) i==j cnt++ == len/2일경우 return false
		int p1 = inx-chkLen+1; // 첫번째 부분수열 시작
		if(p1<0)return true;
		int half = chkLen/2;
		int p2 = inx-half+1; // 두번째 부분수열 시작
		int cnt = 0;
		for (int i = 0; i < half; i++) {
			if(arr[p1+i]==arr[p2+i]) cnt++;
		}
		if(cnt==half)return false;
		// 4n+2단위로 검사
		return true && isGoodArr(inx,chkLen+2);
	}
}
