import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 2666. 벽장문의 이동
 */
public class boj2666 {
    /**
     * closetCount: 벽장의 수, usingClosetCount: 사용할 벽장의 수,
     * openClosetOne: 열려진 벽장1, openClosetTwo: 열려진 벽장2,
     * minMoveCount: 벽장문의 최소 이동횟수
     */
    static int closetCount, usingClosetCount, openClosetOne, openClosetTwo, minMoveCount = Integer.MAX_VALUE;
    /**
     * usingClosets: 사용할 벽장들을 순서대로 정렬한 배열.
     */
    static int[] usingClosets;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        closetCount = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        openClosetOne = Integer.parseInt(st.nextToken());
        openClosetTwo = Integer.parseInt(st.nextToken());
        if(openClosetOne > openClosetTwo) { // 열려진 벽장이 오름차순 정렬이 안 된 경우 정렬.
            int temp = openClosetOne;
            openClosetOne = openClosetTwo;
            openClosetTwo = temp;
        }

        usingClosetCount = Integer.parseInt(br.readLine());
        usingClosets = new int[usingClosetCount];
        for(int i = 0; i < usingClosetCount; i++) {
            usingClosets[i] = Integer.parseInt(br.readLine());
        }

        dfs(openClosetOne, openClosetTwo, 0, 0);

        System.out.println(minMoveCount);
    }

    public static void dfs(int openOne, int openTwo, int count, int moveCount) {
        if(count == usingClosetCount) { // 사용하려는 벽장을 모두 사용.
            minMoveCount = Math.min(minMoveCount, moveCount);
            return;
        }

        if(moveCount >= minMoveCount) { // 아직 사용할 문이 남아있는데, 이미 최소 이동횟수를 넘겼을 경우
            return;
        }

        if(usingClosets[count] == openOne || usingClosets[count] == openTwo) { // 사용하려는 벽장이 열려진 벽장인 경우
            dfs(openOne, openTwo, count + 1, moveCount);
        } else if(usingClosets[count] > openOne && usingClosets[count] < openTwo) { // 사용하려는 벽장이 열려진 벽장들 사이에 있는 경우
            dfs(usingClosets[count], openTwo, count + 1, moveCount + usingClosets[count] - openOne);
            dfs(openOne, usingClosets[count], count + 1, moveCount + openTwo - usingClosets[count]);
        } else if(usingClosets[count] < openOne) { // 사용하려는 벽장이 열려진 벽장의 좌측 벽장 중의 하나인 경우
            dfs(usingClosets[count], openTwo, count + 1, moveCount + openOne - usingClosets[count]);
        } else { // 사용하려는 벽장이 열려진 벽장의 우측 벽장 중의 하나인 경우
            dfs(openOne, usingClosets[count], count + 1, moveCount + usingClosets[count] - openTwo);
        }
    }
}
