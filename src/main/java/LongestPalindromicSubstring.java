class LongestPalindromicSubstring {
  public static void main(String[] args) {
    Assert.assertEquals(longestPalindrome("babad"), "bab");
    Assert.assertEquals(longestPalindrome("cbbd"), "bb");
  }

  private static char[] addSeparators(String s) {
    char[] nc = new char[s.length() * 2 + 1];
    for (int i = 0; i < nc.length - 1; i = i + 2) {
      nc[i] = '#';
      nc[i + 1] = s.charAt(i / 2);
    }
    nc[nc.length - 1] = '#';
    return nc;
  }

  private static String removeSeparators(char[] ca, int c, int r) {
    StringBuilder sb = new StringBuilder();
    for (int i = c - r; i < c + r + 1; i++) {
      if (ca[i] != '#') {
        sb.append(ca[i]);
      }
    }
    return sb.toString();
  }

  public static String longestPalindrome(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }
    char[] ns = addSeparators(s);

    int[] rd = new int[s.length() * 2 + 1];
    int c = 0, r = 0, l = 0, h = 0;
    for (int i = 1; i < ns.length - 1; i++) {
      if (i > r) {
        rd[i] = 0;
        l = i - 1;
        h = i + 1;
      } else {
        int iMirror = 2 * c - i;
        if (iMirror >= 0) {
          if (rd[iMirror] < r - i) { // == might expand outer right r
            rd[i] = rd[iMirror];
            l = -1;
          } else {
            rd[i] = r - i;
            l = i - (r - i) - 1;
            h = i + (r - i) + 1;
          }
        }
      }
      while (l >= 0 && h < ns.length && ns[l] == ns[h]) {
        rd[i]++;
        l--;
        h++;
      }

      if (i + rd[i] > r) { // replace last palindrome
        c = i;
        r = c + rd[i];
      }
    }
    int len = -1, idx = -1;
    for (int i = 0; i < rd.length; i++) {
      if (rd[i] > len) {
        len = rd[i];
        idx = i;
      }
    }

    return removeSeparators(ns, idx, len);
  }

  public static String blongestPalindrome(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }
    int len = 0;
    String result = "";
    for (int i = 0; i < s.length() * 2 - 1; i++) {
      int l, h;
      l = i >> 1;
      if (i % 2 == 1) {
        h = l + 1;
      } else {
        h = l;
      }
      while (l >= 0 && h < s.length()) {
        if (l == h || s.charAt(l) == s.charAt(h)) {
          int newLen = h - l + 1;
          if (newLen > len) {
            len = newLen;
            result = s.substring(l, h + 1);
          }
        } else {
          break;
        }
        l--;
        h++;
      }
    }
    if (len == 0) {
      return s.substring(0, 1);
    }
    return result;
  }

  public static String alongestPalindrome(String s) {
    if (s == null || s.length() < 2) {
      return s;
    }
    int len = 0;
    String result = "";
    for (int i = 0; i < s.length() - 1; i++) {
      for (int j = i + 1; j < s.length(); j++) {
        int l = i, h = j;
        while (l < h) {
          if (s.charAt(l) == s.charAt(h)) {
            l++;
            h--;
          } else {
            break;
          }
        }
        if (l >= h) {
          int newLen = 0;
          if ((newLen = j - i + 1) > len) {
            len = newLen;
            result = s.substring(i, j + 1);
          }
        }
      }
    }
    if (len == 0) {
      return s.substring(0, 1);
    }
    return result;
  }
}
