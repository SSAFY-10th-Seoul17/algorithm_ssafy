import java.io.*;

public class boj4779 {
	static String[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = br.readLine()) != null) {
			int n = Integer.parseInt(line);
			int arr_size = (int)Math.pow(3, n);
			arr = new String[arr_size];
			for(int i = 0; i < arr_size; i++) {
				arr[i] = "-";
			}
			recursion(0, arr_size);
			for(String i : arr) {
				sb.append(i);
			};
			System.out.println(sb.toString());
			sb.setLength(0);
		}
	
	}
	public static void recursion(int start, int size) {
		
		if(size == 1) return;
		
		for(int i = start + size / 3; i < start + size / 3 * 2; i++) {
			arr[i] = " ";
		}
		
		recursion(start, size / 3);
		recursion(start + size / 3 * 2, size/3);
	}
}