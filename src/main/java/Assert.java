
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
    HashMap<T, Integer> matches = new HashMap<>();
    for (T item : expect) {
      matches.put(item, matches.getOrDefault(item, 0) + 1);
    }
    HashMap<T, Integer> target = new HashMap<>();
    for (T item : result) {
      if (!matches.containsKey(item)) {
        assertEquals(false,
            String.format("%s contains in result but not in expect\nresult(%d): %s\nexpect(%d): %s",
                item, result.size(), result, expect.size(), expect));
      } else {
        int num = target.getOrDefault(item, 0);
        if (num == matches.get(item)) {
          assertEquals(false,
              String.format(
                  "%s contains in result but occured more times than in expect(occured %d times)\nresult(%d): %s\nexpect(%d): %s",
                  item, num, result.size(), result, expect.size(), expect));
        }
        target.put(item, num + 1);
      }
    }
    if (expect.size() != result.size()) {
      for (T item : expect) {
        if (!target.containsKey(item)) {
          assertEquals(false,
              String.format(
                  "%s contains in expect but not in result\nresult(%d): %s\nexpect(%d): %s", item,
                  result.size(), result, expect.size(), expect));
        } else {
          int num = matches.get(item);
          int numTarget = target.get(item);
          if (num != numTarget) {
            assertEquals(false,
                String.format(
                    "%s contains in result(occured %d times) but occured less times than in expect(occured %d times)\nresult(%d): %s\nexpect(%d): %s",
                    item, numTarget, num, result.size(), result, expect.size(), expect));
          }
        }
      }
    }
    // for (T item : result) {
    //   if (!expect.contains(item)) {
    //     assertEquals(false,
    //         String.format("%s contains in result but not in expect\nresult(%d): %s\nexpect(%d):
    //         %s",
    //             item, result.size(), result, expect.size(), expect));
    //   }
    // }
    // for (T item : expect) {
    //   if (!result.contains(item)) {
    //     assertEquals(false,
    //         String.format("%s contains in expect but not in result\nresult(%d): %s\nexpect(%d):
    //         %s",
    //             item, result.size(), result, expect.size(), expect));
    //   }
    // }
  }

  public static void assertEquals(int[] result, int[] expect) {
    assertEquals(Arrays.equals(result, expect),
        String.format("result: %s expect: %s", Arrays.toString(result), Arrays.toString(expect)));
  }
}
