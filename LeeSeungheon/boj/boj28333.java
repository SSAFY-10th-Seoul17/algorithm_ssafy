import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj28333 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            City[] cities = new City[N + 1];

            for (int idx = 1; idx <= N; idx++) {
                cities[idx] = new City(idx, null);
            }

            for (int idx = 0; idx < M; idx++) {
                st = new StringTokenizer(br.readLine());
                int fromIdx = Integer.parseInt(st.nextToken());
                int toIdx = Integer.parseInt(st.nextToken());

                cities[fromIdx].nextCity = new City(toIdx, cities[fromIdx].nextCity);
            }
            boolean[] checkCity = solve(cities, N);

            for (int idx = 1; idx < checkCity.length; idx++) {
                if(!checkCity[idx]){
                    continue;
                }
                sb.append(idx).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean[] solve(City[] cities, int N) {

        boolean[] checkCity = new boolean[N + 1];
        boolean[] visited = new boolean[N + 1];
        Queue<City> queue = new ArrayDeque<>();
        queue.offer(new City(1, cities[1].nextCity, null , 1));
        int minCount = 0;

        while (!queue.isEmpty()) {
            City curCity = queue.poll();

            if (curCity.idx == N && (minCount == 0 || minCount == curCity.count)) {
                if (minCount == 0) {
                    minCount = curCity.count;
                }
                System.out.println("/" + curCity.count);
                checkRoot(checkCity, curCity);
                continue;
            }

            if (visited[curCity.idx] || minCount != 0) {
                continue;
            }
            visited[curCity.idx] = true;

            for (City nextCity = curCity.nextCity; nextCity != null; nextCity = nextCity.nextCity) {
                if (visited[nextCity.idx]) {
                    continue;
                }
                queue.offer(new City(cities[nextCity.idx].idx, cities[nextCity.idx].nextCity , curCity, curCity.count + 1));
            }
        }

        return checkCity;
    }

    private static void checkRoot(boolean[] checkCity, City curCity) {
        Queue<City> beforeQueue = new ArrayDeque<>();
        beforeQueue.offer(curCity);

        while (!beforeQueue.isEmpty()) {
            City beforeCity = beforeQueue.poll();

            checkCity[beforeCity.idx] = true;

            if(beforeCity.beforeCity == null){
                continue;
            }

            for (City before = curCity.beforeCity; before != null; before = before.beforeCity) {
                if (checkCity[before.idx]) {
                    continue;
                }
                beforeQueue.offer(before);
            }
        }
    }


    private static class City {
        int idx;
        City nextCity;
        City beforeCity;
        int count;

        public City(int idx, City nextCity) {
            this.idx = idx;
            this.nextCity = nextCity;
        }

        public City(int idx, City nextCity, City beforeCity, int count) {
            this.idx = idx;
            this.nextCity = nextCity;
            this.beforeCity = beforeCity;
            this.count = count;
        }
    }
}
