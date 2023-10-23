package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj3020 {

    static int N; // cave length
    static int H; // cave height
    private static int[] seoksun;
    private static int[] jongyou;

    private static int cnt = 0;
    private static int curMid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        seoksun = new int[N/2+1];
        jongyou = new int[N/2+1];

        int[] conf = new int[H+1];

        int seokIdx = 1;
        int jongIdx = 1;
        for(int i = 0; i < N; i++){ // 200,000
            int c = Integer.parseInt(br.readLine());

            if(i % 2 == 0){
                seoksun[seokIdx++] = c;
            } else {
                jongyou[jongIdx++] = c;
            }
        }
        curMid = N/2+1;
        Arrays.sort(seoksun);
        Arrays.sort(jongyou);

        for(int i = 1; i <= H; i++){
            bs(1, N/2, seoksun, i);
            conf[i] += (N/2-curMid+1);
            curMid = N/2+1;
        }


        for(int i = 1; i <= H; i++){
            bs(1, N/2, jongyou, i);
            conf[H-i+1] += (N/2-curMid+1);
            curMid = N/2+1;
        }
        // find minVal
        Arrays.sort(conf);
        int min = conf[1];
        int ansCnt = 1;
        for(int i = 2; i <= H; i++){
            if(min < conf[i]){
                break;
            } else {
                ansCnt++;
            }
        }
        System.out.println(min + " " + ansCnt);
    }

    private static void bs(int start, int end, int[] p, int target) {
        if(start > end) return;

        int mid = start + (end - start) / 2;

        if (p[mid] >= target) {
            if(mid < curMid){
                curMid = mid;
            }
            bs(start, mid-1, p, target);
        } else {
            bs(mid+1, end, p, target);
        }
    }
}
