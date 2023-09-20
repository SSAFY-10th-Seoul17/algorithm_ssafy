package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.awt.Point;

public class boj14719 {

    private static Point[] h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        h = new Point[m];
        for (int i = 0; i < m; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            h[i] = new Point(tmp, tmp);
        }

        //point.x는 최대값, point.y는 빗물이 가능한 높이

        int left = h[0].y;
        for (int i = 1; i < m; i++) {
            if (h[i].y > left) {
                left = h[i].y;

            }
            h[i].x = left;
        }
        int right = h[m - 1].y;
        for (int i = m - 1; i >= 0; i--) {
            if (h[i].y > right) {
                right = h[i].y;
            }
            h[i].x = Math.min(right, h[i].x);
        }


        int count = 0;
        for (int i = 0; i < m; i++) {
            if (h[i].x >= h[i].y)
                count += h[i].x - h[i].y;
        }

        System.out.println(count);


    }


}

//        for(int i=1;i<m;i++) {
//            if(h[i].y<h[i-1].y) {//앞보다 작으면 최댓값 그대로 가져옴
//                h[i].x=h[i-1].x;
//            }//
//            if(h[i].y>h[i-1].y&&h[i].y<h[i-1].x) {//앞의값 보다 크지만 앞의 최댓값보다는 작을때
//                int tmp=i-1;
//                while(tmp>0) {//뒤로가면서 최댓값을 작은값으로 갱신해줌
//                    if(h[tmp].y<=h[i].y) {
//                        h[tmp].x=h[i].x;
//                        tmp--;
//                    }else {
//                        break;
//                    }
//                }
//            }else if(h[i].y>h[i-1].y&&h[i].y>h[i-1].x){//앞의값보다 크고 앞의 최댓값보다도 클때
//                //직전 최대값으로 맞춰줌
//                int tmp=i-1;
//                while(tmp>0) {//뒤로가면서 최댓값을 작은값으로 갱신해줌
//                    if(h[tmp].y<=h[i-1].y) {
//                        h[tmp].x=h[i-1].x;
//                        tmp--;
//                    }else {
//                        break;
//                    }
//                }
//
//            }
//
//        }
//        int idx=0;
//        int height=h[0].x;
//        h[idx].x=h[idx].y;
//        while(idx<=m-1) {
//            if(height<=h[idx].x) {
//                if(height<h[idx].y)
//                    break;
//                h[idx].x=height;
//                idx++;
//            }else {
//                break;
//            }
//        }
//        idx=m-1;
//        h[idx].x=h[idx].y;
//        height=h[m-1].x;
//        while(idx>=0) {
//            if(height<=h[idx].x) {
//                if(height<h[idx].y)
//                    break;
//                h[idx].x=height;
//                idx--;
//            }else {
//                break;
//            }
//        }
//        int count=0;
//        for(int i=0;i<m;i++) {
//            if(h[i].x>=h[i].y)
//                count+=h[i].x-h[i].y;
//        }