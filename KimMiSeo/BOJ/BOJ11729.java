import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 하노이 탑 이동순서
public class BOJ11729 {
	public static int move;
	public static int n;
	public static List<int[]> li = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		// 원판의 개수 n 
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		move = 0;
		
		hanoi(n,1,2,3);
		
		System.out.println(move);
		System.out.println(sb.toString());
	}
	
	private static void hanoi (int nn, int start, int temp, int end) {
		if (nn == 1) {
			String res = start+" "+end+"\n";
			sb.append(res);
			move++;
			return;
		}
		
		hanoi(nn-1, start, end, temp);
		
		String res = start+" "+end+"\n";
		sb.append(res);
		move++;
		
		hanoi(nn-1, temp, start, end);
	}

}
