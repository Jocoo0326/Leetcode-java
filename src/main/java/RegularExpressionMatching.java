class RegularExpressionMatching {
  public static void main(String[] args) {
    Assert.assertEquals(isMatch("aa", "a"), false);
    Assert.assertEquals(isMatch("aa", "a*"), true);
    Assert.assertEquals(isMatch("aaa", "a*a"), true);
    Assert.assertEquals(isMatch("aaa", "a*aa"), true);
    Assert.assertEquals(isMatch("aaa", "a*aaa"), true);
    Assert.assertEquals(isMatch("aaa", "ab*a*c*a"), true);
    Assert.assertEquals(isMatch("ab", ".*"), true);
    Assert.assertEquals(isMatch("aab", "c*a*b"), true);
    Assert.assertEquals(isMatch("mississippi", "mis*is*p*."), false);
    Assert.assertEquals(isMatch("mississippi", "asdasd.*"), false);
    Assert.assertEquals(isMatch("mississippi", ".*asdasd"), false);
  }

  public static boolean isMatch(String s, String p) {
    // recursive
    // memo = new Result[s.length() + 1][p.length() + 1];
    // return isMatchRecursive(s, p, s.length(), p.length());
    return isMatchDp(s, p);
  }

  public static boolean isMatchDp(String s, String p) {
    boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
    for (int i = 0; i < s.length() + 1; i++) {
      dp[i][0] = i == 0;
    }
    for (int i = 0; i < s.length() + 1; i++) {
      for (int j = 1; j < p.length() + 1; j++) {
        if (i == 0) {
          boolean b = true;
          if (j % 2 == 1) {
            b = false;
          } else {
            for (int k = 1; k < j; k = k + 2) {
              if (p.charAt(k) != '*') {
                b = false;
                break;
              }
            }
          }
          dp[i][j] = b;
          continue;
        }
        // i >= 1 && j >= 1
        final char lsc = s.charAt(i - 1);
        final char lpc = p.charAt(j - 1);
        if (lsc == lpc || lpc == '.') {
          dp[i][j] = dp[i - 1][j - 1];
        } else if (lpc == '*') {
          char llpc;
          dp[i][j] =
              dp[i][j - 2] || (((llpc = p.charAt(j - 2)) == lsc || llpc == '.') && dp[i - 1][j]);
        } else {
          dp[i][j] = false;
        }
      }
    }
    return dp[s.length()][p.length()];
  }

  enum Result { TRUE, FALSE }

  private static Result memo[][];

  public static boolean memorize(boolean result, int m, int n) {
    memo[m][n] = result ? Result.TRUE : Result.FALSE;
    return result;
  }

  public static boolean isMatchRecursive(String s, String p, int m, int n) {
    System.out.println(s + " " + p + " m" + m + " n" + n);
    if (memo[m][n] != null) {
      // System.out.println("-------hited------");
      return memo[m][n] == Result.TRUE;
    }
    if (n == 0) {
      return memorize(m == 0, m, n);
    }
    if (m == 0) {
      if (n % 2 == 1) {
        return memorize(false, m, n);
      }
      for (int i = 1; i < n; i = i + 2) {
        if (p.charAt(i) != '*') {
          return memorize(false, m, n);
        }
      }
      return memorize(true, m, n);
    }

    final char lpc = p.charAt(n - 1);
    final char lsc = s.charAt(m - 1);
    // System.out.println(s + " " + p + " " + lsc + " " + lpc + " m" + m + " n" + n);

    if (lpc == lsc || lpc == '.') {
      return memorize(isMatchRecursive(s, p, m - 1, n - 1), m, n);
    } else if (lpc == '*') {
      final char llpc = p.charAt(n - 2);
      return memorize(isMatchRecursive(s, p, m, n - 2)
              || ((lsc == llpc || llpc == '.') && isMatchRecursive(s, p, m - 1, n)),
          m, n);
    } else {
      return memorize(false, m, n);
    }
  }
}
