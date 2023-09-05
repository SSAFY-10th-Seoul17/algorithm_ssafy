import java.io.*;
import java.util.*;

// small world network
public class Main {
	static int n, k;
	static ArrayList<LL> graph = new ArrayList<>();
	static int[][] dists;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		k = Integer.valueOf(st.nextToken());
		for (int i = 0; i <= n; i++) graph.add(new LL());
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		System.out.println(floyd());
	}

	private static String floyd() {
		dists = new int[n + 1][n + 1];
		final int inf = Integer.MAX_VALUE;

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++)
				dists[i][j] = inf;
		}

		for (int i = 1; i <= n; i++) {
			Node connect = graph.get(i).head;
			dists[i][i] = 0;
			while (connect.next != null) {
				connect = connect.next;
				dists[i][connect.id] = 1;
			}
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				for (int k = 1; k <= n; k++) {
					if (dists[j][i] == inf  || dists[i][k] == inf)
						continue;
					dists[j][k] = Math.min(dists[j][k], dists[j][i] + dists[i][k]);
				}
			}
		}
		
		boolean found = false;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (dists[i][j] > 6) {
					found = true;
					break;
				}
			}
			if (found) break;
		}
		
		return (found)? "Big World!" : "Small World!";
	}
}

class Node {
	int id;
	Node next;

	public Node() {
		this.next = null;
	}

	public Node(int id, Node next) {
		this.id = id;
		this.next = next;
	}
}

class LL {
	Node head;
	Node tail;
	
	LL () {
		head = new Node();
		tail = head;
	}
	
	void add (int n){
		tail.next = new Node(n, null);
		tail = tail.next;
	}
}
