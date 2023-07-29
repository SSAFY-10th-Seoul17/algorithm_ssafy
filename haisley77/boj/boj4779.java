import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj4779 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) {
		try {
			while (true) {
				int N = Integer.parseInt(br.readLine());
				String res = cantorianSet(0,(int) Math.pow(3, N));
				System.out.println(res);
			}
		} catch (Exception e){};
	}
	
	private static String cantorianSet(int start, int end) {
		if (end-start == 1) return "-";

		return cantorianSet(0,(end-start)/3) + " ".repeat((end-start)/3) + cantorianSet(end-(end-start)/3,end);
	}
}
