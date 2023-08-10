import java.io.*;
import java.util.*;

public class Main {
    static int[] visited;
    static StringBuilder sBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());



        System.out.println(sBuilder.toString());

    } // end of main

    private static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<>();
    } // end of BFS

} // end of class