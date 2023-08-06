import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj11729 {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		
		int start = 1;
		int temp = 2;
		int end = 3;
		int count;
	        
    	count = (int) Math.pow(2, n) - 1;
    	bw.write(String.valueOf(count));
    	bw.newLine();
    	bw.flush();
    	hanoi(n, start, temp, end);
    	System.out.println(sb);
		bw.close();
	}
	
	public static void hanoi(int n, int start, int temp, int end) throws IOException {
		if(n == 1) {
//			n 시작 -> 목적
			move(n, start, end);	
			return;
		}else {
			hanoi(n-1, start, end, temp);
//			n번 원판 목적지로
			move(n, start, end);
			hanoi(n-1, temp, start, end);
		}		
	}
	
	public static void move(int n, int start, int end) throws IOException {
//		sb.setLength(0);
		sb.append(start).append(" ").append(end).append("\n");
	}

}
