import java.util.HashMap;

class LongestSubstringWithoutRepeatingCharacters {
  public static void main(String[] args) {
    Assert.assertEquals(lengthOfLongestSubstring(null), 0);
    Assert.assertEquals(lengthOfLongestSubstring(""), 0);
    Assert.assertEquals(lengthOfLongestSubstring(" "), 1);
    Assert.assertEquals(lengthOfLongestSubstring("abcabcbb"), 3);
    Assert.assertEquals(lengthOfLongestSubstring("bbbbb"), 1);
    Assert.assertEquals(lengthOfLongestSubstring("pwwkew"), 3);
    Assert.assertEquals(lengthOfLongestSubstring("pasdf"), 5);
  }

  public static int alengthOfLongestSubstring(String s) {
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

  public static int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    if (s.length() == 1) {
      return 1;
    }
    int l = 0, len = 0;
    HashMap<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      if (map.containsKey(s.charAt(i))) {
        l = Math.max(map.get(s.charAt(i)), l);
      }
      len = Math.max(i + 1 - l, len);
      map.put(s.charAt(i), i + 1);
    }
    return len;
  }
}
