import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2666 {
    private static int result = Integer.MAX_VALUE;
    private static int L;
    private static int[] order;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int s = a < b ? a : b;
        int e = a > b ? a : b;
        L = Integer.parseInt(br.readLine());
        order = new int[L];
        for (int i = 0; i < L; i++){
            order[i] = Integer.parseInt(br.readLine());
        }
        dfs(0,s,e,0);
        System.out.println(result);
    }
    public static void dfs(int index,int s, int e, int res){
        if (res > result) return;
        if (index == L){
            result = Math.min(result, res);
            return;
        }
        if (order[index] <= s){
            dfs(index+1,order[index],e,res+s-order[index]);
        } else if (order[index] >= e){
            dfs(index+1,s,order[index],res+order[index]-e);
        } else {
            dfs(index+1,order[index],e,res+order[index]-s);
            dfs(index+1,s,order[index],res+e-order[index]);
        }
    }
}