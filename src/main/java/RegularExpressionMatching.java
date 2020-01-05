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
    if (s == null || s == "" || p == null || p == "") {
      return true;
    }
    final char[] sc = s.toCharArray();
    final char[] pc = p.toCharArray();
    int i = 0, j = 0;
    while (i < pc.length) {
      final char c = pc[i];
      if (c >= 'a' && c <= 'z') {
        if (j < sc.length && sc[j] == c) {
          j++;
        } else {
          if (!(i + 1 < pc.length && pc[i + 1] == '*')) {
            return false;
          }
        }
      } else if (c == '.') {
        j++;
      } else if (c == '*') {
        if (i > 0) {
          final char lc = pc[i - 1];
          if (lc == '.') {
            j = sc.length;
          } else {
            int k = i + 1;
            int tailCount = 0;
            while (k < pc.length && pc[k] == lc) {
              k++;
              tailCount++;
            }
            if (k < pc.length && pc[k] == '*') {
              tailCount = Math.max(0, tailCount - 2);
            }
            int mc = 0;
            while (j < sc.length && sc[j] == lc) {
              j++;
              mc++;
            }
            if (mc + 1 < tailCount) {
              return false;
            } else {
              j -= tailCount;
            }
          }
        }
      }
      i++;
    }
    return i == pc.length && j == sc.length;
  }
}
