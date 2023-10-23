import java.util.*;
import java.io.*;

class Main {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        hanoi(n, 1, 2, 3);
        String result = sb.toString();
        System.out.println(Arrays.stream(result.split("\n")).count());
        System.out.println(result);

        br.close();
    }

    public static void hanoi(int n, int start, int mid, int to) {
        if(n==1) {
            sb.append(start + " " + to + "\n");
            return;
        }
        // 맨 마지막꺼 빼고 가운데로 옮겨둔다
        hanoi(n-1, start, to, mid);
        sb.append(start + " " + to + "\n"); // 맨 마지막을 옮김

        // 가운데가 시작점이 되고, n-1이 가장 큰 원판으로 n-1을 to로 옮기는 것을 목표로 한다
        hanoi(n-1, mid, start, to);
    }
}