
import java.util.ArrayDeque;
import java.util.Deque;
class LongestValidParentheses {
  public static void main(String[] args) {
    Assert.assertEquals(longestValidParentheses(")("), 0);
    Assert.assertEquals(longestValidParentheses("(()"), 2);
    Assert.assertEquals(longestValidParentheses("()(()"), 2);
    Assert.assertEquals(longestValidParentheses(")()())"), 4);
    Assert.assertEquals(longestValidParentheses("()(())"), 6);
    Assert.assertEquals(longestValidParentheses("(((()()())"), 8);
    Assert.assertEquals(longestValidParentheses("))))((()(("), 2);
  }

  public static int longestValidParentheses(String s) {
    // return longestValidParenthesesStack(s);
    // return longestValidParenthesesTwoPass(s);
    return longestValidParenthesesDP(s);
  }

  public static int longestValidParenthesesDP(String s) {
    int[] dp = new int[s.length()];
    int ans = 0;
    for (int i = 0; i < s.length(); i++) {
      if (i - 1 >= 0 && s.charAt(i) == ')') {
        if (s.charAt(i - 1) == '(') { // this condition can be removed
          dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
        } else if (s.charAt(i - 1) == ')') { // this condition concludes above condition
          if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
            dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 2 >= 0) ? dp[i - dp[i - 1] - 2] : 0);
          }
        }
        ans = Math.max(ans, dp[i]);
      }
    }
    return ans;
  }

  public static int longestValidParenthesesTwoPass(String s) {
    int open = 0, close = 0, maxLen = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        open++;
      } else {
        close++;
      }
      if (close > open) {
        open = close = 0;
      } else if (close == open) {
        maxLen = Math.max(maxLen, open * 2);
      }
    }
    open = close = 0;
    for (int j = s.length() - 1; j >= 0; j--) {
      char c = s.charAt(j);
      if (c == ')') {
        open++;
      } else {
        close++;
      }
      if (close > open) {
        open = close = 0;
      } else if (close == open) {
        maxLen = Math.max(maxLen, open * 2);
      }
    }

    return maxLen;
  }

  public static int longestValidParenthesesStack(String s) {
    if (s == null) {
      return 0;
    }
    int maxLen = 0;
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(-1);
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        stack.push(i);
      } else if (c == ')') {
        stack.pop();
        if (!stack.isEmpty()) {
          maxLen = Math.max(maxLen, i - stack.peek());
        } else {
          stack.push(i);
        }
      }
    }
    return maxLen;
  }
}
