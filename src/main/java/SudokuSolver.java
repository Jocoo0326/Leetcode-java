
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
class SudokuSolver {
  public static void main(String[] args) {
    assertSudoku(new char[][] {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                     {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                     {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                     {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                     {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                     {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                     {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                     {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                     {'.', '.', '.', '.', '8', '.', '.', '7', '9'}},
        new char[][] {{'5', '3', '4', '6', '7', '8', '9', '1', '2'},
            {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
            {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
            {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
            {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
            {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
            {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
            {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
            {'3', '4', '5', '2', '8', '6', '1', '7', '9'}});

    assertSudoku(new char[][] {{'.', '.', '9', '7', '4', '8', '.', '.', '.'},
                     {'7', '.', '.', '.', '.', '.', '.', '.', '.'},
                     {'.', '2', '.', '1', '.', '9', '.', '.', '.'},
                     {'.', '.', '7', '.', '.', '.', '2', '4', '.'},
                     {'.', '6', '4', '.', '1', '.', '5', '9', '.'},
                     {'.', '9', '8', '.', '.', '.', '3', '.', '.'},
                     {'.', '.', '.', '8', '.', '3', '.', '2', '.'},
                     {'.', '.', '.', '.', '.', '.', '.', '.', '6'},
                     {'.', '.', '.', '2', '7', '5', '9', '.', '.'}},
        new char[][] {{'5', '1', '9', '7', '4', '8', '6', '3', '2'},
            {'7', '8', '3', '6', '5', '2', '4', '1', '9'},
            {'4', '2', '6', '1', '3', '9', '8', '7', '5'},
            {'3', '5', '7', '9', '8', '6', '2', '4', '1'},
            {'2', '6', '4', '3', '1', '7', '5', '9', '8'},
            {'1', '9', '8', '5', '2', '4', '3', '6', '7'},
            {'9', '7', '5', '8', '6', '3', '1', '2', '4'},
            {'8', '3', '2', '4', '9', '1', '7', '5', '6'},
            {'6', '4', '1', '2', '7', '5', '9', '8', '3'}});
  }

  private static void assertSudoku(char[][] board, char[][] expect) {
    solveSudoku(board);
    Assert.assertEquals(board, expect);
  }

  public static void solveSudoku(char[][] board) {
    if (board == null || board.length == 0) {
      return;
    }
    // solve(board);
    solveReactiveNetwork(board);
  }

  private static Cell[][] cells = new Cell[9][9];
  private static boolean solveReactiveNetwork(char[][] board) {
    // Cell[][] cells = new Cell[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        cells[i][j] = new Cell();
      }
    }
    // build permission board
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] != '.' && !set(i, j, board[i][j])) {
          return false; // unsolvable
        }
      }
    }

    if (!solveBacktrack()) {
      return false;
    }

    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        board[i][j] = cells[i][j].value;
      }
    }
    return true;
  }

  private static boolean solveBacktrack() {
    final Comparator<Cell> c = new Comparator<Cell>() {
      @Override
      public int compare(Cell o1, Cell o2) {
        return o1.numPermit - o2.numPermit;
      }
    };
    PriorityQueue<Cell> pq = new PriorityQueue<>(c);
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (cells[i][j].value == '.') {
          cells[i][j].x = i;
          cells[i][j].y = j;
          pq.add(cells[i][j]);
        }
      }
    }

    return backtrack(pq);
  }

  private static boolean backtrack(PriorityQueue<Cell> pq) {
    if (pq.isEmpty()) {
      return true;
    }

    Cell cell = pq.poll();
    if (cell.value != '.') {
      return backtrack(pq);
    }
    Cell[][] snapshots = makeCopy(cells);
    for (int i = 0; i < 9; i++) {
      if ((cell.permission & (1 << i)) != 0) {
        if (set(cell.x, cell.y, (char) (i + '1')) && backtrack(pq)) {
          return true;
        }
        restoreCellsArray(snapshots);
      }
    }

    return false;
  }

  private static Cell[][] makeCopy(Cell[][] cells) {
    Cell[][] dest = new Cell[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        dest[i][j] = cells[i][j].copy();
      }
    }
    return dest;
  }

  private static void restoreCellsArray(Cell[][] backup) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        cells[i][j].restore(backup[i][j]);
      }
    }
  }

  private static boolean set(int i, int j, char value) {
    if (cells[i][j].value == value) {
      return true;
    }
    if (!cells[i][j].isPermit(value)) {
      System.out.println("set no permit");

      return false;
    }
    cells[i][j].value = value;
    cells[i][j].numPermit = 1;
    cells[i][j].clear();
    cells[i][j].setPermission(value);

    for (int k = 0; k < 9; k++) {
      if (k != i && !updatePermission(k, j, value)) {
        return false;
      }
      if (k != j && !updatePermission(i, k, value)) {
        return false;
      }
      int ix = (i / 3) * 3 + (k / 3);
      int jx = (j / 3) * 3 + (k % 3);
      if (ix != i && jx != j && !updatePermission(ix, jx, value)) {
        return false;
      }
    }
    return true;
  }

  private static boolean updatePermission(int i, int j, char value) {
    if (!cells[i][j].isPermit(value)) {
      return true;
    }
    if (cells[i][j].value == value) {
      return false;
    }
    cells[i][j].removePermission(value);
    if (--cells[i][j].numPermit == 1) {
      return set(i, j, cells[i][j].firstPermission());
    }
    return true;
  }

  private static String printCells() {
    if (cells == null) {
      return "null";
    }
    int iMax = cells.length - 1;
    if (iMax == -1) {
      return "[]";
    }
    StringBuilder b = new StringBuilder();
    b.append("[\n");
    for (int i = 0;; i++) {
      b.append(Arrays.toString(cells[i]));
      if (i == iMax) {
        return b.append("\n]").toString();
      }
      b.append('\n');
    }
  }

  private static class Cell {
    int permission;
    int numPermit;
    char value;
    int x;
    int y;
    Cell() {
      value = '.';
      numPermit = 9;
      permission = 0x1FF;
    }

    boolean isPermit(char v) {
      return (permission & (1 << (v - '1'))) != 0;
    }

    void setPermission(char v) {
      permission = permission | (1 << (v - '1'));
    }

    void removePermission(char v) {
      permission = permission & ~(1 << (v - '1'));
    }

    void clear() {
      permission = 0;
    }

    char firstPermission() {
      for (int i = 0; i < 9; i++) {
        if ((permission & (1 << i)) != 0) {
          return (char) (i + '1');
        }
      }
      return '0';
    }

    void restore(Cell back) {
      this.permission = back.permission;
      this.numPermit = back.numPermit;
      this.value = back.value;
      this.x = back.x;
      this.y = back.y;
    }

    Cell copy() {
      Cell copy = new Cell();
      copy.permission = permission;
      copy.numPermit = numPermit;
      copy.value = value;
      copy.x = x;
      copy.y = y;
      return copy;
    }

    @Override
    public String toString() {
      return "" + value;
    }
  }

  private static boolean solve(char[][] board) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (board[i][j] == '.') {
          for (char c = '1'; c <= '9'; c++) {
            if (isValidSudoku(board, i, j, c)) {
              board[i][j] = c;
              if (solve(board)) {
                return true;
              } else {
                board[i][j] = '.';
              }
            }
          }
          return false;
        }
      }
    }
    return true;
  }

  private static boolean isValidSudoku(char[][] board, int row, int col, char c) {
    for (int i = 0; i < 9; i++) {
      if (board[row][i] == c || board[i][col] == c
          || board[(row / 3) * 3 + i / 3][(col / 3) * 3 + i % 3] == c) {
        return false;
      }
    }
    return true;
  }
}
