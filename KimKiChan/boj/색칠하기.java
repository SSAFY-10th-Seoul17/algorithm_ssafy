package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//13265번
public class 색칠하기 {
	
	private static boolean isPossible;
	private static int[] color;
	private static ArrayList<Integer>[] connect;

	//연결된 두 동그라미는 서로 색이 다르다
	//2가지 색상으로 색칠이 가능한지의 여부
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		for(int t = 0; t < test; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); 
			//동그라미 n, 직선 m
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			
			connect = new ArrayList[n+1];
			for(int i = 0; i <= n; i++) {
				connect[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				connect[x].add(y);
				connect[y].add(x);
				
			}
			
			isPossible = true;
			color = new int[n+1]; // 0: 색칠 안 됨, 1: 색칠 1, 2: 색칠 2
			
			for(int i = 1; i <= n; i++) {
				if(!isPossible) break;
				if(color[i] == 0) {
					color[i] = 1;
					setColor(i);
				}
			}
			
			sb.append(isPossible ? "possible" : "impossible").append("\n");
			
		}// end of test case
	
		System.out.println(sb);
	
	}// end of main

	private static void setColor(int current) {
		if(!isPossible) return;
		
		for(int i = 0; i < connect[current].size(); i++) {
			int next = connect[current].get(i);
			
			if(color[next] == 0) { // 색칠 안 됨
				color[next] = 3 - color[current]; // 1이면 2, 2이면 1
				setColor(next);
			}else if(color[next] == color[current]) { // 색 일치
				isPossible = false;
				return;
			}
			// 색이 이미 칠해져 있거나 다를 경우 아무것도 할 필요가 없다
			
		}

	}
}// end of class

