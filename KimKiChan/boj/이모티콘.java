import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

//14226번
public class 이모티콘 {
	private static int s;
	private static boolean[][] emoticon;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = Integer.parseInt(br.readLine());
		emoticon = new boolean[2001][2001];
		emoticon[1][0] = true;
		
		sol();
		
	}

	private static void sol() {
		Deque<int[]> dq = new ArrayDeque<>();
		dq.offer(new int[] {1, 0, 0}); // 위치, 복사, 시간
		
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int index = cur[0];
			int copy = cur[1];
			int time = cur[2];
			
			if(index == s) {
				System.out.println(time);
				return;
			}
			
			//복사
			if(copy != index) {
				int[] copied = {index, index, time+1};
				dq.offer(copied);
			}
			//붙여넣기
			if(index + copy <= 2000 && !emoticon[index+copy][copy]) {
				emoticon[index+copy][copy] = true;
				int[] paste = {index+copy, copy, time+1};
				dq.offer(paste);
			}
			//한칸 앞
			if(index-1 >= 1 && !emoticon[index-1][copy]) {
				emoticon[index-1][copy] = true;
				int[] back = {index-1, copy, time+1};
				dq.offer(back);
			}
		}
		
	}
}
