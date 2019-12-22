class Assert {
  public static void assertEquals(boolean expect, String exception) {
    if (!expect) {
      throw new Error(exception);
    }
  }

  public static void assertEquals(int result, int expect) {
    assertEquals(result == expect, String.format("result: %d expect: %d", result, expect));
  }
}
