import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Person {
		Person p;
		int no;

		public Person(Person p, int no) {
			this.p = p;
			this.no = no;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		Person[] friends = new Person[n + 1];
		
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p1 = Integer.parseInt(st.nextToken());
			int p2 = Integer.parseInt(st.nextToken());
			friends[p1] = new Person(friends[p1], p2);
			friends[p2] = new Person(friends[p2], p1);
		}
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] connected = new boolean[n+1];
		connected[1]=true;
		int cnt = 0;
		Person iter = friends[1];
		while(iter!=null) {
			cnt++;
			connected[iter.no] = true;
			queue.offer(iter.no);
			iter = iter.p;
		}
		while(!queue.isEmpty()) {
			int no = queue.poll();
			Person cur = friends[no];
			while(cur!=null) {
				if(!connected[cur.no]) {
					connected[cur.no] = true;
					cnt++;
				}
				cur = cur.p;
			}
		}
		System.out.println(cnt);
	} // end of main
} // end of class