import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2666 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int wall1 = Integer.parseInt(st.nextToken());
        int wall2 = Integer.parseInt(st.nextToken());

        int schedule = Integer.parseInt(br.readLine());
        int[] schedules = new int[schedule];
        for (int num = 0; num < schedule; num++) {
            schedules[num] = Integer.parseInt(br.readLine());
        }
        System.out.println(solve(schedules, 0, Math.min(wall1, wall2), Math.max(wall1, wall2), 0));

    }

    private static int solve(int[] schedules, int idx, int smallWall, int bigWall, int score) {
        for (int index = idx; index < schedules.length; index++) {
            if (schedules[index] == smallWall || schedules[index] == bigWall) {
                continue;
            } else if (schedules[index] < smallWall) {
                score += Math.abs(schedules[index] - smallWall);
                smallWall = schedules[index];
            } else if (schedules[index] > bigWall) {
                score += Math.abs(schedules[index] - bigWall);
                bigWall = schedules[index];
            } else {
                score += Math.min(solve(schedules, index+1, schedules[index], bigWall, Math.abs(schedules[index] - smallWall)),
                        solve(schedules, index+1, smallWall, schedules[index], Math.abs(schedules[index] - bigWall)));
                break;
            }
        }

        return score;
    }
}
