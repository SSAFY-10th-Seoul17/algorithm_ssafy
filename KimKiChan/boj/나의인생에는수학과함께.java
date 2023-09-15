import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//17265번
public class 나의인생에는수학과함께 {
	// 오른쪽, 아래, 각각 n번 이동 가능
	private static int n;
	private static char[][] map;
	private static int maxCalorie = Integer.MIN_VALUE;
	private static int minCalorie = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < n; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
		
		calculate(0, 0, 0, ' ');
		
		System.out.println(maxCalorie + " " + minCalorie);
		
	}// end of main

	private static void calculate(int x, int y, int result, char op) {
		int tempResult = result;
		char tempOp = ' ';

		if(map[x][y] == '+' || map[x][y] == '-' || map[x][y] == '*') { // 연산자 저장
			tempOp = map[x][y];
		}else {
			switch (op) { // 연산 결과 저장
			case '+':
				tempResult += (map[x][y] - '0');
				break;
			case '-':
				tempResult -= (map[x][y] - '0');
				break;
			case '*':
				tempResult *= (map[x][y] - '0');
				break;

			default: // 첫 위치
				tempResult = (map[x][y] - '0');
				break;
			}
			
			if(x == n-1 && y == n-1) { // 맵 끝
				maxCalorie = maxCalorie < tempResult ? tempResult : maxCalorie;
				minCalorie = minCalorie > tempResult ? tempResult : minCalorie;
				return;
			}
		}
		
		// 맵 안 이동
		if(x < n-1)	calculate(x+1, y, tempResult, tempOp);
		if(y < n-1) calculate(x, y+1, tempResult, tempOp);

	}

}// end of class
