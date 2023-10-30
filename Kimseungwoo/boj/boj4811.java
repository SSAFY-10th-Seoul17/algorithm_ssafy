package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj4811 {

    static int N;
    private static long[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        map = new long[31][31];
        mapInit();
        while(true){
            N = Integer.parseInt(br.readLine());
            if(N == 0)
                break;
            sb.append(map[N][N]).append("\n");
        }
        System.out.println(sb.toString());
    } // end of main
    private static void mapInit(){
        for(int i = 1; i < 31; i++){
            map[0][i] = 1;
        }

        for(int w = 1; w < 31; w++){
            for(int h = 0; h < 31; h++){
                if(h < w)
                    continue;
                map[w][h] = map[w-1][h] + map[w][h-1];
            }
        }
    }
} // end of class
