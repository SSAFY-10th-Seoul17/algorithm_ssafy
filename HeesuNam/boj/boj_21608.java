import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static HashSet<Integer> students = new HashSet<>();
	private static ArrayList<HashSet<Integer>> friends = new ArrayList<>(); // 해당 학생이 좋아하는 학생번호
	private static int[][] map;
	private static Plot[] location;
	private static int[] dr = { -1, 0, 1, 0 };
	private static int[] dc = { 0, 1, 0, -1 };
	private static int[] order;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N + 2][N + 2]; // 맵에 학생번호저장
		location = new Plot[N * N + 1]; // 학생들 r,c저장
		order = new int[N * N]; // 들어온 학생의 번호
		for (int i = 0; i <= N * N; i++) {
			friends.add(new HashSet<Integer>());
		}

		for (int i = 0; i < N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int student = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 4; j++) {
				int favFriend = Integer.parseInt(st.nextToken());
				friends.get(student).add(favFriend);
			}
			order[i] = student;
		}
		setLocation(order[0], 2, 2);

		for (int i = 1; i < N * N; i++) {
			findBlock(order[i]);
		}

		System.out.println(getScore());
	} // end of main

	private static int getScore() {
		int ans = 0;
		for (int i = 1; i <= N * N; i++) {
			Plot cur = location[i];
			int cnt = 0;
			for (int j = 0; j < 4; j++) {
				int nr = cur.r + dr[j];
				int nc = cur.c + dc[j];
				if (1 <= nr && nr <= N && 1 <= nc && nc <= N && friends.get(i).contains(map[nr][nc]))
					cnt++;
			}
			switch (cnt) {
			case 1:
				ans += 1;
				break;
			case 2:
				ans += 10;
				break;
			case 3:
				ans += 100;
				break;
			case 4:
				ans += 1000;
				break;
			}
		}
		return ans;
	}

	private static void findBlock(int studentId) {
		HashMap<Plot, Integer> counter = new HashMap<>();
		for (int fav : friends.get(studentId)) {
			if (students.contains(fav)) {
				Plot cur = location[fav];
				for (int i = 0; i < 4; i++) {
					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];
					if (1 <= nr && nr <= N && 1 <= nc && nc <= N && map[nr][nc] == 0) {
						Plot nxt = new Plot(nr, nc);
						counter.put(nxt, counter.getOrDefault(nxt, 0) + 1);
					}
				}
			}
		}
		PriorityQueue<Block> pq = new PriorityQueue<>();
		if (counter.isEmpty()) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (map[i][j] == 0) {
						Plot cur = new Plot(i, j);
						pq.offer(new Block(cur, 0, cntEmpty(cur)));
					}
				}
			}
		} else {
			for (Entry<Plot, Integer> e : counter.entrySet()) {
				pq.offer(new Block(e.getKey(), e.getValue(), cntEmpty(e.getKey())));
			}
		}
		Block select = pq.poll();
		setLocation(studentId, select.p.r, select.p.c);
	}

	private static int cntEmpty(Plot p) {
		int cnt = 0;
		for (int i = 0; i < 4; i++) {
			int nr = p.r + dr[i];
			int nc = p.c + dc[i];
			if (1 <= nr && nr <= N && 1 <= nc && nc <= N && map[nr][nc] == 0)
				cnt++;
		}
		return cnt;
	}

	private static void setLocation(int studentId, int r, int c) {
		map[r][c] = studentId; // 학생 맵에 배치
		location[studentId] = new Plot(r, c); // 학생 위치 저장
		students.add(studentId); // 현재 배치된 학생에 추가

	}

	static class Plot {
		int r;
		int c;

		public Plot(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof Plot)) {
				return false;
			}
			Plot casted = (Plot) obj;
			return casted.r == r && casted.c == c;
		}

		@Override
		public int hashCode() {
			return Objects.hash(r, c);
		}

	}

	static class Block implements Comparable<Block> {
		Plot p;
		int likeCnt;
		int emptyCnt;

		public Block(Plot p, int likeCnt, int emptyCnt) {
			this.p = p;
			this.likeCnt = likeCnt;
			this.emptyCnt = emptyCnt;
		}

		@Override
		public int compareTo(Block o2) {
			if (likeCnt == o2.likeCnt) {
				if (emptyCnt == o2.emptyCnt) {
					if (p.r == o2.p.r) {
						return p.c - o2.p.c;
					}
					return p.r - o2.p.r;
				}
				return o2.emptyCnt - emptyCnt;
			}
			return o2.likeCnt - likeCnt;
		}

	}
} // end of class
