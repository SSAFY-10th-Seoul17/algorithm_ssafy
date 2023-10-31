import java.io.*;
import java.util.*;

// 단어 정렬 String
// 조합 L개뽑기 -> 첫번째 문자열 박아놓고 cnt-1 [cnt-1][0] = [0][cnt-1]같은 애만 일단뽑기
// L개되면 for r=1부터 대각선 문자열 검사 inx+1부터 검사시작
// 

public class Main {
	private static int L, N;
	private static String[] inputs, ans;
	private static StringBuilder sb = new StringBuilder();
	private static boolean[] used;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		inputs = new String[N];
		for (int i = 0; i < N; i++) {
			inputs[i] = br.readLine();
		}
		Arrays.sort(inputs);
		ans = new String[L];
		used = new boolean[N];
		for (int i = 0; i < N; i++) {
			used[i] = true;
			ans[0] = inputs[i];
			if (combi(1))
				break;
			used[i] = false;
		}
		System.out.println(sb.length() == 0 ? "NONE" : sb.toString());
	} // end of main

	private static boolean combi(int cnt) {
		if (cnt == L) {
			if (chkBoard()) {
				for (int i = 0; i < L; i++) {
					sb.append(ans[i]).append("\n");
				}
				return true;
			}
			return false;
		}
		for (int i = 0; i < N; i++) {
			if (!used[i] && ans[0].charAt(cnt) == inputs[i].charAt(0)) {
				used[i] = true;
				ans[cnt] = inputs[i];
				if (combi(cnt + 1)) {
					return true;
				}
				used[i] = false;
			}
		}
		return false;

	}

	private static boolean chkBoard() {
		for (int i = 1; i < L; i++) {
			for (int j = i + 1; j < L; j++) {
				if (ans[i].charAt(j) != ans[j].charAt(i))
					return false;
			}
		}
		return true;
	}
} // end of class
