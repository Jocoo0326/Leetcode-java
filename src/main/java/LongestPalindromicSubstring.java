class LongestPalindromicSubstring {
  public static void main(String[] args) {
    Assert.assertEquals(longestPalindrome("babad"), "bab");
    Assert.assertEquals(longestPalindrome("cbbd"), "bb");
    // System.out.println("text".substring(1, 3));
  }

  public static String longestPalindrome(String s) {}

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
