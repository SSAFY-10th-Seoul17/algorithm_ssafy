import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/** 백준 5567번 */
public class 결혼식 {
	private static List<Integer>[] friendList;
	private static boolean[] isInvited;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 상근이의 동기의 수 n
		int n = Integer.parseInt(br.readLine());
		// 리스트의 길이 m
		int m = Integer.parseInt(br.readLine());
		// 친구 리스트 friendList
		friendList = new ArrayList[n+1];
		for(int i = 1; i < friendList.length; i++) {
			friendList[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) { // 친구 관계 a <-> b
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friendList[a].add(b);
			friendList[b].add(a);
		}
		
		// 결혼식에 친구를 부른다 -> 친구의 친구까지 부른다. 친구의 친구의 친구는 안 부른다
		// 초대 여부
		isInvited = new boolean[n+1];
		isInvited[1] = true;
		invite(1, 0);
		
		// 초대된 동기의 수
		int count = 0;
		for(int i = 2; i < isInvited.length; i++) { // 자신 제외
			if(isInvited[i] == true) count++;
		}
		
		System.out.println(count);
		
	}// end of main

	private static void invite(int node, int count) { // 자신부터 시작
		
		if(count == 2) {// 친구의 친구까지
			return;
		}
		
		for(int friend : friendList[node]) {
			isInvited[friend] = true;
			invite(friend, count+1);
		}
		
	}
	
}// end of class
