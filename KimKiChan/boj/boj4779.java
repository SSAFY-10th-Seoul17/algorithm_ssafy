import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class boj4779 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static char c[];
    
    static void cantor(int a, int b) throws IOException {
		if(b < 3) {
			return;
		}
		
		for(int i = a + b/3; i < a + 2 * (b/3); i++) {
			c[i] = ' ';
		}
		
		cantor(a, b/3);
		
		cantor(a + 2*(b/3), b/3);
		
	}
	
	
	public static void main(String[] args) throws Exception {
        // 1. -가 3^n개 문자열
        // 2. 0 ~ 3^(n-1) - 1, 3^(n-1) ~ 2 * 3^(n-1) - 1, 2 * 3^(n-1) ~ 3^n -1
        // 3. 가운데 문자열을 공백으로 바꾼다
        // 4. 3^(n-1)이 1이 될 때까지
        
        String str = "";
        
        while((str = br.readLine()) != null) {
        	int n = Integer.parseInt(str);
        	int num = (int) Math.pow(3, n);
        	c = new char[num];
        	for(int i = 0; i < num; i++) {
        		c[i] = '-';
        	}
        	cantor(0, num);
        	for(int i = 0; i < num; i++) {
        		bw.write(c[i]);
        	}
        	bw.newLine();
        	bw.flush();
        }
      
	}
}
