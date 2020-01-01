class StringToInteger {
  public static void main(String[] args) {
    Assert.assertEquals(myAtoi("42"), 42);
    Assert.assertEquals(myAtoi("     -42"), -42);
    Assert.assertEquals(myAtoi("4193 with words"), 4193);
    Assert.assertEquals(myAtoi("words and 987"), 0);
    Assert.assertEquals(myAtoi("-91283472332"), -2147483648);
  }

  public static int myAtoi(String s) {
    return 0;
  }
}
