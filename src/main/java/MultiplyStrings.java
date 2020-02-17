class MultiplyStrings {
  public static void main(String[] args) {
    Assert.assertEquals(addTwoNumbers("1", "2"), "3");
    Assert.assertEquals(addTwoNumbers("9", "9"), "18");

    Assert.assertEquals(multiply("1", "2"), "2");
    Assert.assertEquals(multiply("123", "456"), "56088");
    Assert.assertEquals(multiply("9133", "0"), "0");
  }

  // Briliant
  public static String multiply(String num1, String num2) {
    int len1 = num1.length();
    int len2 = num2.length();
    int[] accumulate = new int[len1 + len2];
    for (int i = len1 - 1; i >= 0; i--) {
      for (int j = len2 - 1; j >= 0; j--) {
        int t = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
        int p2 = i + j + 1;
        int sum = accumulate[p2] + t;
        accumulate[p2 - 1] += (sum / 10);
        accumulate[p2] = (sum % 10);
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int p : accumulate) {
      if (!(p == 0 && sb.length() == 0)) {
        sb.append(p);
      }
    }
    if (sb.length() == 0) {
      return "0";
    }
    return sb.toString();
  }

  public static String multiplyStupid(String num1, String num2) {
    String ans = "0";
    if (num1.equals("0") || num2.equals("0")) {
      return ans;
    }
    for (int i = num1.length() - 1; i >= 0; i--) {
      int d1 = num1.charAt(i) - '0';
      int carry = 0;
      StringBuilder sb = new StringBuilder();
      for (int j = num2.length() - 1; j >= 0; j--) {
        int d2 = num2.charAt(j) - '0';
        int t = d1 * d2 + carry;
        carry = t / 10;
        sb.insert(0, t % 10);
      }
      if (carry > 0) {
        sb.insert(0, carry);
      }
      if (!sb.toString().equals("0")) {
        for (int k = 0; k < (num1.length() - 1 - i); k++) {
          sb.append(0);
        }
      }
      ans = addTwoNumbers(ans, sb.toString());
    }
    return ans;
  }

  public static String addTwoNumbers(String num1, String num2) {
    int carry = 0;
    int i = 0;
    final int len1 = num1.length();
    final int len2 = num2.length();
    StringBuilder sb = new StringBuilder();
    while (i < Math.max(len1, len2) || carry > 0) {
      int d1 = i < len1 ? (num1.charAt(len1 - 1 - i) - '0') : 0;
      int d2 = i < len2 ? (num2.charAt(len2 - 1 - i) - '0') : 0;
      int t = (d1 + d2 + carry);
      carry = t / 10;
      sb.insert(0, (t % 10));
      i++;
    }
    return sb.toString();
  }
}
