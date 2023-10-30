import java.util.*;
import java.io.*;

/*
 * 아이디어 :
 * 일단 데이터 크기를 봐라.. -> 앞에서부터 규칙대로 반복하면 시간초과난다 + 메모리에 저장하면 메모리초과난다
 * => 위에서부터 분할하면서 탐색범위를 빼주면서 내려와야 한다
 * -> 여기서 주어진 규칙을 잘보면 k번째 문자열은 양끝에 k-1번째 문자열을 두고, 가운데에 k+2개의 o를 가진 문자열이 생성된다
 * -> 그러면 전체 k번째 문자열은 3구간으로 분할할 수 있고, 가운데 부분이 나올때까지 오른쪽 끝 부분만 탐색하면 된다
 *
 * 분할 정복 사고과정
 * - 일단 분할할 moo 문자열을 가상으로 만들어주어야 한다
 * 	- 그럼 결과로 : N 인덱스가 포함될 문자열의 길이와, 가운데 부분의 길이(mid), 양쪽 끝 부분의 길이(side)가 나온다
 * 	- 결과를 바탕으로 가운데 부분의 범위를 파악할 수 있고, 그 가운데 부분이 나올때까지 분할을 할 수 있다
 * - 가운데 범위내에 N이 있다면 이제 그 범위내의 첫번째 인덱스인지(m) 아닌지(o)만 확인하면 된다
 */
public class boj5904 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		int N = Integer.parseInt(br.readLine());

		// solution
		boolean res = divedConquer(N);

		// output
		System.out.println(res ? "m" : "o");
	}

	static boolean divedConquer(int N) {
		int side = 3, mid = 4;
		while(side < N) {
			side = side * 2 + mid;
			mid++;
		}

		int len = side;
		side = (side - (mid-1)) / 2;
		int left = side, right = len - side; // mid 의 범위 : left < mid <= right

		if(left < N && N <= right) { // 현재 시점의 mid 이다
			if(N - left == 1) {
				return true;
			} else {
				return false;
			}
		} else  { // 오른쪽 사이드에 있다
			return divedConquer(N - (side+mid-1));
		}
	}
}
