package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.awt.Point;

public class boj4179 {
    static ArrayDeque<Point> fire = new ArrayDeque<>();

    private static char[][] map;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };
    static int[][] fireTime;
    static int[][] escapeTime;
    private static int r;
    private static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        String s;

        map = new char[r][c];
        fireTime = new int[r][c];
        escapeTime = new int[r][c];

        Point start = null;

        for (int i = 0; i < r; i++) {
            s = br.readLine();
            for (int j = 0; j < c; j++) {
                fireTime[i][j]=1000000;
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'F') {
                    fire.add(new Point(j, i));
                }
                if (map[i][j] == 'J') {
                    start = new Point(j, i);
                }
            }
        }
        fireBfs();
        // 입력 종료

        int result=jihoonBfs(start);
        System.out.println(result==-1?"IMPOSSIBLE":result);
    }

    private static int jihoonBfs(Point start) {
        boolean[][] flag=new boolean[r][c];
        ArrayDeque<Point> q = new ArrayDeque<>();
        q.add(start);
        flag[start.y][start.x]=true;

        int curTime=1;
        while(!q.isEmpty()) {
            ArrayDeque<Point> tmp=new ArrayDeque<>();
            while(!q.isEmpty()) {
                Point cur = q.poll();
                int curX = cur.x;
                int curY = cur.y;
                if(curX==0||curX==c-1||curY==0||curY==r-1)
                    return curTime;
                escapeTime[curY][curX] = curTime;

                for (int i = 0; i < 4; i++) {
                    int cmpX = curX + dx[i];
                    int cmpY = curY + dy[i];

                    if (cmpX >= 0 && cmpX < c && cmpY >= 0 && cmpY < r
                            && map[cmpY][cmpX] != '#'&&!flag[cmpY][cmpX]
                            &&curTime<fireTime[cmpY][cmpX]) {
                        flag[cmpY][cmpX]=true;
                        tmp.add(new Point(cmpX,cmpY));
                    }
                }
            }
            curTime++;
            q=tmp;
        }
        return -1;
    }

    private static void fireBfs() {
        boolean[][] flag=new boolean[r][c];
        int curTime=0;
        while (!fire.isEmpty()) {
            ArrayDeque<Point> tmp = new ArrayDeque<>();
            while (!fire.isEmpty()) {
                Point cur = fire.poll();
                int curX = cur.x;
                int curY = cur.y;
                fireTime[curY][curX] = curTime;
                flag[curY][curX]=true;

                for (int i = 0; i < 4; i++) {
                    int cmpX = curX + dx[i];
                    int cmpY = curY + dy[i];

                    if (cmpX >= 0 && cmpX < c && cmpY >= 0 && cmpY < r
                            && map[cmpY][cmpX] != '#'&&!flag[cmpY][cmpX]) {
                        flag[cmpY][cmpX]=true;
                        tmp.add(new Point(cmpX,cmpY));
                    }
                }
            }
            curTime++;
            fire=tmp;
        }

    }
}

