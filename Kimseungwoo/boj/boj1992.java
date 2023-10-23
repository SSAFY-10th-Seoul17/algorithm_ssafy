package toyproject.somedaybucket.myAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Tree {
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();

    static void quadTree(int x, int y, int size) {
        if(check(x, y, size)){
            sb.append(arr[x][y]);
            return;
        }

        int newSize = size/2;

        sb.append("(");

        quadTree(x, y, newSize);
        quadTree(x, y+newSize, newSize);
        quadTree(x+newSize, y, newSize);
        quadTree(x+newSize, y+newSize, newSize);

        sb.append(")");
    }

    public static boolean check(int x, int y, int size) {
        int value = arr[x][y];

        for(int i = x; i < x + size; i++) {
            for(int j = y; j < y + size; j++) {
                if(value != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}



public class boj1992 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.valueOf(st.nextToken());

        Tree.arr = new int[N][N];

        for(int i = 0; i<N; i++){
            String str = br.readLine();
            for(int j = 0; j<N; j++){
                Tree.arr[i][j] = str.charAt(j)-'0';
            }
        }

        Tree.quadTree(0, 0, N);

        System.out.println(Tree.sb);
    }
}
