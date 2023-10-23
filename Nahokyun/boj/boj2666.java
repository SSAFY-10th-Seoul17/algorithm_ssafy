package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj2666 {

    private static int size;
    static int max = Integer.MAX_VALUE;
    private static boolean[] wall;
    private static int[] seq;
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        wall = new boolean[n + 2];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 2; i++) {
            wall[Integer.parseInt(st.nextToken())] = true;
        }
        size = Integer.parseInt(br.readLine());

        seq = new int[size + 1];
        for (int i = 1; i <= size; i++) {
            seq[i] = Integer.parseInt(br.readLine());
        }
        int leftdoor = -1;
        int rightdoor = -1;
        boolean flag = true;
        for (int i = 1; i <= n; i++) {
            if (wall[i] && flag) {
                leftdoor = i;
                flag = false;
            } else if (wall[i]) {
                rightdoor = i;
            }
        }

        track(1, 0, leftdoor, rightdoor);

        System.out.println(max);
    }

    static private void track(int curIdx, int curMove, int leftdoor, int rightdoor) {

//        System.out.println("leftdoor = " + leftdoor+"rightdoor = "+rightdoor);
//        System.out.println("curMove = " + curMove);
//        System.out.println(Arrays.toString(wall));


        if (curIdx == size + 1) {
            max = Math.min(curMove, max);
            return;
        }
        int num = seq[curIdx];
//        System.out.println("num = " + num);
//        System.out.println();

        if (wall[num]) {
            track(curIdx + 1, curMove, leftdoor, rightdoor);
            return;
        }


        if (leftdoor > num) {

            wall[num] = true;
            wall[leftdoor] = false;
            track(curIdx + 1, curMove + leftdoor - num, num, rightdoor);
            wall[num] = false;
            wall[leftdoor] = true;
        } else if (rightdoor < num) {
            wall[num] = true;
            wall[rightdoor] = false;
            track(curIdx + 1, curMove + num - rightdoor, leftdoor, num);
            wall[num] = false;
            wall[rightdoor] = true;
        } else {
            wall[num] = true;
            wall[leftdoor] = false;
            track(curIdx + 1, curMove + num - leftdoor, num, rightdoor);
            wall[num] = false;
            wall[leftdoor] = true;

            wall[num] = true;
            wall[rightdoor] = false;
            track(curIdx + 1, curMove + rightdoor - num, leftdoor, num);
            wall[num] = false;
            wall[rightdoor] = true;
        }
    }
/*    static private int find(int num,int flag){

        if(flag==0){
            int count=1;
            while(num-count>=0&&!wall[num-count]){
                count++;
            }
            return count==num?0:count;
        }else{
            int count=1;
            while(num+count<=n+1&&!wall[num+count]){
                count++;
            }
            return count+num==n?0:count-1;
        }
    }*/
}
