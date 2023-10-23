import java.util.*;
import java.io.*;

/**
 * map을 저장할 때, 빈 곳의 좌표, 선생님의 좌표를 리스트에 담아 놓습니다.
 * 빈 곳 C 3 으로 조합을 통해 3개의 장애물을 설치한 후, 선생님을 4방 탐색하여 학생을 만나지 않는 경우에만 result를 true로 바꿔주었습니다.
 */
public class BOJ18428_감시피하기 {
    static int N,picks[];
    static char[][] map;
    static List<int[]> list, teacherList;
    static boolean result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        list = new ArrayList<>();
        teacherList = new ArrayList<>();

        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'X'){
                    list.add(new int[] {i,j});
                }
                else if (map[i][j] == 'T'){
                    teacherList.add(new int[] {i,j});
                }
            }
        }

        picks = new int[3];
        result = false;
        build(0,0,list.size());
        if (result){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }

    private static void build(int start, int cnt, int size){
        if (cnt == 3){ // 장애물 3개 설치 위치 정하기 -> nC3
            char[][] copymap = new char[N][N];
            for (int i=0; i<N; i++){
                for (int j=0; j<N; j++){
                    copymap[i][j] = map[i][j];
                }
            }
            isAble(Arrays.copyOf(picks,3), copymap);
            return;
        }
        for (int i=start; i<size; i++){
            picks[cnt] = i;
            build(i+1, cnt+1, size);
        }
    }
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    private static void isAble(int[] picks, char[][] copymap ){
        // 3개 벽 설치
        for (int i=0; i<3; i++){
            int[] pick = list.get(picks[i]);
            copymap[pick[0]][pick[1]] = 'O';
        }

        for (int i=0; i<teacherList.size(); i++) {
            int[] teacher = teacherList.get(i);
            int tr = teacher[0];
            int tc = teacher[1];

            for (int j = 0; j < 4; j++) { // 4 ways
                int nr = tr;
                int nc = tc;
                while(true){
                    nr +=dr[j];
                    nc += dc[j];
                    if (nr>= 0 && nr < N && nc >= 0 && nc < N) { // 범위를 벗어나지 않으면
                        if (copymap[nr][nc] == 'O') { // 장애물을 만나면
                            break;
                        } else if (copymap[nr][nc] == 'S') { // 학생을 만나면 - 걸린다
                            return;
                        }
                    }else{
                        break;
                    }
                }
            }
        }
        result = true;
    }

}
