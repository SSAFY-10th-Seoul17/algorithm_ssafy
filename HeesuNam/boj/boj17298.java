import java.io.*;
import java.util.*;

// 스택
// 왼쪽에서부터 넣기
// 넣으려는 수(num,inx)가 stack peek보다 크면 pop
// 작거나 같으면 stack 넣기
// 다돌면 끝
public class Main {
	static class Pos {
		int num;
		int inx;

		public Pos(int num, int inx) {
			super();
			this.num = num;
			this.inx = inx;
		}	
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		int[] NGE = new int[N];
		Arrays.fill(NGE, -1);
		ArrayDeque<Pos> stack = new ArrayDeque<>();
		stack.add(new Pos(input[0], 0));
		for (int i = 1; i < N; i++) {
			while (!stack.isEmpty() && stack.getLast().num < input[i]) {
				NGE[stack.pollLast().inx] = input[i];
			}
			stack.add(new Pos(input[i],i));
		}
		StringBuilder sb = new StringBuilder();
		for (int n:NGE){
			sb.append(n).append(" ");
		}
		System.out.println(sb.toString());

	} // end of main
} // end of class
