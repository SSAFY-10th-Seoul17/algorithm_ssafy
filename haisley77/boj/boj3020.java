import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj3020 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[] up = new int[H];
		int[] down = new int[H];
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				down[Integer.parseInt(br.readLine())-1]++;
			} else {
				up[H-Integer.parseInt(br.readLine())]++;
			}
		}
		int[] arr = new int[H];
		int tmp = 0;
		for (int i = H-1; i >= 0; i--) {
			tmp += down[i];
			arr[i] += tmp;
		}
		tmp = 0;
		for (int i = 0; i < H; i++) {
			tmp += up[i];
			arr[i] += tmp;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < H; i++) {
			if (min > arr[i]) {
				min = arr[i];
			}
		}
		int cnt = 0;
		for (int i = 0; i < H; i++) {
			if (arr[i] == min) {
				cnt++;
			}
		}
		System.out.println(min + " " + cnt);
	}

}
