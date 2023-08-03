import java.io.*;

public class boj17103 {
	static boolean[] isT = new boolean[1000001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t_case = 0; t_case < T; t_case++) {
			int n = Integer.parseInt(br.readLine());
			isT[0] = isT[1] = true;
			
			for(int i = 2; i*i<isT.length; i++) {
				if(!isT[i]) {
					for(int j = i*i; j < isT.length; j+=i) {
						isT[j] = true;
					}
				}
			}
			int cnt = 0;
			int right = n-2;
			for(int i = 2; i <= n/2; i++) {
				if(!isT[i] && !isT[right]) {
					if(i + right == n) {
						cnt++;
					}
				}
				right--;
			}
			System.out.println(cnt);
		}	
	}
}
