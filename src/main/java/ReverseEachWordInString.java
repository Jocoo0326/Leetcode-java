class ReverseEachWordInString {
  public static void main(String[] args) {
    String s1 = "the sky is blue";
    String s2 = "blue is sky the";
    Assert.assertEquals(reverse(s1), s2);
  }

  public static String reverse(String s1) {
    char[] chars = s1.toCharArray();
    for (int i = 0, j = 0; i < s1.length(); i++) {
      if (s1.charAt(i) == ' ') {
        swap(chars, j, i - 1);
        j = i + 1;
      }
      if (i == s1.length() - 1) {
        swap(chars, j, i);
      }
    }
    swap(chars, 0, s1.length() - 1);
    return new String(chars);
  }

  private static void swap(char[] cs, int start, int end) {
    for (int i = start, j = end; i < j; i++, j--) {
      char t = cs[j];
      cs[j] = cs[i];
      cs[i] = t;
    }
  }
}
