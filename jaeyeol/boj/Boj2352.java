import java.io.*;
import java.util.*;

public class Boj2352 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Integer> connections = new ArrayList<>(n);
        connections.add(Integer.MAX_VALUE);

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(st.nextToken());

            if (number > connections.get(connections.size() - 1)) {
                connections.add(number);
            } else {
                int idx = Collections.binarySearch(connections, number);
                connections.set(idx < 0 ? -(idx + 1) : idx, number);
            }
        }

        System.out.println(connections.size());
    }

}

