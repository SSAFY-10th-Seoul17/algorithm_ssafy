import java.util.*;
import java.io.*;
// 03:02 - 03:56 : 54m

// 방법 1. BFS
// 1과 친구, 친구의 친구까지만 초대할 수 있기 때문에 bfs에서 depth가 2까지인 출발점을 제외한 정점의 개수를 세는 문제라고 판단하였습니다.
// 초대하면 해당 사람의 visited를 1씩 증가시켜주고, 2보다 클 때에는 친구의 친구이므로 더 이상 큐에 넣지 안도록 처리해주었습니다.
// 이때, break를 사용하여 다음에 큐에서 기다리고 있는 친구를 세지 못하게 해서 틀렸었는데, continue로 수정하여 큐에 남아있는 친구도 처리해줄 수 있도록 수정하였습니다.


// 방법2. Set 사용
// 상근이의 친구가 몇명인지 확인하고, 친구들의 친구만 확인해주면 bfs를 사용하지 않아도 괜찮을 것같다는 생각을 하게 되었습니다.
// 인접리스트는 방법1과 동일하게 만들어주고, 상근이의 친구들을 set에 저장합니다. 
// 이후 상근이의 친구들의 친구들도 set에 넣어줍니다.
// 그러면 set에는 상근이가 포함되어 있을 수도, 없을 수도 있기 때문에 상근이를 마지막에 넣어주고, 1을 빼서 친구들의 수만 출력해줍니다.

public class BOJ5567_결혼식 {
 public static void main(String[] args) throws Exception {
	 // 친구, 친구의 친구 초대 , 동기는 n명 , 학번은 1~n , 상근이는 1
	 // 결혼식에 초대할 사람 수 출력 -> 친구의 친구까지만 : 길이가 2까지 == depth가 2까지만
	 // 상근이의 친구 몇명인지 확인, 친구들의 친구의 수만 확인하기 -> bfs를 사용하지 않아도 괜찮지 않을까..?
	 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 int n = Integer.parseInt(br.readLine());
	 int m = Integer.parseInt(br.readLine());
	 List<ArrayList> friends = new ArrayList<>();
	 int result = 0;
	 
	 for (int i=0; i<n+1; i++) {
		 friends.add(new ArrayList<>());
	 }
	 
	 // 인접 리스트 생성
	 for (int i=0; i<m; i++) {
		 StringTokenizer st = new StringTokenizer(br.readLine()," ");
		 int from = Integer.parseInt(st.nextToken());
		 int to = Integer.parseInt(st.nextToken());
		 
		 friends.get(from).add(to);
		 friends.get(to).add(from);
	 }
	 
	 Set<Integer> set = new HashSet<Integer>();
	 
	 ArrayList f1 = friends.get(1); // 상근이의 친구
	 for (int i=0; i<f1.size(); i++) {
		 int f = (int)f1.get(i); // 상근이의 친구
		 set.add(f);
		 
		 ArrayList f2 = friends.get(f); // 친구의 친구
		 
		 for (int j=0; j<f2.size(); j++) {
			 int ff = (int)f2.get(j);
			 set.add(ff);
		 }
	 }
	 set.add(1);
	 System.out.println(set.size()-1); 
	 
///////////////////////////////////////////////////////////////////////////////////////
	 // dfs, visited == 2이면 끝
	 int[] visited = new int[n+1];
	 Queue<Integer> q = new ArrayDeque<>();
	 q.offer(1);
	 visited[1] = 1; // 상근이도 1을 더해주었으므로 visited == 2일 때가 아닌, visited > 2 일 때 친구의 친구를 넘어서는 것임
	 
	 while (!q.isEmpty()) {
		 int cur = q.poll();
		 ArrayList list = friends.get(cur);
		 
		 for (int i=0; i<list.size(); i++) {
			 int next = (int)list.get(i);
			 if (visited[next] == 0) { // 방문하지 않았을 때
				 visited[next] = visited[cur]+1;
				 result ++;
				 
				 if (visited[next] > 2) {
					 continue; // break해주면 안된다!! -> 다른 친구의 친구가 있을지 모르니까
				 }
				 
				 q.offer(next);
			 }
		 }
	 }
	 System.out.println(result);
 }
}
