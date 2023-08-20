import java.io.*;

public class boj1543 {
	static String n;
	static String m;
	static int cnt;
	static int idx = 0;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = br.readLine();
		m = br.readLine();
		cnt = 0;
		
		count();
		System.out.println(cnt);
	}
	public static void count() {
		boolean flag = false;
		for(int i = 0; i < n.length()-m.length()+1; i++) {
			for(int j = 0; j < m.length(); j++) {
				if(n.charAt(j + i) != m.charAt(j)) {
					flag = false;
					break;
				}
				flag = true;
			}
			if(flag) {
				i += m.length()-1;
				cnt++;
			}
		}	
	}
}
