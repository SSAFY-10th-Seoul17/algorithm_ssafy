// 영화 감독 숌
// 📝 

// 1. 규칙을 찾으려고 했지만 찾지 못했다.
// 2. 666부터 1씩 올라가면서 하나씩 다 찾아준다.
import java.io.*;
import java.util.*;
public class BOJ1436 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int num = 666;

		
		while(n > 0) {
			if (Integer.toString(num).contains("666")) {
				n--;
			}
			
			num++;
		}
		
		System.out.println(num-1);
		
	}
}
