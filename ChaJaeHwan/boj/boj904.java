import java.io.*;
import java.util.*;

public class boj904 {

    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int length = 3;
        int k = 0;
        while(length < n) {
            length *= 2;
            k++;
            length += k+3;
        }
        System.out.println(solve(length, k));

    }

    public static char solve(int length, int k) {

        if(k == 0) {
            if (n == 1){
                return 'm';
            } else {
                return 'o';
            }
        }

        int prevLength = (length - (k+3)) /2;
        if( n <= prevLength) {
            return solve(prevLength, k-1);
        } else if( n <= prevLength + k+3) {
            if(n == prevLength + 1){
                return 'm';
            } else {
                return 'o';
            }
        } else {
            n -= (prevLength + k+3);
            return solve(prevLength, k-1);
        }
    }

}
