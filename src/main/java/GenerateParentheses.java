
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

class GenerateParentheses {
  public static void main(String[] args) {
    Assert.assertEquals(
        generateParenthesis(3), Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()"));
    Assert.assertEquals(generateParenthesis(4),
        Arrays.asList("(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())",
            "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())",
            "()()()()"));
    Assert.assertEquals(generateParenthesis(5),
        Arrays.asList("((((()))))", "(((()())))", "(((())()))", "(((()))())", "(((())))()",
            "((()(())))", "((()()()))", "((()())())", "((()()))()", "((())(()))", "((())()())",
            "((())())()", "((()))(())", "((()))()()", "(()((())))", "(()(()()))", "(()(())())",
            "(()(()))()", "(()()(()))", "(()()()())", "(()()())()", "(()())(())", "(()())()()",
            "(())((()))", "(())(()())", "(())(())()", "(())()(())", "(())()()()", "()(((())))",
            "()((()()))", "()((())())", "()((()))()", "()(()(()))", "()(()()())", "()(()())()",
            "()(())(())", "()(())()()", "()()((()))", "()()(()())", "()()(())()", "()()()(())",
            "()()()()()"));
  }

  public static List<String> generateParenthesis(int n) {
    return generateParenthesisDemo(n);
  }

  public static List<String> generateParenthesisDemo(int n) {
    // C(2n,n)/(n+1)
    // 1 nil
    // 1 ()
    // 2 (()) ()()
    // 5 ()(()) ((())) (())() ()()() (()())
    // 14
    // 42
    // A(B)
    HashMap<Integer, List<String>> mapping = new HashMap<>();
    LinkedList<String> queue = new LinkedList<>();
    queue.add("");
    mapping.put(0, queue);

    for (int i = 1; i <= n; i++) {
      int lo = 0, hi = i - 1;
      LinkedList<String> list = new LinkedList<>();
      while (lo < i) {
        for (String loString : mapping.get(lo)) {
          for (String hiString : mapping.get(hi)) {
            list.add(loString + "(" + hiString + ")");
          }
        }
        lo++;
        hi--;
      }
      mapping.put(i, list);
    }
    return mapping.get(n);
  }
}
