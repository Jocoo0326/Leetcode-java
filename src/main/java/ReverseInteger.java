
import java.util.ArrayDeque;
import java.util.Queue;

class ReverseInteger {
  public static void main(String[] args) {
    Assert.assertEquals(reverse(123), 321);
    Assert.assertEquals(reverse(-123), -321);
    Assert.assertEquals(reverse(120), 21);
    Assert.assertEquals(reverse(1534236469), 0);
  }

  public static int reverse(int x) {
    int minus = x < 0 ? -1 : 1;
    int s = x * minus;
    Queue<Integer> d = new ArrayDeque<>();
    int t = s;
    while (t > 0) {
      d.add(t % 10);
      t = t / 10;
    }
    t = 0;
    while (!d.isEmpty()) {
      t = t * 10 + d.poll();
      if (t < 0) {
        return 0;
      }
    }

    return minus * t;
  }
}
