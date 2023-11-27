import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int l, n;
	static String[] str;
	static int[] res;
	static boolean[] flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		l = Integer.parseInt(st.nextToken());  // 단어 길이
		n = Integer.parseInt(st.nextToken());  // 단어 개수
		str = new String[n];
		res = new int[n];
		flag = new boolean[n];
		for (int i = 0; i < n; i++) {
			str[i] = br.readLine();
		}
		
		Arrays.sort(str);
		if (mabang(0)) {
			for (int i = 0; i < l; i++) {
				sb.append(str[res[i]]).append('\n');
			}
		}
		else {
			sb.append("NONE");
		}
		
		System.out.println(sb);
		
	}
	
	public static boolean mabang(int idx) {
		if (idx == l) {
			return true;
		}
		
		for (int i = 0; i < n; i++) {
			if (flag[i]) continue;
			res[idx] = i;
			if (check(idx)) {
				flag[i] = true;
				if (mabang(idx+1)) {
					return true;
				}
				flag[i] = false;
			}
		}
		return false;
	}
	
	public static boolean check(int idx) {
		for (int j = 0; j < idx; j++) {
			if (str[res[idx]].charAt(j) != str[res[j]].charAt(idx)) {
				return false;
			}
		}
		return true;
	}
	

}
