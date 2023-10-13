import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj2239_이승헌_300ms {

    static int[] square_flag = new int[9]; // 사분면
    static int[] row_flag = new int[9];
    static int[] column_flag = new int[9];
    static int[][] map;
    static List<Node> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        map = new int[9][9];
        list = new ArrayList<>();

        for (int row = 0; row < 9; row++) {
            String input = br.readLine();
            for (int column = 0; column < 9; column++) {
                if ((map[row][column] = input.charAt(column) - '0') == 0) {
                    list.add(new Node(row, column));
                    continue;
                }
                row_flag[row] |= (1 << (map[row][column]));
                column_flag[column] |= (1 << (map[row][column]));
                square_flag[(row / 3) * 3 + (column / 3)] |= (1 << (map[row][column]));
            }
        }
        solve(list.iterator());

        list.sort((o1, o2) -> {
            if (o1.row == o2.row) {
                return o1.column - o2.column;
            }
            return o1.row - o2.row;
        });
        tracking(0);

        for (int[] rows : map) {
            for (int idx : rows) {
                sb.append(idx);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void solve(Iterator<Node> iterator) {

        while (iterator.hasNext()) {
            Node node = iterator.next();

            int square = (node.row / 3) * 3 + (node.column / 3);
            int flag = row_flag[node.row] | column_flag[node.column] | square_flag[square];
            int count = 0;
            for (int i = 1; i <= 9; i++) {
                if ((flag & (1 << i)) != 0) {
                    continue;
                }
                if (++count > 1) {
                    break;
                }
            }

            if (count == 1) {
                int check = (int) (Math.log(1022 - flag) / Math.log(2));
                map[node.row][node.column] = check;

                row_flag[node.row] |= (1 << (check));
                column_flag[node.column] |= (1 << (check));
                square_flag[square] |= (1 << (check));
                iterator.remove();
            }
        }
    }

    private static boolean tracking(int index) {
        if (index == list.size()) {
            return true;
        }

        Node node = list.get(index);
        int square = (node.row / 3) * 3 + (node.column / 3);
        int flag = row_flag[node.row] | column_flag[node.column] | square_flag[square];

        for (int i = 1; i <= 9; i++) {
            if ((flag & (1 << i)) != 0) {
                continue;
            }

            row_flag[node.row] |= (1 << (i));
            column_flag[node.column] |= (1 << (i));
            square_flag[square] |= (1 << (i));
            map[node.row][node.column] = i;

            if (tracking(index + 1)) {
                return true;
            }

            row_flag[node.row] &= ~(1 << (i));
            column_flag[node.column] &= ~(1 << (i));
            square_flag[square] &= ~(1 << (i));
        }
        return false;
    }

    private static class Node {

        int row;
        int column;

        public Node(int row, int column) {
            this.row = row;
            this.column = column;
        }

    }
}
