import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println(moo(n));
        br.close();
    }
    public static char moo(int num) {
        if(num == 1) return 'm';
        else if (num <= 3) return 'o';

        // k찾기
        int k=0, size = 3;
        while(size < num) {
            size = (2*size + (++k+3));   // k + 2개의 o와 1개의 m
        }
        int left = (size-(k+3)) / 2;    // 중앙 기준 m 바로 왼쪽
        if(num > size - left) return moo(num - size + left); // 중앙 기준 오른쪽 범위?
        else if(num == left + 1) return 'm';    // 중앙
        else return 'o';
    }
}
