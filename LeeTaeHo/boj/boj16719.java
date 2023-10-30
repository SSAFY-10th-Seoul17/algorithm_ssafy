import java.io.*;
import java.util.*;
public class boj16719 {
    static String line;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();
        size = line.length();
        visited = new boolean[size];

        getLine(0, size - 1);

        System.out.println(sb);
    }

    static void getLine(int left, int right){
        if(left > right) return;

        int mid = left;

        for (int i = left; i <= right; i++) {
            if(line.charAt(mid) > line.charAt(i)) mid = i;
        }

        visited[mid]= true;
        for (int i = 0; i < size; i++) {
            if(visited[i]) sb.append(line.charAt(i));
        }
        sb.append("\n");

        getLine(mid + 1, right);
        getLine(left, mid - 1);
    }
}