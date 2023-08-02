import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2615 {
	static boolean win = false;
	static int[][] baduk = new int[19][19];
	public static void main(String[] args) throws Exception {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		for(int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 19; j++) {
				baduk[i][j] = Integer.parseInt(st.nextToken());
			}
		}
					
		for(int i = 0; i < baduk.length; i++) {
			for(int j = 0; j < baduk[i].length; j++) {
				if(baduk[i][j] != 0) { // 바둑알이 있을 경우
					if(!win) col(i, j);
					if(!win) row(i, j);
					if(!win) diagRUp(i, j);
					if(!win) diagRDown(i, j);
				} // if문
			}//j문 끝
		}//i문 끝
		
		if(win == false) {
			System.out.println("0");
		}
		
	}// end of main
    
    static public boolean check(int x, int y, int dx, int dy){ 
        if(dx < 19 && dx >= 0 && dy < 19 && dy >= 0){ // 해당 원소가 바둑판 안에 있는지 확인
            if(baduk[x][y] == baduk[dx][dy]){// 반대 방향의 원소가 같은 색인지 확인
                return true;
            }
        }
        return false;
    }
	
	static public void col(int x, int y) { // 세로
		if(check(x, y, x-1, y)){
            return;
		}
		for(int i = 1; i < 5; i++) {
			int dx = x + i;
			int dy = y;
            if(dx < 19 && dx >= 0 && dy < 19 && dy >= 0) { // 바둑판을 벗어나지 않는다
				if(!(baduk[x][y] == baduk[dx][dy])) {
					return;
				}	
			}else {
				return;
			}
		}
		if(x+5 < 19) {
			if(baduk[x][y] == baduk[x+5][y]) { // 여섯번째 바둑알은 일치하면 안된다
				return;
			}else {
//				System.out.println("문제7");
				win = true;
				System.out.println(baduk[x][y]);
				System.out.println((x+1) + " " + (y+1));
				return;
			}	
		}else {
//			System.out.println("문제8");
			win = true;
			System.out.println(baduk[x][y]);
			System.out.println((x+1) + " " + (y+1));
			return;
		}	
	}
	static public void row(int x, int y) {
		if(check(x, y, x, y-1)){
            return;
        }
		for(int i = 1; i < 5; i++) {
			int dx = x;
			int dy = y + i;
			if(dx < 19 && dx >= 0 && dy < 19 && dy >= 0) { // 바둑판을 벗어나지 않는다
				if(!(baduk[x][y] == baduk[dx][dy])) {
					return;
				}
			}else {
				return;
			}
				
		}
		if(y+5 < 19) {
			if(baduk[x][y] == baduk[x][y+5]) { // 여섯번째 바둑알은 일치하면 안된다
				return;
			}else {
//				System.out.println("문제5");
				win = true;
				System.out.println(baduk[x][y]);
				System.out.println((x+1) + " " + (y+1));
				return;
			}	
		}else {
//			System.out.println("문제6");
			win = true;
			System.out.println(baduk[x][y]);
			System.out.println((x+1) + " " + (y+1));
			return;
		}	
	}
	static public void diagRUp(int x, int y) {
		if(check(x, y, x+1, y-1)){
            return;
        }
		for(int i = 1; i < 5; i++) {
			int dx = x - i;
			int dy = y + i;
			if(dx < 19 && dx >= 0 && dy < 19 && dy >= 0) { // 바둑판을 벗어나지 않는다
				if(!(baduk[x][y] == baduk[dx][dy])) {
					return;
				}
			}else {
				return;
			}
		}
		if(x-5 >= 0 && y+5 < 19) {
			if(baduk[x][y] == baduk[x-5][y+5]) { // 여섯번째 바둑알은 일치하면 안된다
				return;
			}else {
//				System.out.println("문제1");
				win = true;
				System.out.println(baduk[x][y]);
				System.out.println((x+1) + " " + (y+1));
				return;
			}	
		}else {
//			System.out.println("문제2");
			win = true;
			System.out.println(baduk[x][y]);
			System.out.println((x+1) + " " + (y+1));
			return;
		}
	}
	
	static public void diagRDown(int x, int y) {
		if(check(x, y, x-1, y-1)){
            return;
        }
		for(int i = 1; i < 5; i++) {
			int dx = x + i;
			int dy = y + i;
			if(dx < 19 && dx >= 0 && dy < 19 && dy >= 0) { // 바둑판을 벗어나지 않는다
				if(!(baduk[x][y] == baduk[dx][dy])) {
					return;
				}
			}else {
				return;
			}
		}
		if(x+5 < 19 && y+5 < 19) {
			if(baduk[x][y] == baduk[x+5][y+5]) { // 여섯번째 바둑알은 일치하면 안된다
				return;
			}else {
//				System.out.println("문제3");
				win = true;
				System.out.println(baduk[x][y]);
				System.out.println((x+1) + " " + (y+1));
				return;
			}	
		}else {
//			System.out.println("문제4");
			win = true;
			System.out.println(baduk[x][y]);
			System.out.println((x+1) + " " + (y+1));
			return;
		}
	}	
	
	
}// end of class

