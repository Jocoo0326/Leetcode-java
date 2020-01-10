class LongestCommonPrefix {
  public static void main(String[] args) {
    Assert.assertEquals(longestCommonPrefix(new String[] {"flower", "flow", "flight"}), "fl");
    Assert.assertEquals(longestCommonPrefix(new String[] {"dog", "racecar", "car"}), "");
    Assert.assertEquals(longestCommonPrefix(new String[] {"", ""}), "");
    Assert.assertEquals(longestCommonPrefix(new String[] {"c", "c"}), "c");
  }

  public static String longestCommonPrefix(String[] strs) {
    if (strs == null) {
      return "";
    }

    boolean b = false;
    int i;
    for (i = 0; i < strs[0].length(); i++) {
      if (b)
        break;
      final char c = strs[0].charAt(i);
      for (int j = 1; j < strs.length; j++) {
        if (i >= strs[j].length() || c != strs[j].charAt(i)) {
          b = true;
          break;
        }
      }
    }

    return strs[0].substring(0, b ? i - 1 : i);
  }
}
