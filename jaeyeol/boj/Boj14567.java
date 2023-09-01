import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj14567 {
    static class Subject {
        int number;
        int preCourses;
        Subject next;

        public Subject(int number, int preCount, Subject next) {
            this.number = number;
            this.preCourses = preCount;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Subject[] subjects = new Subject[n + 1];
        for (int i = 1; i <= n; i++) {
            subjects[i] = new Subject(i, 0, null);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            subjects[a].next = new Subject(b, 0, subjects[a].next);
            subjects[b].preCourses++;
        }

        System.out.println(getDistancesString(subjects, n));
    }

    private static String getDistancesString(Subject[] subjects, int n) {
        Queue<Subject> queue = new LinkedList<>();
        int[] semesters = new int[n + 1];
        int semester = 0;

        for (int i = 1; i <= n; i++) {
            if (subjects[i].preCourses == 0) {
                queue.add(subjects[i]);
            }
        }

        while (!queue.isEmpty()) {
            Queue<Subject> nextQueue = new LinkedList<>();
            semester++;

            while (!queue.isEmpty()) {
                Subject subject = queue.poll();
                semesters[subject.number] = semester;

                for (Subject next = subjects[subject.number].next; next != null; next = next.next) {
                    if (--subjects[next.number].preCourses == 0) {
                        nextQueue.add(subjects[next.number]);
                    }
                }
            }

            queue = nextQueue;
        }

        return getString(semesters);
    }

    private static String getString(int[] semesters) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < semesters.length; i++) {
            result.append(semesters[i]).append(" ");
        }
        return result.toString();
    }

}
