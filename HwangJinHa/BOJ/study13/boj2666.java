import java.io.*;
import java.util.*;

public class boj2666 {
	static ArrayList<String> list = new ArrayList<>();
	static ArrayList<Integer> ans = new ArrayList<>();
	static int[] num = new int[21];
	
	static void make(int l, int r, int c, int limit, String sample, int way) {
		c++;
		if (num[c] < l) {
			sample += 'l';
			way += l - num[c];
			if (c == limit) {
				list.add(sample);
				ans.add(way);
				return;
			}
			make(num[c], r, c, limit, sample, way);
		}
		else if (num[c] > r) {
			sample += 'r';
			way += num[c] - r;
			if (c == limit) {
				list.add(sample);
				ans.add(way);
				return;
			}
			make(l, num[c], c, limit, sample, way);
		}
		else if (num[c] == l || num[c] == r) {
			sample += 's';
			if (c == limit) {
				list.add(sample);
				ans.add(way);
				return;
			}
			make(l, r, c, limit, sample, way);
		}
		else {
			if (c == limit) {
				list.add(sample + 'l');
				ans.add(way + num[c] - l);
				list.add(sample + 'r');
				ans.add(way + r - num[c]);
				return;
			}
			make(num[c], r, c, limit, sample + 'l', way + num[c] - l);
			make(l, num[c], c, limit, sample + 'r', way + r - num[c]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		int l = Integer.valueOf(st.nextToken());
		int r = Integer.valueOf(st.nextToken());

		int c = Integer.valueOf(br.readLine());

		if (r < l) {
			int temp = r;
			r = l;
			l = temp;
		}
		for (int i = 1; i <= c; i++) {
			num[i] = Integer.valueOf(br.readLine());
		}
		String sample = "";
		make(l, r, 0, c, sample, 0);
		int small = ans.get(0);
		for (int i = 1; i < list.size(); i++) {
			if (ans.get(i) < small) small = ans.get(i);
		}
		System.out.println(small);
	}
}
