import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj1780 {
	private static int[][] paper;
	private static int[] cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		cnt = new int[3];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		count(0,0,N);
		for (int c : cnt) {
			System.out.println(c);
		}
	}
	private static void count(int si, int sj, int len) {
		if (len == 1) {
			cnt[paper[si][sj]+1]++;
			return;
		}
		boolean flag = true;
		int num = paper[si][sj];
		fofo:
		for (int i = si; i < si + len; i++) {
			for (int j = sj; j < sj + len; j++) {
				if (paper[i][j] != num) {
					flag = false;
					break fofo;
				}
			}
		}
		if (flag) {
			cnt[num+1]++;
			return;
		} else {
			len /= 3;
			for (int i = 0; i < len*3; i += len) {
				for (int j = 0; j < len*3; j += len) {
					count(si+i,sj+j,len);
				}
			}
		}
	}
}
