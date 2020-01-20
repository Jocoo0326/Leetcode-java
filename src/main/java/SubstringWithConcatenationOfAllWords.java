
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
class SubstringWithConcatenationOfAllWords {
  public static void main(String[] args) {
    Assert.assertEquals(
        findSubstring("barfoothefoobarman", new String[] {"foo", "bar"}), Arrays.asList(0, 9));
    Assert.assertEquals(
        findSubstring("wordgoodgoodgoodbestword", new String[] {"word", "good", "best", "word"}),
        Arrays.asList());
    Assert.assertEquals(
        findSubstring("wordgoodgoodgoodbestword", new String[] {"word", "good", "best", "good"}),
        Arrays.asList(8));
    Assert.assertEquals(findSubstring("a", new String[] {}), Arrays.asList());
    Assert.assertEquals(findSubstring("a", new String[] {"a"}), Arrays.asList(0));
  }

  public static List<Integer> findSubstring(String s, String[] words) {
    LinkedList<Integer> res = new LinkedList<>();
    if (s == null || words == null || s.isEmpty() || words.length == 0) {
      return res;
    }
    final int len = s.length();
    final int wordLen = words[0].length();
    final int numWords = words.length;
    HashMap<String, Integer> matches = new HashMap<>();
    for (String item : words) {
      matches.put(item, matches.getOrDefault(item, 0) + 1);
    }
    for (int i = 0; i < len - numWords * wordLen + 1; i++) {
      int num = 0;
      HashMap<String, Integer> target = new HashMap<>();
      while (num < numWords) {
        String child = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
        if (!matches.containsKey(child)) {
          break;
        } else {
          if (matches.get(child) == target.getOrDefault(child, 0)) {
            break;
          }
          target.put(child, target.getOrDefault(child, 0) + 1);
        }
        num++;
      }
      if (num == numWords) {
        res.add(i);
      }
    }

    return res;
  }
}
