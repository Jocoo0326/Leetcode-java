
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class LetterCombinationsOfAPhoneNumber {
  final static String[] maps = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

  public static void main(String[] args) {
    Assert.assertEquals(letterCombinationsOfAPhoneNumber("23"),
        Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
    Assert.assertEquals(letterCombinationsOfAPhoneNumber(""), Arrays.asList());
  }

  public static List<String> letterCombinationsOfAPhoneNumber(String digits) {
    if (digits == null || digits.isEmpty()) {
      return new ArrayList<>();
    }
    // return letterCombinationsOfAPhoneNumberRecursive(digits, 0, digits.length() - 1);
    return letterCombinationsOfAPhoneNumberBFS(digits);
  }

  public static List<String> letterCombinationsOfAPhoneNumberBFS(String digits) {
    LinkedList<String> val = new LinkedList<>();
    val.add("");
    while (val.peek().length() != digits.length()) {
      String remove = val.remove();
      for (char c : maps[digits.charAt(remove.length()) - '2'].toCharArray()) {
        val.add(remove + String.valueOf(c));
      }
    }
    return val;
  }

  public static List<String> digitToLetters(char digit) {
    List<String> res = new ArrayList<>(4);
    if (digit >= '2' && digit <= '9') {
      for (char c : maps[digit - '2'].toCharArray()) {
        res.add(String.valueOf(c));
      }
    }
    return res;
  }

  public static List<String> letterCombinationsOfAPhoneNumberRecursive(
      String digits, int l, int r) {
    List<String> val = digitToLetters(digits.charAt(l));
    if (l == r) {
      return val;
    }
    List<String> res = new ArrayList<>();
    final List<String> rest = letterCombinationsOfAPhoneNumberRecursive(digits, l + 1, r);
    for (int i = 0; i < val.size(); i++) {
      for (int j = 0; j < rest.size(); j++) {
        res.add(val.get(i) + rest.get(j));
      }
    }
    return res;
  }
}
