import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static List<Integer> list;
	static boolean[] used;
	static int[] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 중복 없는 수 생성
		list = new ArrayList<>();
		used = new boolean[10];
		for (int i = 1; i < 10; i++) {
			nums = new int[i];
			makeNum(i, 0);
		}
		
		String line;
		while ((line = br.readLine()) != null) {
			int num = Integer.parseInt(line);
			
			int s = 0;
			int e = list.size()-1;
			int res = 0;
			while (s < e) {
				int m = (s + e) / 2;
				int mNum = list.get(m);
				if (num < mNum) {
					e = m;
					res = mNum;
				}
				else {
					s = m+1;
				}
				
			}
			sb.append(res).append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void makeNum(int len, int idx) {
		if (idx == len) {
			int num = 0;
			for (int i = 0; i < len; i++) {
				num += nums[len-i-1] * (Math.pow(10, i));
			}
			list.add(num);
			return;
		}
		for (int i = 1; i < 10; i++) {
			if (used[i]) continue;
			nums[idx] = i;
			used[i] = true;
			makeNum(len, idx+1);
			used[i] = false;
		}
	}
}
