class CountAndSay {
  public static void main(String[] args) {
    Assert.assertEquals(countAndSay(1), "1");
    Assert.assertEquals(countAndSay(2), "11");
    Assert.assertEquals(countAndSay(3), "21");
    Assert.assertEquals(countAndSay(4), "1211");
    Assert.assertEquals(countAndSay(5), "111221");
  }

  public static String countAndSay(int n) {
    String base = "1";
    for (int i = 1; i < n; i++) {
      int k = 0, len = base.length();
      StringBuilder b = new StringBuilder();
      while (k < len) {
        char c = base.charAt(k);
        int count = 1;
        while (++k < len && base.charAt(k) == c) {
          count++;
        }
        b.append(count).append(c);
      }
      base = b.toString();
    }
    return base;
  }
}
