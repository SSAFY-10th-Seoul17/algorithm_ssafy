import java.io.*;
import java.util.*;

// 모임 역순으로 감염확인
// 최종감염자들을 일단 최초감염자라 가정
// 최종비감염자가 있는경우 그 모임에 참여자들은 최초감염자가 될 수 없음 -> 비감염으로 수정 
// 최초감염자가 확정
// 모임확인 ->최조감염자나 감염된사람과 같은 모임일경우 감염
// 처음 인풋과 다르면 no

public class Main {

	private static int N, M;
	private static int[] finalInfected, expected;
	private static int[][] meetings;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		finalInfected = new int[N + 1];
		meetings = new int[M][];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int people = Integer.parseInt(st.nextToken());
			meetings[i] = new int[people];
			for (int j = 0; j < people; j++) {
				int p = Integer.parseInt(st.nextToken());
				meetings[i][j] = p;
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			if ("1".equals(st.nextToken()))
				finalInfected[i] = 1;
		}
		expected = new int[N + 1];
		System.arraycopy(finalInfected, 0, expected, 0, N + 1);
		
		findInitial();
		chkInfected();
	} // end of main



	private static void chkInfected() {
		int[] infected = new int[N+1];
		System.arraycopy(expected, 0, infected, 0, N + 1);
		for (int i = 0; i < M; i++) {
			for(int chkPeople:meetings[i]) {
				if(infected[chkPeople]==1) {
					for(int people:meetings[i]) {
						infected[people]=1;
					}
					break;
				}
			}
		}
		isEqual(infected,finalInfected);
	}



	private static void isEqual(int[] infected, int[] finalInfected) {
		StringBuilder sb = new StringBuilder();
		sb.append("YES\n");
		for (int i = 1; i <= N; i++) {
			sb.append(expected[i]).append(" ");
			if(infected[i]!=finalInfected[i]) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println(sb.toString());
	}



	private static void findInitial() {
		for (int i = M-1; i >=0; i--) {
			for (int chkPeople:meetings[i]) {
				if(expected[chkPeople]==0) {
					for (int people:meetings[i]) {
						expected[people]=0;
					}
					break;
				}
			}
		}
	}
} // end of class
