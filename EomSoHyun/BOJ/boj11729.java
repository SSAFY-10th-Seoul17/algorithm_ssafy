import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.IOException; 

public class Main  {
	static int cnt = 0;
	static ArrayList startRoute = new ArrayList();
	static ArrayList EndRoute = new ArrayList();
	
	public static void hanoi(int n, int start, int temp, int dest) {
		if (n == 0) return;
		
		hanoi(n-1, start, dest, temp);
		startRoute.add(String.valueOf(start));
		EndRoute.add(String.valueOf(dest));
		cnt++;
		hanoi(n-1, temp, start, dest);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		hanoi(n, 1, 2, 3);

		sb.append(cnt).append('\n');
		for (int i = 0; i < startRoute.size(); i++) {
			sb.append(startRoute.get(i)).append(" ").append(EndRoute.get(i)).append('\n');
		}
		
		System.out.println(sb);
	}  // end of main
}  // end of class
