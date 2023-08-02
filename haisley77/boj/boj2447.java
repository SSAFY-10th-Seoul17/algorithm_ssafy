import java.io.*;

public class boj2447 {
	static String[][] star;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		star = new String[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				star[i][j] = " ";
			}
		}
		makePrint(0,0,N);
		for (String[] strs : star) {
			for (String str : strs) {
				bw.write(str+"");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

	public static void makePrint(int y, int x, int n) {
		if (n == 1) {
			star[y][x] = "*";
			return;
		}
		
		int size = n/3;
		
		for (int i = x; i < x + size*2 + 1; i += size) {
			makePrint(y,i,size);
		}
		makePrint(y+size,x,size);
		makePrint(y+size,x+size*2,size);
		for (int i = x; i < x + size*2 + 1; i += size) {
			makePrint(y+size*2,i,size);
		}
		
		
	}

}
