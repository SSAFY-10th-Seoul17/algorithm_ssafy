import java.io.*;
import java.util.*;

public class boj11729 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static List<String> results = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		// input
		int n = Integer.parseInt(br.readLine());

		// solution : 재귀호출
		hanoi(n, 1, 2, 3);

		// output
		bw.write(results.size()+"\n");
		for(String result : results) {
			bw.write(result+"\n");
		}
		bw.flush();
		bw.close();
	}

	static void hanoi(int n, int from, int mid, int to) {
		if(n == 0) {
			return;
		}
		// A -> B : 가장 큰 원판보다 작은 애들 임시로 이동
		hanoi(n-1, from, to, mid);
		// A -> C : 시작점에 남은 원판 1개(현재 제일 큰거) 목적지로 이동
		results.add(from+" "+to);
		// B -> C : 임시에 옮겨놓은 애들 다시 목적지로 가도록
		hanoi(n-1, mid, from, to);
	}
}
