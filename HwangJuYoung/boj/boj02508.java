import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            char[][] box = new char[r][c];
            for (int j = 0; j < box.length; j++) {
                st = new StringTokenizer(br.readLine());
                box[j] = st.nextToken().toCharArray();
            }

            int cnt = 0;
            for (int j = 0; j < box.length; j++) {
                for (int k = 0; k < box[j].length; k++) {
                    if (box[j][k] == '>' && k < c - 2) {
                        if (box[j][k+1] == 'o' && box[j][k+2] == '<') {
                            cnt++;
                        }
                    } else if (box[j][k] == 'v' && j < r - 2) {
                        if (box[j+1][k] == 'o' && box[j+2][k] == '^') {
                            cnt++;
                        }
                    }
                }
            }
            System.out.println(cnt);
        }
    }
}