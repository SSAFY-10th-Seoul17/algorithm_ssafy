import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj11729 {

	static StringBuilder sb;
	static int total;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = Integer.parseInt(br.readLine());
		sb = new StringBuilder();

		total = 0;
		hanoi(cnt, 1, 2, 3);
		System.out.println(total);
		System.out.println(sb);
	}

	public static void hanoi(int cnt, int from, int mid, int to) {
		if (cnt == 1) {
			total++;
			sb.append(from + " " + to + "\n");
			return;
		}

		hanoi(cnt - 1, from, to, mid);
		total++;
		sb.append(from + " " + to + "\n");
		hanoi(cnt - 1, mid, from, to);

	}
}
