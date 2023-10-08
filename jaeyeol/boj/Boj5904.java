import java.io.*;
import java.util.*;

public class Boj5904 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Integer.parseInt(br.readLine());

        System.out.println(sequenceWordIsM(n) ? 'm' : 'o');
    }

    private static boolean sequenceWordIsM(long n) {
        List<Long> lengths = new ArrayList<>();
        long length = 0;

        while (length <= n) {
            lengths.add(length += length + lengths.size() + 3);
        }

        return wordIsM(lengths, n, lengths.size() - 1);
    }

    private static boolean wordIsM(List<Long> lengths, long n, int idx) {
        if (idx <= 0) {
            return n == 1;
        }
        
        long left = lengths.get(idx - 1);
        long right = lengths.get(idx - 1) + idx + 3;
        
        if (n > left && n <= right) {
            return n == left + 1;
        }

        return wordIsM(lengths, n > right ? n - right : n, idx - 1);
    }

}

