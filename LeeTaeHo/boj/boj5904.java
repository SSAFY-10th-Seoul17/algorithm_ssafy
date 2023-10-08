import java.io.*;

public class boj5904 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int length = 3;
        int k = 0;
        while(length < n){
            length *= 2;
            k++;
            length += (k + 3);
        }
        System.out.println(moo(length, k, n));
    }
    private static char moo(int length, int k, int n){
        int prevLen = (length - (k + 3)) / 2;
        int midLen = prevLen + k + 3;

        if(k == 0){
            if(n == 1){
                return 'm';
            }else{
                return 'o';
            }
        }
        if(n <= prevLen){
            return moo(prevLen, k - 1, n);
        } else if(n <= midLen){
            if(n == prevLen + 1){
                return 'm';
            }else{
                return 'o';
            }
        } else{
            n -= midLen;
            return moo(length - midLen, k - 1, n);
        }
    }
}
