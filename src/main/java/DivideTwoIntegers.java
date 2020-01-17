class DivideTwoIntegers {
  public static void main(String[] args) {
    Assert.assertEquals(divide(10, 3), 3);
    Assert.assertEquals(divide(7, -3), -2);
    Assert.assertEquals(divide(-7, -3), 2);
    Assert.assertEquals(divide(-2147483648, -1), 2147483647);
    Assert.assertEquals(divide(-2147483648, -2), 1 << 30);
    Assert.assertEquals(divide(-2147483648, 2), -1073741824);
    Assert.assertEquals(divide(-1010369383, -2147483648), 0);
  }

  public static int divide(int dividend, int divisor) {
    boolean signSame = !(dividend > 0 ^ divisor > 0);
    if (dividend == Integer.MIN_VALUE) {
      if (divisor == Integer.MIN_VALUE) {
        return 1;
      } else if (divisor == -1) {
        return Integer.MAX_VALUE;
      } else if (divisor == 1) {
        return dividend;
      } else {
        return divide(dividend + Math.abs(divisor), divisor) + (signSame ? 1 : -1);
      }
    }
    if (divisor == Integer.MIN_VALUE) {
      return 0;
    }
    // !!! Math.abs(Integer.MIN_VALUE) is Integer.MIN_VALUE
    int d = Math.abs(dividend), dd = Math.abs(divisor), r = 0;
    while (d >= dd) {
      int shift = 1, m = 0;
      // dd << shift may overflows
      while ((m = dd << shift) <= d && m > 0) {
        shift++;
      }
      r += 1 << (--shift);
      d -= dd << shift;
    }
    return signSame ? r : -r;
  }
}
