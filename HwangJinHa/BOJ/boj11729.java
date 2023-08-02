import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj11729 {
	static BufferedWriter bw;
	
	static void hanoi2(int a, int b, int c, int size) throws IOException { // a 에서 c로 이동하고 b를 이용할 수 있다.
		if (size == 0) {
			return;
		}
		hanoi2(a, c, b, size - 1);
		bw.write(a + " " + c + '\n');
		hanoi2(b, a, c, size - 1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.valueOf(br.readLine());
		
		BigInteger hanoiTime = new BigInteger("0");
		for (int i = 1; i <= n; i++) {
			hanoiTime = hanoiTime.multiply(new BigInteger("2")).add(BigInteger.ONE);
		}
		
		bw.write(hanoiTime + "\n");
		if (n <= 20)
			hanoi2(1, 2, 3, n);
		bw.flush();
	}
}
