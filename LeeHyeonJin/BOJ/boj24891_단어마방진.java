import java.io.*;
import java.util.*;

/**
 * 아이디어 : 백트래킹
 * 1. 입력받은 단어들을 사전순으로 나열한 다음 순서대로 배열에 넣어본다.
 *    - 이때, 나열된 단어들의 가로와 세로가 동일한 단어(마방진 규칙 준수)인 경우 바로 출력한다.
 * 2. 규칙을 준수할 때까지 단어들의 순서를 사전순으로 변경하면서 1번과정을 반복하며 "백트래킹"한다.
 */
public class boj24891_단어마방진 {
	static int L, N;
	static String[] words, mabangjin;
	static boolean[] selected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		words = new String[N];
		mabangjin = new String[L];
		selected = new boolean[N];
		for(int i=0; i<N; i++) words[i] = br.readLine();

		// solution
		Arrays.sort(words);
		DFS(0);

		// output
		System.out.println("NONE");
	}

	static void DFS(int cnt) {
		if(cnt == L) {
			if(check()) {
				for(String word : mabangjin) sb.append(word).append("\n");
				System.out.println(sb.toString());
				System.exit(0);
			}
			return;
		}

		for(int i=0; i<N; i++) {
			if(selected[i]) continue;
			mabangjin[cnt] = words[i];
			selected[i] = true;
			DFS(cnt+1);
			selected[i] = false;
		}
	}

	static boolean check() {
		for(int i=0; i<L; i++) {
			for(int j=0; j<L; j++) {
				if(mabangjin[i].charAt(j) != mabangjin[j].charAt(i)) return false;
			}
		}
		return true;
	}
}
