import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj5904 {
    private static char res;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N  = Integer.parseInt(br.readLine());
        int prevL = 0, L = 3, i = 0;
        while (L < N) {
            prevL = L;
            L = L * 2 + (++i + 3);
        }
        go(prevL, L, i, N);
        System.out.println(res);
    }
    private static void go(int prevL, int L, int i, int offset){
        if (L == 3){
            if (offset == 1) res = 'm';
            else res = 'o';
            return;
        }
        if (offset <= prevL){
            go((prevL-(i+2))/2, prevL, i-1, offset);
        } else if (offset <= L - prevL){
            if (offset-prevL == 1) res = 'm';
            else res = 'o';
        } else {
            go((prevL-(i+2))/2, prevL, i-1, offset - prevL - i - 3);
        }
    }

}