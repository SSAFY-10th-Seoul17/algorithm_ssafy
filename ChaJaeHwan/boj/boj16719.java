import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class boj16719 {

    static char[] ch;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ch = br.readLine().toCharArray();
        visited = new boolean[ch.length];
        recursive(0, ch.length-1);
        System.out.println(sb);
    }

    static void recursive(int from, int to) {
        if (from > to) {
            return;
        }
        char min = 'Z' + 1;
        int minIdx = 101;
        for (int i = from; i <= to; i++) {
            if (!visited[i] && (ch[i] < min)) {
                min = ch[i];
                minIdx = i;
            }
        }
        visited[minIdx]= true;
        for (int i = 0; i < ch.length; i++) {
            if (visited[i]) {
                sb.append(ch[i]);
            }
        }
        sb.append("\n");
        recursive(minIdx+1, to);
        recursive(from, minIdx - 1);
    }

}
