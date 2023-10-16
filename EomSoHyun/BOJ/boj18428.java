import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int N;
	static char[][] hall;
	static List<int[]> tLoc;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		hall = new char[N][N];
		tLoc = new ArrayList<int[]>();
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0, idx = 0; j < N; j++, idx+=2) {
				hall[i][j] = line.charAt(idx);
				if (hall[i][j] == 'T') {
					tLoc.add(new int[] {i, j});
				}
			}
		}
		
		if (simulation(0)) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
		
		
		
	}
	
	public static boolean simulation(int cnt) {
		if (cnt == 3) {
			if (check()) return true;
			return false;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (hall[i][j] == 'X') {
					hall[i][j] = 'O';
					if (simulation(cnt+1)) {
						return true;
					}
					hall[i][j] = 'X';
				}
			}
		}
		return false;
		
	}
	
	public static boolean check() {
		for (int i = 0; i < tLoc.size(); i++) {
			int x = tLoc.get(i)[0];
			int y = tLoc.get(i)[1];
			
			// 상 
			for (int j = x-1; j >= 0; j--) {
				if (hall[j][y] == 'O') break;  // 해당 라인에 장애물 설치됨 
				else if(hall[j][y] == 'S') return false;  // 감시 피하기 실패
			}
			// 하
			for (int j = x+1; j < N; j++) {
				if (hall[j][y] == 'O') break;  // 해당 라인에 장애물 설치됨 
				else if(hall[j][y] == 'S') return false;  // 감시 피하기 실패
			}
			// 좌
			for (int j = y-1; j >= 0; j--) {
				if (hall[x][j] == 'O') break;  // 해당 라인에 장애물 설치됨 
				else if(hall[x][j] == 'S') return false;  // 감시 피하기 실패
			}
			// 우 
			for (int j = y+1; j < N; j++) {
				if (hall[x][j] == 'O') break;  // 해당 라인에 장애물 설치됨 
				else if(hall[x][j] == 'S') return false;  // 감시 피하기 실패
			}
		}
		
		return true;
	}
}
