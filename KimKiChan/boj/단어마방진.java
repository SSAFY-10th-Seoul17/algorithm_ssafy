import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//24894번
public class 단어마방진 {
	private static int l;
	private static String[] input;
	private static String[] magicSquare;
	private static boolean[] checked;
	private static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		l = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		input = new String[n];
		for(int i = 0; i < n; i++) {
			input[i] = br.readLine();
		}
		
		Arrays.sort(input);
		
		checked = new boolean[n];
		magicSquare = new String[n];
		dfs(0);
		
		System.out.println("NONE");
	}
	
	private static void dfs(int line) {
		if(line == l) {

			if(checkMagic(magicSquare)) {
				System.out.println(squareToString(magicSquare));
				System.exit(0);
			}
			return;
		}
		for(int i = 0; i < n; i++) {
			if(checked[i]) continue;
			checked[i] = true;
			magicSquare[line] = input[i];
			dfs(line+1);
			checked[i] = false;
		}
		
	}

	private static boolean checkMagic(String[] stringArr) {
		for(int i = 0; i < l; i++) {
			for(int j = 0; j < l; j++) {
				if(stringArr[i].charAt(j) != stringArr[j].charAt(i)) {//마방진 실패
					return false;
				}
			}
		}
		return true;
	}
		

	private static String squareToString(String[] stringArr) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < l; i++) {
			sb.append(stringArr[i]).append("\n");
		}
		return sb.toString();
	}
}
