
import java.util.LinkedList;
import java.util.Stack;
class ValidParentheses {
  public static void main(String[] args) {
    Assert.assertTrue(isValid("()"));
    Assert.assertTrue(isValid("()[]{}"));
    Assert.assertFalse(isValid("(]"));
    Assert.assertFalse(isValid("([)]"));
    Assert.assertTrue(isValid("{[]}"));
  }

  public static boolean isValid(String s) {
    // return isValidLinkedList(s);
    return isValidStack(s);
  }

  public static boolean isValidStack(String s) {
    if (s == null || s.isEmpty()) {
      return true;
    }
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == '(') {
        stack.push(')');
      } else if (c == '[') {
        stack.push(']');
      } else if (c == '{') {
        stack.push('}');
      } else if (stack.empty() || stack.pop() != c) {
        return false;
      }
    }
    return true;
  }

  public static boolean isValidLinkedList(String s) {
    if (s == null || s.isEmpty()) {
      return true;
    }
    LinkedList<Character> stack = new LinkedList<>();
    stack.add('#');
    for (char c : s.toCharArray()) {
      final char top = stack.peekLast();
      if ((c == ')' && top == '(') || (c == '}' && top == '{') || (c == ']' && top == '[')) {
        stack.removeLast();
      } else {
        stack.add(c);
      }
    }
    return stack.size() == 1 && stack.peek() == '#';
  }
}
