import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj3066 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            result.append(getLIS(br, n)).append("\n");
        }

        System.out.println(result);
    }

    static int[] ports = new int[40000 + 1];
    private static int getLIS(BufferedReader br, int n) throws IOException {
        int top = 0;
        ports[0] = Integer.MAX_VALUE;

        while (n-- > 0) {
            int port = Integer.parseInt(br.readLine());

            if (port > ports[top]) {
                ports[++top] = port;
            } else {
                ports[-Arrays.binarySearch(ports, 0, top, port) - 1] = port;
            }
        }

        return top + 1;
    }

}

