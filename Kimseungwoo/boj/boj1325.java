package Algo;

import java.util.*;
import java.io.*;

public class boj1325 {
	
	static ArrayList<ArrayList<Integer>> connectionArr = new ArrayList<>();
	static int[] arr;
	static int size, m;
	static boolean[] visited;
	static int count;
	static int mxVal = -1;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		size = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[size+1];
		
		for(int i = 0; i<= size; i++) {
			connectionArr.add(new ArrayList<>());
		}
		
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			connectionArr.get(to).add(from);
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i<=size; i++) {
			visited = new boolean[size+1];
			count = 0;
			bfs(i);
			arr[i] = count;
			if (count > mxVal) {
				mxVal = count;
			}
		}
		
		for(int i = 1; i<=size; i++) {
			if (arr[i] == mxVal) {
				sb.append(i + " ");
			}
		}
		
		System.out.println(sb);
	}
	
	public static void bfs(int idx) {	
		Queue<Integer> q = new LinkedList<>();
		q.add(idx);
		visited[idx] = true;
		while (!q.isEmpty()) {
			int index = q.poll();
			for(int j : connectionArr.get(index)) {
				if(!visited[j]) {
					q.add(j);
					visited[j] = true;
					count++;
				}
			}
		}
	}
}
