
import java.util.Arrays;
import java.util.LinkedList;
class TrappingRainWater {
  public static void main(String[] args) {
    Assert.assertEquals(trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}), 6);
    Assert.assertEquals(trap(new int[] {2, 1, 0, 2}), 3);
    Assert.assertEquals(trap(new int[] {4, 2, 3}), 1);
    Assert.assertEquals(trap(new int[] {6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1,
                            3, 4, 6, 8, 1, 3}),
        83);
  }

  // brilliant
  public static int trap(int[] height) {
    int water = 0;
    int left = 0, right = height.length - 1;
    int leftMax = 0, rightMax = 0;
    while (left < right) {
      if (height[left] < height[right]) {
        leftMax = Math.max(leftMax, height[left]);
        water += (leftMax - height[left]);
        left++;
      } else {
        rightMax = Math.max(rightMax, height[right]);
        water += (rightMax - height[right]);
        right--;
      }
    }
    return water;
  }

  public static int trapDP(int[] height) {
    // build leftMaxArray and rightMaxArray
    final int length = height.length;
    int[] leftMaxArray = new int[length];
    int[] rightMaxArray = new int[length];
    for (int i = 0; i < length; i++) {
      if (i > 0) {
        leftMaxArray[i] = Math.max(leftMaxArray[i - 1], height[i]);
      } else {
        leftMaxArray[i] = height[i];
      }
    }
    for (int i = length - 1; i >= 0; i--) {
      if (i < length - 1) {
        rightMaxArray[i] = Math.max(rightMaxArray[i + 1], height[i]);
      } else {
        rightMaxArray[i] = height[i];
      }
    }
    int water = 0;
    for (int i = 0; i < length; i++) {
      water += (Math.min(leftMaxArray[i], rightMaxArray[i]) - height[i]);
    }
    return water;
  }

  public static int trapStack(int[] height) {
    int water = 0;
    LinkedList<Node> stack = new LinkedList<>();
    for (int i = 0; i < height.length; i++) {
      int cur = height[i];
      if (stack.isEmpty() || stack.peekLast().value > cur) {
        stack.add(new Node(i, cur));
        continue;
      }
      Node top;
      int surface = 0;
      while ((top = stack.peekLast()) != null) {
        surface = top.value;
        int delta = (Math.min(top.value, cur) - top.base) * (i - 1 - top.index);
        // System.out.println("[" + top.index + ", " + i + "] delta " + delta);
        water += delta;
        if (top.value <= cur) {
          stack.pollLast();
        }
        if (!stack.isEmpty()) {
          stack.peekLast().base = surface;
        }
        if (top.value >= cur) {
          break;
        }
      }
      stack.add(new Node(i, cur));
    }
    return water;
  }

  private static class Node {
    int index;
    int value;
    int base = 0;
    Node(int index, int value) {
      this.index = index;
      this.value = value;
    }

    public String toString() {
      return "index " + index + " value " + value + " base " + base;
    }
  }
}
