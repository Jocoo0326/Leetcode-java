class WildcardMatching {
  public static void main(String[] args) {
    Assert.assertEquals(isMatch("aa", "a"), false);
    Assert.assertEquals(isMatch("aaa", "aa"), false);
    Assert.assertEquals(isMatch("aa", "*"), true);
    Assert.assertEquals(isMatch("", "*"), true);
    Assert.assertEquals(isMatch("", "?"), false);
    Assert.assertEquals(isMatch("cb", "?a"), false);
    Assert.assertEquals(isMatch("adceb", "*a*b"), true);
    Assert.assertEquals(isMatch("acdcb", "a*c?b"), false);
    Assert.assertEquals(isMatch("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "*aaaaaaab"), false);
  }

  public static boolean isMatch(String s, String p) {
    int i = 0, j = 0, match = 0, start = -1;
    while (i < s.length()) {
      if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
        i++;
        j++;
      } else if (j < p.length() && p.charAt(j) == '*') {
        start = j;
        match = i;
        j++;
      } else if (start != -1) {
        j = start + 1;
        i = ++match;
      } else {
        return false;
      }
    }
    while (j < p.length() && p.charAt(j) == '*') {
      j++;
    }
    return j == p.length();
  }

  public static boolean isMatchCommonWay(String s, String p) {
    if (p == null || p.equals("")) {
      return (s == null || s.equals(""));
    }
    Result[][] dp = new Result[s.length() + 1][p.length() + 1];
    isMatchDP(s, p, 0, 0, dp);
    return dp[0][0] == Result.TRUE;
  }

  public static boolean isMatchDP(String s, String p, int m, int n, Result[][] dp) {
    // System.out.println("m " + m + " n " + n);

    if (dp[m][n] != null) {
      return dp[m][n] == Result.TRUE;
    }
    boolean b = false;
    final char sc = m < s.length() ? s.charAt(m) : '#';
    final char pc = n < p.length() ? p.charAt(n) : '#';
    if (sc == pc && sc == '#') {
      b = true;
    } else if (sc == pc || (sc != '#' && pc == '?')) {
      b = isMatchDP(s, p, m + 1, n + 1, dp);
    } else if (pc == '*') {
      b = (m < s.length() && isMatchDP(s, p, m + 1, n, dp)) || isMatchDP(s, p, m, n + 1, dp);
    }
    dp[m][n] = b ? Result.TRUE : Result.FALSE;
    return b;
  }

  enum Result { TRUE, FALSE }
}
