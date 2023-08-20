import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj11729 {
	static int cnt;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		hanoi(N,1,2,3);
		System.out.println(cnt);
		System.out.println(sb);

	}

	public static void hanoi(int n, int departure, int temp, int destination) {
		if (n == 0) {
			return;
		}
		hanoi(n-1,departure,destination,temp);
		sb.append(departure + " " + destination +"\n");
		cnt++;
		hanoi(n-1,temp,departure,destination);
	}
}
