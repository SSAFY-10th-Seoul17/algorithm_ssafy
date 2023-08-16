import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2210 {

    private static int[][] arr;
	private static ArrayList<String> list;
	public static void main(String[] args) throws IOException {
        //숫자를 문자열처리 해야함
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        arr = new int[5][5]; //5*5 숫자판
        
        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }//입력받기
        
        list = new ArrayList<String>();
        numbers = new int[6];
        for (int i = 0; i < 5; i++) { 
        	for (int j = 0; j < 5; j++) {
        		dfs(0, i, j);
			}
        }
        
        System.out.println(list.size());
    }
    static int[] numbers;
    static public void dfs(int cnt, int x, int y) {
    	//네 방향으로 여섯번 이동하면 종료
        if(cnt == 6) {
//        	System.out.println(Arrays.toString(numbers));
        	String str = "";
        	for (int i = 0; i < numbers.length; i++) {
				str += numbers[i];
			}
        	if(!list.contains(str)) {
        		list.add(str);
        	}
        	
        	return;
        }
        //범위 체크
        if(x < 0 || x >= 5 || y < 0 || y >= 5) {
        	return;
        }
        numbers[cnt] = arr[x][y];
        dfs(cnt+1, x-1, y);//상
        dfs(cnt+1, x+1, y);//하
        dfs(cnt+1, x, y-1);//좌
        dfs(cnt+1, x, y+1);//우
        
    }

}
