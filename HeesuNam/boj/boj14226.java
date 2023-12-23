import java.io.*;
import java.util.*;


// screen+=clipboard*2
// screen-=1

public class Main {
	private static int S;
	private static boolean[][] typed;
	static class Emoji{
		int clipboard;
		int screen;
		int time;
		public Emoji(int clipboard, int screen, int time) {
			super();
			this.clipboard = clipboard;
			this.screen = screen;
			this.time = time;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = Integer.parseInt(br.readLine());
		typingEmoji();
	}
	private static void typingEmoji() {
		typed = new boolean[S+1][S+1]; // clipboard, screen
		Queue<Emoji> queue = new ArrayDeque<>();
		queue.offer(new Emoji(0,1,0));
		while(!queue.isEmpty()) {
			Emoji cur = queue.poll();
			if(cur.screen==S) {
				System.out.println(cur.time);
				break;
			}
			if(typed[cur.clipboard][cur.screen])continue;
			typed[cur.clipboard][cur.screen] = true;
			if(cur.screen>0) { 
				// 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
				if(!typed[cur.screen][cur.screen])
					queue.offer(new Emoji(cur.screen, cur.screen,cur.time+1));
				//화면에 있는 이모티콘 중 하나를 삭제
				if(!typed[cur.clipboard][cur.screen-1])
					queue.offer(new Emoji(cur.clipboard, cur.screen-1,cur.time+1));
			}
			if(cur.clipboard>0 && cur.screen+cur.clipboard <= S&& !typed[cur.clipboard][cur.screen+cur.clipboard]) { 
				// 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
				queue.offer(new Emoji(cur.clipboard, cur.screen+cur.clipboard, cur.time+1));
			}
		}
		
	} // end of main
} // end of class
