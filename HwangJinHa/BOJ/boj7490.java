import java.util.Scanner;

public class boj7490 {
	static int n;
	static char[] crr;
	static char[] letters = {' ', '+', '-'};
	
	static boolean checkIfWork() {
		int[] nums = new int[n];
		int idx = 0;
		int tmp = 1;
		for(int i = 0; i < n; i++) {
			if (i == n - 1) {
				nums[idx++] = tmp;
				break;
			}
				
			if (crr[i] == ' ') {
				tmp = tmp * 10 + i + 2;
			}
			else {
				nums[idx++] = tmp;
				tmp = i + 2;
			}
		}
		
		idx = 0;
		int result = nums[idx++];
		for(int i = 1; i < n; i++) {
			if (crr[i - 1] == ' ') {
				continue;
			}
			else if (crr[i - 1] == '+') {
				result += nums[idx++];
			}
			else if (crr[i - 1] == '-') {
				result -= nums[idx++];
			}
		}
		
		return (result == 0)? true : false;
	}

	static void crrToString() {
		for(int i = 0; i < n - 1; i++) {
			System.out.print("" + (i+1) + crr[i]);
		}
		System.out.println(n);
	}

	static void dfs(int depth) {
		if (depth == n - 1) {
			if (checkIfWork()) {
				crrToString();
			}
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			crr[depth] = letters[i];
			dfs(depth + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int t = sc.nextInt();
		for (int test_case = 0; test_case < t; test_case++) {
			n = sc.nextInt();
			crr = new char[n - 1];
			dfs(0);
			
			System.out.println();
		}
	}
}
