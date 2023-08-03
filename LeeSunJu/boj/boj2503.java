import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj2503 {

	static ArrayList<Integer> arr, tmp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		arr = new ArrayList<>();
		tmp = new ArrayList<>();
		permutation();

		String n;
		int s, b, ts, tb;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			n = st.nextToken();
			s = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());


			for (int j = 0; j < arr.size(); j++) {
				ts = 0;
				tb = 0;
				for (int k = 0; k < n.length(); k++) {
					String str1 = n.charAt(k) + "";
					String str2 = arr.get(j) + "";
					if (str2.contains(str1)) {
						if (str2.indexOf(str1) == k) {
							ts++;
						} else {
							tb++;
						}
					}
				}

				if (ts != s || tb != b) {
					tmp.add(arr.get(j));
				}
			}
		}

		for (int i = 0; i < tmp.size(); i++) {
			arr.remove(tmp.get(i));
		}

		System.out.println(arr.size());
	}

	private static void permutation() {
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				if (i == j) continue;
				for (int k = 1; k <= 9; k++) {
					if (k == i || k == j) continue;
					arr.add(i * 100 + j * 10 + k);
				}
			}
		}
	}

}