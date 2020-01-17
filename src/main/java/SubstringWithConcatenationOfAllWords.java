
import java.util.Arrays;
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
    if (s == null || s.isEmpty() || words.length == 0) {
      return Arrays.asList();
    }
    final int len = s.length();
    final int wordLen = words[0].length();
    final List<String> matches = new LinkedList<>();
    LinkedList<Integer> res = new LinkedList<>();
    int[] indexes = new int[len];
    for (int i = 0; i < words.length; i++) {
      int lastIndex = -1;
      matches.add(words[i]);
      while (lastIndex + wordLen < len) {
        int index = -1;
        if ((index = s.indexOf(words[i], ++lastIndex)) != -1) {
          lastIndex = index;
          indexes[index] = i + 1;
        }
      }
    }
    matches.sort(null);
    System.out.println(Arrays.toString(indexes));

    for (int j = 0; j < indexes.length; j++) {
      if (indexes[j] == 0) {
        continue;
      }
      LinkedList<String> set = new LinkedList<>();
      for (int i = j; i < len; i += wordLen) {
        if (indexes[i] == 0) {
          break;
        } else {
          set.add(words[indexes[i] - 1]);
          if (set.size() == words.length) {
            set.sort(null);
            if (matches.equals(set)) {
              res.add(j);
            }
            break;
          }
        }
      }
    }

    return res;
  }
}
