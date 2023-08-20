import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2508 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		char[][] arr;
		StringTokenizer st;

		while(T > 0) {
			br.readLine();
			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			arr = new char[r][c];

			for (int j = 0; j < r; j++) {
				arr[j] = br.readLine().toCharArray();
			}

			int cnt = 0;
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (arr[i][j] == 'o') {
						if (((i - 1) >= 0 && arr[i - 1][j] == 'v')
							&& ((i + 1) < r && arr[i + 1][j] == '^')) { // 위아래 문자 확인
							cnt++;
						}

						if (((j - 1) >= 0 && arr[i][j - 1] == '>')
							&& ((j + 1) < c && arr[i][j + 1] == '<')) { // 좌우 문자 확인
							cnt++;
						}
					}
				}
			}

			System.out.println(cnt);
			T--;
		}
	}
}