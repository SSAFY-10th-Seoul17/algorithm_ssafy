import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1436 {
	public static void main(String[] args) throws NumberFormatException, IOException {
        StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0; // cnt: 종말 수
		int title = 0; // title: 영화 제목에 들어간 수
		int temp;
		int n = Integer.parseInt(br.readLine());

		while (cnt!=n) {
			title++;
			temp = title;
			while (temp != 0) {
				if (temp % 1000 == 666) {
					cnt++;
					break;
				}
				else {
					temp = temp / 10;
				}
			}
		}
		sb.append(title);
		System.out.println(sb.toString());
	}
	
}
