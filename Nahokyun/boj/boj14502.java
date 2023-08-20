package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class boj14502 {
    static int[] dx=new int[] {-1,0,0,1};
    static int[] dy=new int[] {0,1,-1,0};
    static boolean[][] flag;
    static int[][] arr;
    static int[][] afterWall;
    static int max=Integer.MIN_VALUE;
    static int n;
    static int m;
    static ArrayList<Position> virusPosition=new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        arr=new int[n][m];
        afterWall=new int[n][m];
        flag=new boolean[n][m];

        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                int input=Integer.parseInt(st.nextToken());
                arr[i][j]=input;
                if(input==2) {
                    virusPosition.add(new Position(i,j));
                }
            }
        }//입력 종료
        init();
        buildWall(0,0,0);

        System.out.println(max);

    }
    private static void init() {
        for(int i=0;i<n;i++) {
            afterWall[i]=arr[i].clone();
        }
    }//init종료
    private static void buildWall(int count,int lastY,int lastX) {
        if(count==3) {
            //미리 기둥 세워진 맵 백업
            int[][] backup=new int[n][m];
            for(int i=0;i<n;i++) {
                backup[i]=afterWall[i].clone();
            }

            //바이러스 bfs 실행
            for(int i=0;i<virusPosition.size();i++) {
                for(int j=0;j<n;j++) {
                    Arrays.fill(flag[j], false);
                }

                bfs(virusPosition.get(i).second,virusPosition.get(i).first);
            }

            //안전구역 크기 확인, 비교 후 갱신
            int countSafe=0;
            for(int[] i:afterWall) {
                for(int j:i)
                    if(j==0)
                        countSafe++;
            }
            if(max<countSafe)
                max=countSafe;

            //바이러스 퍼지기전 원상복구
            for(int i=0;i<n;i++) {
                afterWall[i]=backup[i].clone();
            }

            return;
        }

        for(int i=lastY;i<n;i++) {
            for(int j=lastX;j<m;j++) {
                if(j==m-1) {
                    lastX=0;
                    lastY++;
                }
                if(afterWall[i][j]==0) {
                    afterWall[i][j]=1;
                    buildWall(count+1,i,j);
                    afterWall[i][j]=0;
                }
            }
        }
    }//buildWall종료
    private static void bfs(int x,int y) {
        Queue<Position> q=new LinkedList<>();
        q.add(new Position(y,x));
        flag[y][x]=true;
        while(!q.isEmpty()) {
            int curY=q.peek().first;
            int curX=q.peek().second;
            q.poll();
            afterWall[curY][curX]=2;
            flag[curY][curX]=true;
            for(int i=0;i<4;i++) {
                int cmpY=curY+dy[i];
                int cmpX=curX+dx[i];
                if(cmpY>=0&&cmpY<n&&cmpX>=0&&cmpX<m
                        &&!flag[cmpY][cmpX]&&afterWall[cmpY][cmpX]==0) {
                    q.add(new Position(cmpY,cmpX));
                }
            }
        }

    }
}
class Position{
    int first;
    int second;
    public Position(int y, int x) {
        this.first = y;
        this.second = x;
    }

}

