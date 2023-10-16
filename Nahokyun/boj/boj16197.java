package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.awt.Point;

public class boj16197 {

    private static char[][] map;
    private static int[] dx = { 0, 0, 1, -1 };
    private static int[] dy = { 1, -1, 0, 0 };
    private static int n;
    private static int m;

    static int minMove=401;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        boolean isSecond = false;
        Point first = null;
        Point second = null;
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'o') {
                    if (!isSecond) {
                        first = new Point(j, i);
                        isSecond = true;
                    } else {
                        second = new Point(j, i);
                    }
                }
            }
        }
        // 입력 종료


        track(first, second, 0);
        System.out.println(minMove==401?-1:minMove);

    }

    private static void track(Point first, Point second, int moveCount) {
        if(moveCount>=10||(first.x==second.x&&first.y==second.y)) {

            return;
        }
        int f_curX=first.x;
        int f_curY=first.y;
        int s_curX=second.x;
        int s_curY=second.y;

        for(int i=0;i<4;i++) {
            int f_cmpX=first.x+dx[i];
            int f_cmpY=first.y+dy[i];
            int s_cmpX=second.x+dx[i];
            int s_cmpY=second.y+dy[i];

            if(f_cmpX>=0&&f_cmpY>=0&&f_cmpY<n&&f_cmpX<m
                    &&s_cmpX>=0&&s_cmpY>=0&&s_cmpY<n&&s_cmpX<m) {
                //둘다 범위에 있을때
                if(map[f_cmpY][f_cmpX]=='#'&&map[s_cmpY][s_cmpX]!='#') {
                    second.x=s_cmpX;
                    second.y=s_cmpY;
                    track(first,second,moveCount+1);
                    second.x=s_curX;
                    second.y=s_curY;
                }else if(map[f_cmpY][f_cmpX]!='#'&&map[s_cmpY][s_cmpX]=='#') {
                    first.x=f_cmpX;
                    first.y=f_cmpY;
                    track(first,second,moveCount+1);
                    first.x=f_curX;
                    first.y=f_curY;
                }else if(map[f_cmpY][f_cmpX]=='#'&&map[s_cmpY][s_cmpX]=='#') {
                    continue;
                }else {
                    first.x=f_cmpX;
                    first.y=f_cmpY;
                    second.x=s_cmpX;
                    second.y=s_cmpY;
                    track(first,second,moveCount+1);
                    second.x=s_curX;
                    second.y=s_curY;
                    first.x=f_curX;
                    first.y=f_curY;
                }
            }else if(f_cmpX>=0&&f_cmpY>=0&&f_cmpY<n&&f_cmpX<m
                    &&(s_cmpX<0||s_cmpX>=m||s_cmpY<0||s_cmpY>=n)) {
                minMove=Math.min(minMove, moveCount+1);
            }else if(s_cmpX>=0&&s_cmpY>=0&&s_cmpY<n&&s_cmpX<m
                    &&(f_cmpX<0||f_cmpX>=m||f_cmpY<0||f_cmpY>=n)) {
                minMove=Math.min(minMove, moveCount+1);
            }


        }





    }

}
