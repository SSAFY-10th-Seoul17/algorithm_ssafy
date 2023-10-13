import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj5904 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        System.out.println(solve(N));
    }

    private static String solve(int N) {

        int index = 0;
        int length = 3;

        while (length < N){
            index ++;  // 0 3
            length = length * 2 + index + 3 ;
        }

        while (length > 3){
            int preLength = (length - (index + 3))/2;
            if (preLength > N) {
                length = preLength;
            }else if(preLength + index + 3 < N) {
                length = preLength;
                N -= (preLength + index + 3);
            }else{
                return N - preLength == 1 ? "m" : "o";
            }
            index --;
        }

        return N == 1 ? "m" : "o";
    }
}
