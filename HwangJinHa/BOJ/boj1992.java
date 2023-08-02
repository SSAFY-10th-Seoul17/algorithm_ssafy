import java.io.*;
import java.util.*;

// 쿼드트리
public class boj1992 {
	static String[] map;
	static String result;
	
	static boolean isSame(int l, int u, int r, int d) {
		char ref = map[u].charAt(l);
		for(int y = u; y <= d; y++) {
			for(int x = l; x <= r; x++) {
				if(ref != map[y].charAt(x)) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void make(int l, int u, int r, int d) {
		if (l == r || isSame(l, u, r, d)) {
			result = result + String.valueOf(map[u].charAt(l));
			return;
		}
		int vm = (u + d) / 2;
		int hm = (l + r) / 2;
		
		result = result + "(";
		make(l, u, hm, vm);
		make(hm + 1, u, r, vm);
		make(l, vm + 1, hm, d);
		make(hm + 1, vm + 1, r, d);
		result = result + ")";
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		result = "";
		
		int n = Integer.valueOf(br.readLine());
		map = new String[n];
		for (int i = 0; i < n; i++) {
			map[i] = br.readLine().trim();
		}
		
		make(0, 0, n-1, n-1);
		
		System.out.println(result);
		
		
	}
}
