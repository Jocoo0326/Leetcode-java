class LongestSubstringWithoutRepeatingCharacters {
  public static void main(String[] args) {
    // Assert.assertEquals(lengthOfLongestSubstring(null), 0);
    // Assert.assertEquals(lengthOfLongestSubstring(""), 0);
    // Assert.assertEquals(lengthOfLongestSubstring(" "), 1);
    Assert.assertEquals(lengthOfLongestSubstring("abcabcbb"), 3);
    Assert.assertEquals(lengthOfLongestSubstring("bbbbb"), 1);
    Assert.assertEquals(lengthOfLongestSubstring("pwwkew"), 3);
    Assert.assertEquals(lengthOfLongestSubstring("pasdf"), 5);
  }

  public static int lengthOfLongestSubstring(String s) {
    int n = s.length(), ans = 0;
    int[] index = new int[128]; // current index of character
    // try to extend the range [i, j]
    for (int j = 0, i = 0; j < n; j++) {
      i = Math.max(index[s.charAt(j)], i);
      ans = Math.max(ans, j - i + 1);
      index[s.charAt(j)] = j + 1;
    }
    return ans;
  }

  public static int alengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    if (s.length() == 1) {
      return 1;
    }
    int l = 0, len = 1;
    for (int i = 1; i < s.length(); i++) {
      int curLen = i - l;
      int idx = s.indexOf((int) s.charAt(i), l);
      if (idx > -1 && idx < i) {
        l = idx + 1;
      } else {
        curLen++;
      }
      if (curLen > len) {
        len = curLen;
      }
    }
    return len;
  }
}
