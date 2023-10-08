import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj14719 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        int amount = 0;
        int[] stack = new int[w];
        int top = -1;
        int maxHeight = 0;
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < w; i++) {
            int height = Integer.parseInt(st.nextToken());
            if (height < maxHeight) {
                stack[++top] = height;
                continue;
            }

            while (top >= 0) {
                amount += maxHeight - stack[top--];
            }
            maxHeight = height;
        }

        maxHeight = 0;
        while (top >= 0) {
            if (stack[top] >= maxHeight) {
                maxHeight = stack[top];
            } else {
                amount += maxHeight - stack[top];
            }
            top--;
        }

        System.out.println(amount);
    }

}

