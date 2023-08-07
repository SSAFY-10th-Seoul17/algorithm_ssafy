import java.util.*;
import java.io.*;

class Main{
    /*
        1. 초기 모든 노드는 흰색이다
        2. 부모 노드를 칠하면 자식 노드들도 같은색으로 칠해짐
        3. 입력 상태를 보면 부모와 자식 노드의 색이 다른 상황 -> 자식노드가 어떤 서브트리의 부모노드역할로 색칠한 경우를 뜻함
        --> 서브트리의 root 역할로 선택된 노드의 개수를 찾는다
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] color = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            color[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            // 연결된 노드의 색이 다르다는 것은 한 노드가 어떤 서브트리의 루트노드로 색칠되었다는 의미
            if(color[a] != color[b]) cnt++;
        }
        System.out.println(color[1] != 0 ? cnt+1 : cnt);    // 전체 트리의 root노드 체크
        br.close();
    }
}