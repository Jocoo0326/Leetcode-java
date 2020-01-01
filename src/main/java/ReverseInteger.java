class ReverseInteger {
  public static void main(String[] args) {
    Assert.assertEquals(reverse(123), 321);
    Assert.assertEquals(reverse(-123), -321);
    Assert.assertEquals(reverse(120), 21);
    Assert.assertEquals(reverse(1534236469), 0);
  }

  public static int reverse(int x) {
    int t = 0;
    while (x != 0) {
      int d = x % 10;
      x = x / 10;
      if (t > Integer.MAX_VALUE / 10 || (t == Integer.MAX_VALUE / 10 && d > 7)) {
        return 0;
      }
      if (t < Integer.MIN_VALUE / 10 || (t == Integer.MIN_VALUE / 10 && d < -8)) {
        return 0;
      }
      t = t * 10 + d;
    }
    return t;
  }

  public static int areverse(int x) {
    int minus = x < 0 ? -1 : 1;
    int s = x * minus;
    int t = 0;
    while (s > 0) {
      if (t > Integer.MAX_VALUE / 10) {
        return 0;
      }
      int d = s % 10;
      t = t * 10 + d;
      if (t < 0) {
        return 0;
      }
      s = s / 10;
    }
    return minus * t;
  }
}
