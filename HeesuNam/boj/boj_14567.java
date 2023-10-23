import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
	private static int[] inDegree;
	private static int N;
	private static int M;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		inDegree = new int[N+1];
		Arrays.fill(inDegree, 0);
		ArrayList<ArrayList<Integer>> courses = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			courses.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			courses.get(A).add(B);
			inDegree[B]++;
		}
		Queue<int[]>queue = new ArrayDeque<>();
		for (int i = 1; i <= N; i++) {
			if(inDegree[i]==0) queue.add(new int[] {i,1});
		}
		
		int[] answer = new int[N+1];
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			answer[cur[0]]=cur[1];
			for(int nxt:courses.get(cur[0])) {
				if(--inDegree[nxt]==0) queue.add(new int[] {nxt,cur[1]+1});
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(answer[i]).append(" ");
		}
		System.out.println(sb.toString());
	}
}
