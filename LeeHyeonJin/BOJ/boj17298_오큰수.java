import java.io.*;
import java.util.*;

// 아이디어 :
// "Stack"을 통해 무작위로 나열된 숫자들을 일정한 규칙으로 거른다.
// 1. int 배열의 숫자를 왼쪽부터 차례대로 하나씩 push()한다.
// 2. 넣은 숫자보다 더 큰 숫자가 나오면 해당 숫자보다 작은 숫자들은 pop()한다.
//    - 이때, pop 한 숫자 인덱스의 값에 pop 한 원인이되는 숫자를 맵핑해준다.
public class boj17298_오큰수 {
	static int N;
	static Node[] nums;
	static int[] results;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// input
		N = Integer.parseInt(br.readLine());
		nums = new Node[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) nums[i] = new Node(Integer.parseInt(st.nextToken()), i);

		// solution
		results = new int[N];
		STACK();

		// output
		for(int result : results) sb.append(result).append(" ");
		System.out.println(sb.toString());
	}

	static void STACK() {
		Stack<Node> stack = new Stack<>();
		for(int i=0; i<N; i++) {
			while (!stack.isEmpty() && stack.peek().num < nums[i].num) {
				Node num = stack.pop();
				results[num.idx] = nums[i].num;
			}
			stack.push(new Node(nums[i].num, nums[i].idx));
		}
		while(!stack.isEmpty()) {
			Node num = stack.pop();
			results[num.idx] = -1;
		}
	}

	static class Node {
		int num, idx;

		public Node(int num, int idx) {
			this.num = num;
			this.idx = idx;
		}
	}
}
