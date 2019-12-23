class Assert {
  public static void assertEquals(boolean expect, String exception) {
    if (!expect) {
      throw new Error(exception);
    }
  }

  public static void assertEquals(int result, int expect) {
    assertEquals(result == expect, String.format("result: %d expect: %d", result, expect));
  }

  public static void assertEquals(String result, String expect) {
    assertEquals(expect.equals(result), String.format("result: %s expect: %s", result, expect));
  }

  public static void assertEquals(boolean result, boolean expect) {
    assertEquals(expect == result, String.format("result: %s expect: %s", result, expect));
  }

  public static void assertEquals(double result, double expect) {
    assertEquals(Double.compare(expect, result) == 0,
        String.format("result: %s expect: %s", result, expect));
  }
}
