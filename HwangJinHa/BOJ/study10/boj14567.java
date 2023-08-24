import java.io.*;
import java.util.*;

// 선수과목
public class Main {
	static int N, M;
	static ArrayList<LL> graph = new ArrayList<Main.LL>();
	static int[] ans;
	static int[] countEntry;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());

		ans = new int[N+1];
		countEntry = new int[N+1];

		for (int i = 0; i <= N; i++) graph.add(new LL());
		for (int i = 0; i <= N; i++) ans[i] = 1;

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());

			graph.get(a).add(b);
			countEntry[b]++;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (countEntry[i] == 0) {
				q.add(i);
			}
		}
		
		while (!q.isEmpty()) {
			int now = q.poll();
			Node next = graph.get(now).head;
			while (next.next != null) {
				next = next.next;
				int num = next.num;
				ans[num] = Math.max(ans[num], ans[now] + 1);
				if (--countEntry[num] == 0) {
					q.add(num);
				}
			}
		}
		for (int i = 1; i <= N; i++) 
			sb.append(ans[i]).append(" ");
		System.out.println(sb);
	}
	
	static class Node{
		int num;
		Node next;
	}
	
	static class LL {
		Node head;
		Node tail;
		
		public LL() {
			this.head = new Node();
			this.tail = this.head;
		}

		void add(int n) {
			tail.next = new Node();
			tail = tail.next;
			tail.num = n;
		}
	} }
