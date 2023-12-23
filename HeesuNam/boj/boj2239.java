import java.io.*;
import java.util.*;

public class Main {
	private static int[][] board = new int[9][9];
	static boolean[][] squareChk = new boolean[9][10];
	static boolean[][] rowChk = new boolean[9][10];
	static boolean[][] colChk = new boolean[9][10];
	static List<Node> fillNode = new ArrayList<>();

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				int num = tmp[j] - '0';
				if (num != 0) {
					pickNum(i, j, num, true);
					board[i][j] = num;
				} else {
					fillNode.add(new Node(i, j));
				}
			}
		}
		sudoku(0);
	} // end of main

	private static void pickNum(int r, int c, int num, boolean chk) {
		squareChk[square(r, c)][num] = chk;
		rowChk[r][num] = chk;
		colChk[c][num] = chk;

	}

	private static void sudoku(int inx) {
		if (inx == fillNode.size()) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(board[i][j]);
				}
				sb.append("\n");
			}
			System.out.println(sb.toString());
			System.exit(0);
		}
		Node cur = fillNode.get(inx);
		for (int i = 1; i <= 9; i++) {
			if (numChk(cur, i)) {
				pickNum(cur.r, cur.c, i, true);
				board[cur.r][cur.c] = i;
				sudoku(inx + 1);
				pickNum(cur.r, cur.c, i, false);
			}

		}
	}

	private static boolean numChk(Node node, int num) {
		return !squareChk[square(node.r, node.c)][num] && !rowChk[node.r][num] && !colChk[node.c][num];
	}

	private static int square(int r, int c) {
		return 3 * (r / 3) + c / 3;
	}
} // end of class
