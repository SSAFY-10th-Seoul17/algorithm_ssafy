import java.io.*;
import java.util.Arrays;

// 칸토어 집합
public class boj4779 {
	static String[] arr;
	
	static void makeArr(int l, int r, int size) {
		if (size == 1) {
			return;
		}
		size /= 3;
		makeArr(l, l + size - 1, size);
		makeSpace(l + size, l + 2 * size - 1);
		makeArr(l + 2 * size, r, size);
	}
	static void makeSpace(int l, int r) {
		for (int i = l; i <= r; i++)
			arr[i] = " ";
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = "";

		while((input = br.readLine()) != null) {
			int n = Integer.valueOf(input);
			int size = (int) Math.pow(3, n);

			arr = new String[size];
			Arrays.fill(arr, "-");
			
			makeArr(0, size - 1, size);
			
			System.out.println(String.join("", arr));
		}
	}

}
