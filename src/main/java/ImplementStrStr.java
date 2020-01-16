class ImplementStrStr {
  public static void main(String[] args) {
    Assert.assertEquals(strStr("hello", ""), 0);
    Assert.assertEquals(strStr("hello", "ll"), 2);
    Assert.assertEquals(strStr("aaaaa", "bba"), -1);
    Assert.assertEquals(strStr("mississippi", "a"), -1);
  }

  public static int strStr(String haystack, String needle) {
    // return haystack.indexOf(needle);
    if (needle == null || needle.length() == 0) {
      return 0;
    }
    char[] source = haystack.toCharArray();
    char[] target = needle.toCharArray();
    int sourceCount = source.length;
    int targetCount = target.length;
    for (int i = 0; i <= sourceCount - targetCount; i++) {
      int j = 0;
      while (j < targetCount && source[i + j] == target[j]) {
        j++;
      }
      if (j == targetCount) {
        return i;
      }
    }
    return -1;
  }
}
