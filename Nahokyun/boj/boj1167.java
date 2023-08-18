package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj1167 {
    static ArrayList<DesAndDist>[] arr;
    static boolean[] flag;
    static int max = Integer.MIN_VALUE;
    static int first;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int curNode = Integer.parseInt(st.nextToken());
            while (true) {
                int des = Integer.parseInt(st.nextToken());
                if (des == -1)
                    break;
                int dist = Integer.parseInt(st.nextToken());
                arr[curNode].add(new DesAndDist(des, dist));
            }

        } // 입력 종료

        flag=new boolean[n+1];
        flag[1]=true;
        dfs(1, 0);
        flag=new boolean[n+1];
        flag[first]=true;
        dfs(first,0);

        System.out.println(max);
    }// end of main

    private static void dfs(int j, int sum) {
        int sz = arr[j].size();

        if (sum > max) {
            max = sum;
            first=j;
        }
        for (int i = 0; i < sz; i++) {
            DesAndDist tmp = arr[j].get(i);
            if (!flag[tmp.des]) {
                flag[tmp.des] = true;
                dfs(tmp.des, sum + tmp.dist);
            }
        }
    }
}

class DesAndDist {
    int des;
    int dist;

    public DesAndDist(int des, int dist) {
        this.des = des;
        this.dist = dist;
    }
}
