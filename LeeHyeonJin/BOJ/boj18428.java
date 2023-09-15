import java.util.*;
import java.io.*;

/*
 * 아이디어 :
 * 1. map 위에 조합을 통해 O를 설치할 3가지 경우의 수 추출
 * 2. 경우의 수마다 -> map 위에 존재하는 모든 T를 시작점으로 DFS를 한 결과, S와 만나는 경우가 있는지 여부 체크
 * 	  - DFS 종료조건 : map 범위를 벗어나는 경우, 해당 위치의 값이 O인 경우
 */
public class boj18428 {
	static int N;
	static String[][] map;
	static List<int[]> empties = new ArrayList<>(); //조합의 대상이 될 빈 공간의 좌표 미리 저장
	static int[][] selets = new int[3][2]; //조합으로 임시 지정한 장애물을 세울 좌표
	static List<int[]> teachers = new ArrayList<>(); //DFS를 시작해야하는 좌표
	static boolean possible = false, done = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];

		for(int r=0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<N; c++) {
				map[r][c] = st.nextToken();
				if(map[r][c].equals("X")) empties.add(new int[] { r, c });
				if(map[r][c].equals("T")) teachers.add(new int[] { r, c });
			}
		}

		// solution
		combinations(0, 0);

		// output
		System.out.println(possible ? "YES" : "NO");
	}

	static void combinations(int cnt, int start) {
		if(done) return; //이제 더이상 탐색안해도 되는 경우
		if(cnt == 3) {
			boolean flag = true;
			for(int[] teacher : teachers) {
				if(!DFS(teacher[0], teacher[1])) flag = false;
			}
			//선생님들이 모두 탐색을 했는데도 flag가 true라면 == 가능한 경우 존재 => 백트래킹도 종료
			if(flag) possible = done = true;
			return;
		}
		for(int i=start; i<empties.size(); i++) {
			selets[cnt] = empties.get(i);
			combinations(cnt+1, i+1);
		}
	}

	static boolean DFS(int sr, int sc) {
		//경우의 수에 따라 장애물 설치
		setMap("O");

		//DFS
		int[][] directions = { {-1,0},{1,0},{0,-1},{0,1} };
		Stack<int[]> stack = new Stack<>();
		for(int d=0; d<4; d++) stack.push(new int[] { sr, sc, d }); //DFS 퍼져나갈때, 이전의 방향을 유지하면서 계속 갈 수 있음

		boolean flag = true;
		while(!stack.isEmpty()) {
			int[] now = stack.pop();
			int nr = now[0] + directions[now[2]][0];
			int nc = now[1] + directions[now[2]][1];
			if(!(nr>=0 && nr<N && nc>=0 && nc<N) || map[nr][nc].equals("O")) continue;
			//학생과 선생님이 만나면 => 안된다고 하고, 그만 탐색
			if(map[nr][nc].equals("S")) {
				flag = false;
				break;
			}
			stack.push(new int[] { nr, nc, now[2] });
		}

		//설치했던 장애물 원복
		setMap("X");
		return flag;
	}

	//map을 셋팅하는 메서드 : s가 O면 장애물 설치, X면 원복
	static void setMap(String s) {
		for(int[] select : selets) {
			map[select[0]][select[1]] = s;
		}
	}
}
