class PalindromeNumber {
  public static void main(String[] args) {
    Assert.assertEquals(isPalindrome(121), true);
    Assert.assertEquals(isPalindrome(-121), false);
    Assert.assertEquals(isPalindrome(10), false);
  }

  public static boolean isPalindrome(int x) {
    if (x < 0) {
      return false;
    }
    final int MAX_INT = Integer.MAX_VALUE;
    int t = 0;
    final int original = x;
    while (x != 0) {
      int d = x % 10;
      x = x / 10;
      if (t > MAX_INT || (t == MAX_INT && d > 7)) {
        return false;
      }
      t = t * 10 + d;
    }
    return t == original;
  }
}
