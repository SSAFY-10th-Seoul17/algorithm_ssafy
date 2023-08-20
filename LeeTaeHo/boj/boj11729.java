import java.io.*;

public class boj11729 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		bw.write((int) (Math.pow(2, n) -1) + "\n");
		
		jae(n, 1, 2, 3);
		
		bw.flush();
		bw.close();
	}
	
	public static void jae(int a, int st, int mid, int end) throws IOException {
		if(a == 1) {
			bw.write(st + " " + end + "\n");
			return;
		}
		else{
			jae(a-1, st, end, mid);
			bw.write(st + " " + end + "\n");
			jae(a-1, mid, st, end);
		
		}
	}
}
