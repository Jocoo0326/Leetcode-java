
import java.util.HashSet;
class ValidSudoku {
  public static void main(String[] args) {
    Assert.assertEquals(isValidSudoku(new char[][] {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}),
        true);

    Assert.assertEquals(isValidSudoku(new char[][] {{'8', '3', '.', '.', '7', '.', '.', '.', '.'},
                            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}}),
        false);

    Assert.assertEquals(isValidSudoku(new char[][] {{'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                            {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                            {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                            {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                            {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                            {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                            {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                            {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                            {'.', '.', '4', '.', '.', '.', '.', '.', '.'}}),
        false);
  }

  public static boolean isValidSudoku(char[][] board) {
    // return isValidSudokuIntuitive(board);
    return isValidSudokuShortAndSimple(board);
  }

  public static boolean isValidSudokuShortAndSimple(char[][] board) {
    HashSet<String> set = new HashSet<>();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        final char c = board[i][j];
        if (c != '.') {
          if (!set.add(c + "in row " + i) || !set.add(c + "in column " + j)
              || !set.add(c + "in box " + i / 3 + " " + j / 3)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public static boolean isValidSudokuIntuitive(char[][] board) {
    // row check
    for (int i = 0; i < 9; i++) {
      HashSet<Character> set = new HashSet<>();
      for (int j = 0; j < 9; j++) {
        final char c = board[i][j];
        if (c == '.') {
          continue;
        } else if (set.contains(c)) {
          return false;
        }
        set.add(c);
      }
    }

    // column check
    for (int i = 0; i < 9; i++) {
      HashSet<Character> set = new HashSet<>();
      for (int j = 0; j < 9; j++) {
        final char c = board[j][i];
        if (c == '.') {
          continue;
        } else if (set.contains(c)) {
          return false;
        }
        set.add(c);
      }
    }

    // sub-box check
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        final int iStart = i * 3, jStart = j * 3;
        HashSet<Character> set = new HashSet<>();
        for (int r = 0; r < 3; r++) {
          for (int l = 0; l < 3; l++) {
            final char c = board[iStart + r][jStart + l];
            if (c == '.') {
              continue;
            } else if (set.contains(c)) {
              return false;
            }
            set.add(c);
          }
        }
      }
    }
    return true;
  }
}
