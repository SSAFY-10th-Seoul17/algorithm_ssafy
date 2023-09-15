import java.io.*;

public class Main {
    static int len, n, k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine()) - 1;
        len = 3;
        k = 0;

        while (len <= n) {
            len = len + ++k + 3 + len;
        }

        S(len,  k);

        
    }

    public static void S (int len, int k) {
        int s = (len - (k + 3)) / 2;
        int m = (len - (k + 3)) / 2 + k + 3;

        if (n < s) {
            S(s, k-1);
        }
        else if (n >= s && n < m) {
            if (n == s) {
                System.out.println('m');
            } else {
                System.out.println('o');
            }
        }
        else {
            n = n - m;
            S(s, k-1);
        }

    }
}
