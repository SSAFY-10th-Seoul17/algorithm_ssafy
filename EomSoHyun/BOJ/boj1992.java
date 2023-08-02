import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 

public class Main  {
	static int n;
	static String[][] map;
	static String result = "";
	
	public static void calc(int i, int j, int size) {
		if (isSame(i, j, size)) {
			result = result + map[i][j];
			return;
		} else {
			size /= 2;
			result = result + '(';
			calc(i, j, size);  // 제1사분면
			calc(i, j + size, size);  // 제2사분면
			calc(i + size, j, size);  // 제3사분면
			calc(i + size, j + size, size);  // 제4사분면
			result = result + ')';
			
		}
	}
	
	public static boolean isSame(int i, int j, int size) {
		String ch = map[i][j];
		int dx, dy;
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				dx = i + x;
				dy = j + y;
				if (!ch.equals(map[dx][dy])) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		map = new String[n][n];
		for (int i = 0; i < map.length; i++) {
			String[] line = br.readLine().split("");
			for (int j = 0; j < map.length; j++) {
				map[i][j] = line[j];
			}
		}
		
		
		calc(0, 0, n);
		System.out.println(result);
		
		
	}  // end of main
}  // end of class
