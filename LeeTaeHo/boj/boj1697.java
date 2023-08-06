import java.io.*;
import java.util.*;

public class boj1697 {
	static int[] arr = new int[100001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		bfs(n, k);
		System.out.println(arr[k]);
	}
	
	public static void bfs(int n, int k) {
		Queue<Integer> q = new LinkedList<Integer>();
		if(n == k) return;
		q.add(n);
		Loop:
		while(!q.isEmpty()) {
			int x = q.poll();
			int[] dx = {x + 1, x - 1, x * 2};
			for(int i : dx) {
				if(i >= 0 && i < 100001 && arr[i] == 0) {
					arr[i] = arr[x] + 1;
					if(i == k) break Loop;
					q.add(i);
				}
			}
		}
	} 
}