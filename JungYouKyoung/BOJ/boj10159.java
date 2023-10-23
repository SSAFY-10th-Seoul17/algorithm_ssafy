import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_10159저울 {

	private static int[][] matrix;
	private static int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine()); // 물건의 개수
		int m = Integer.parseInt(br.readLine()); // 미리 측정된 물건 쌍의 개수

		matrix = new int[n + 1][n + 1];

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int m1 = Integer.parseInt(st.nextToken()); // 물건1
			int m2 = Integer.parseInt(st.nextToken()); // 물건2

			matrix[m1][m2] = 1; // 간선 표시
		}
		
		for (int i = 1; i < matrix.length; i++) {
			int res = answer(i);
			sb.append(n-res).append("\n");
		}
		System.out.println(sb.toString());
		
		

	}// end main

	private static int answer(int k) {
		int child = 0;
		boolean[] isVisited = new boolean[n+1];
		//자식코드 
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.offer(k); //시작점은 1
		
		while(!queue.isEmpty()) {
			int current = queue.poll();
			
			for (int i = 1; i < matrix.length; i++) {
				if(!isVisited[i] && matrix[current][i] == 1) { //자식코드면
					queue.offer(i); //자식 들어감
					isVisited[i] = true;
					child++;
				}
			}
		}
		
		//부모
		int parent = 0;
		boolean[] isVisited2 = new boolean[n+1];
		//자식코드 
		Queue<Integer> queue2 = new ArrayDeque<Integer>();
		queue2.offer(k); //시작점은 1
		
		while(!queue2.isEmpty()) {
			int current = queue2.poll();
			
			for (int i = 1; i < matrix.length; i++) {
				if(!isVisited2[i] && matrix[i][current] == 1) { //자식코드면
					queue2.offer(i); //자식 들어감
					isVisited2[i] = true;
					parent++;
				}
			}
		}

		return (child + parent + 1);
		
	}

}// end class
