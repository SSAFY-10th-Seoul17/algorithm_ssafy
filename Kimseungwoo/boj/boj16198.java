package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj16198 {

    static int N;
    static long max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        ArrayList<Integer> a = new ArrayList<>();

        for(int i = 0; i < N; i++){
            a.add(Integer.parseInt(st.nextToken()));
        }

        getEnergy(N, 0, a);
        System.out.println(max);
    }

    private static void getEnergy(int n, long curSum, ArrayList<Integer> paramArr){
        if(n == 2){
            if(max < curSum) max = curSum;
            return;
        }

        for(int i = 1; i < paramArr.size()-1; i++){
            int tmp = paramArr.get(i);
            int energy = paramArr.get(i-1)*paramArr.get(i+1);
            paramArr.remove(i);
            getEnergy(n-1, curSum+energy, paramArr);
            paramArr.add(i, tmp);

        }
    }


}
