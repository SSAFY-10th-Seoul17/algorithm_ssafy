import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int cnt = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Hanoi(N, 1, 2, 3);
        System.out.println(cnt);
        System.out.println(sb.toString());
    } // end of main

    static void Hanoi (int N, int start, int temp, int end) {
        if (N == 0) return;

        Hanoi(N-1, start, end, temp);
        sb.append(start).append(" ").append(end).append("\n");
        cnt++;
        Hanoi(N-1, temp, start, end);
    }
} // end of class

