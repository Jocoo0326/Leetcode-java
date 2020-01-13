
import java.util.Collections;
import java.util.List;
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

  public static void assertTrue(boolean exp) {
    assertEquals(exp, true);
  }

  public static void assertFalse(boolean exp) {
    assertEquals(exp, false);
  }

  public static void assertEquals(boolean result, boolean expect) {
    assertEquals(expect == result, String.format("result: %s expect: %s", result, expect));
  }

  public static void assertEquals(double result, double expect) {
    assertEquals(Double.compare(expect, result) == 0,
        String.format("result: %s expect: %s", result, expect));
  }

  public static <T> void assertEquals(List<T> result, List<T> expect) {
    if (result == null || expect == null) {
      if (expect == result) {
        return;
      } else if (result == null) {
        assertEquals(false, String.format("result: %s expect: %s", result, expect));
      }
    }
    boolean b =
        expect.size() == result.size() && expect.containsAll(result) && result.containsAll(expect);
    assertEquals(b, String.format("result: %s expect: %s", result, expect));
  }
}
