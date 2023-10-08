import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. 아이디어 - 아래를 기준으로 위에서 큰 수/ 작은 수 더하기 - 마지막 행에서 제일 큰수, 작은수 출력
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] board = new int[3];
		int[] maxdp = new int[3];
		int[] mindp = new int[3];
		int[] maxVal = new int[3];
		int[] minVal = new int[3];
		
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, inx = 0; j < 3; j++, inx += 2) {
				board[j] = str.charAt(inx) - '0';
				switch (j) {
				case 0:
					maxVal[j] = board[j] + Math.max(maxdp[0], maxdp[1]);
					minVal[j] = board[j] + Math.min(mindp[0], mindp[1]);
					break;
				case 1:
					maxVal[j] = board[j] + Math.max(maxdp[2], Math.max(maxdp[0], maxdp[1]));
					minVal[j] = board[j] + Math.min(mindp[2], Math.min(mindp[0], mindp[1]));
					break;
				case 2:
					maxVal[j] = board[j] + Math.max(maxdp[2], maxdp[1]);
					minVal[j] = board[j] + Math.min(mindp[2], mindp[1]);
					break;
				}
			}
			System.arraycopy(maxVal, 0, maxdp, 0, 3);
			System.arraycopy(minVal, 0, mindp, 0, 3);
		}
		System.out.printf("%d %d", Math.max(maxdp[2], Math.max(maxdp[0], maxdp[1])),
				Math.min(mindp[2], Math.min(mindp[0], mindp[1])));
	}
}
