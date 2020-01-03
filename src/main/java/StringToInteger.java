class StringToInteger {
  public static void main(String[] args) {
    Assert.assertEquals(myAtoi("42"), 42);
    Assert.assertEquals(myAtoi("     -42"), -42);
    Assert.assertEquals(myAtoi("4193 with words"), 4193);
    Assert.assertEquals(myAtoi("words and 987"), 0);
    Assert.assertEquals(myAtoi("-91283472332"), -2147483648);
    Assert.assertEquals(myAtoi("91283472332"), 2147483647);
  }

  public static int myAtoi(String s) {
    if (s == null || s == "") {
      return 0;
    }
    int val = 0;
    boolean minus = false;
    boolean signParsed = false;
    final int INT_MAX = Integer.MAX_VALUE;
    final int INT_MIN = Integer.MIN_VALUE;
    final char[] cs = s.toCharArray();
    for (char c : cs) {
      if (!signParsed) {
        if (c == ' ') {
          continue;
        } else if (c == '-' || c == '+') {
          signParsed = true;
          if (c == '-') {
            minus = true;
          }
          continue;
        }
      }
      if (c >= '0' && c <= '9') {
        if (!signParsed) {
          signParsed = true;
        }
        int num = c - '0';

        if (val > INT_MAX / 10 || (val == INT_MAX / 10 && minus == false && num > 7)) {
          return INT_MAX;
        }
        if (val < INT_MIN / 10 || (val == INT_MIN / 10 && minus == true && num > 8)) {
          return INT_MIN;
        }
        val = val * 10 + (minus ? -num : num);
      } else {
        return val;
      }
    }

    return val;
  }
}
