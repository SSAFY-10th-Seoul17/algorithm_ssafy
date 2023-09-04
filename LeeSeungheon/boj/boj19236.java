import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj19236 {

    static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Fish[][] fishMap = new Fish[4][4];
        for (int row = 0; row < 4; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int column = 0; column < 4; column++) {
                fishMap[row][column] = new Fish(row, column, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1);
            }
        }

        // 상어 투입
        Shark shark = new Shark(0, 0, -1, fishMap[0][0].dir, fishMap[0][0].number);
        fishMap[0][0] = shark;

        solve(fishMap, shark);
        System.out.println(result);
    }

    private static void solve(Fish[][] fishMap, Shark shark) {
        //물고기 이동
        moveFish(fishMap);
        //상어 이동
        moveShark(fishMap, shark);
    }

    private static void moveShark(Fish[][] fishMap, Shark shark) {
        int nextRow = shark.row + dy[shark.dir];
        int nextColumn = shark.column + dx[shark.dir];

        while (nextRow < 4 && nextColumn < 4 && nextRow >= 0 && nextColumn >= 0 ){
            Fish[][] newFishMap = new Fish[4][4];


            if(fishMap[nextRow][nextColumn].number == 0){
                nextRow += dy[shark.dir];
                nextColumn += dx[shark.dir];
                continue;
            }

            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    if (fishMap[row][col] instanceof Shark) {
                        newFishMap[row][col] = new Shark((Shark) fishMap[row][col]);
                    } else {
                        newFishMap[row][col] = new Fish(fishMap[row][col]);
                    }
                }
            }
            Shark newShark = new Shark(nextRow, nextColumn, -1, newFishMap[nextRow][nextColumn].dir, shark.count + newFishMap[nextRow][nextColumn].number);

            newFishMap[nextRow][nextColumn] = newShark;
            newFishMap[shark.row][shark.column] = new Fish(shark.row, shark.column, 0,0);

            solve(newFishMap, newShark);

            nextRow += dy[shark.dir];
            nextColumn += dx[shark.dir];
        }

        result = Math.max(result , shark.count);
    }


    private static void moveFish(Fish[][] fishMap) {
        PriorityQueue<Fish> pq = new PriorityQueue<>();
        for (int row = 0; row < 4; row++) {
            for (int column = 0; column < 4; column++) {
                if (fishMap[row][column].number == 0) {
                    continue;
                }
                pq.offer(fishMap[row][column]);
            }
        }

        pq.poll();
        while (!pq.isEmpty()) {
            Fish curFish = pq.poll();
            if (curFish.number == 0) {
                continue;
            }
            while (curFish.row + dy[curFish.dir] >= 4 || curFish.row + dy[curFish.dir] < 0 ||
                    curFish.column + dx[curFish.dir] >= 4 || curFish.column + dx[curFish.dir] < 0 ||
                    fishMap[curFish.row + dy[curFish.dir]][curFish.column + dx[curFish.dir]].number == -1) {
                if (++curFish.dir > 7) {
                    curFish.dir = 0;
                }
            }



            int nextRow = curFish.row + dy[curFish.dir];
            int nextColumn = curFish.column + dx[curFish.dir];
            Fish nextFish = fishMap[nextRow][nextColumn];


            fishMap[curFish.row][curFish.column] = nextFish;
            fishMap[curFish.row][curFish.column].row = curFish.row;
            fishMap[curFish.row][curFish.column].column = curFish.column;

            fishMap[nextRow][nextColumn] = curFish;
            fishMap[nextRow][nextColumn].row = nextRow;
            fishMap[nextRow][nextColumn].column = nextColumn;
        }
    }

    private static class Shark extends Fish {

        int count;

        public Shark(Shark other) {
            super(other);
            this.count = other.count;
        }
        public Shark(int row, int column, int number, int dir, int count) {
            super(row, column, number, dir);
            this.count = count;
        }
    }

    private static class Fish implements Comparable<Fish> {

        int row;
        int column;
        int number;
        int dir;

        public Fish(Fish other) {
            this.row = other.row;
            this.column = other.column;
            this.number = other.number;
            this.dir = other.dir;
        }

        public Fish(int row, int column, int number, int dir) {
            this.row = row;
            this.column = column;
            this.number = number;
            this.dir = dir;
        }

        @Override
        public int compareTo(Fish o) {
            return Integer.compare(number, o.number);
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "row=" + row +
                    ", column=" + column +
                    ", number=" + number +
                    ", dir=" + dir +
                    '}';
        }
    }
}
