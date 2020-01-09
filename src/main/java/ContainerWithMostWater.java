
import java.util.ArrayList;
import java.util.List;
class ContainerWithMostWater {
  public static void main(String[] args) {
    Assert.assertEquals(maxArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}), 49);
  }

  public static int maxArea(int[] height) {
    // return maxAreaBruteForce(height);
    // return maxAreaOpt(height);
    return maxAreaSinglePass(height);
  }

  public static int maxAreaSinglePass(int[] height) {
    int l = 0, r = height.length - 1, max = 0;
    while (l < r) {
      max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
      if (height[l] > height[r]) {
        r--;
      } else {
        l++;
      }
    }
    return max;
  }

  public static int maxAreaOpt(int[] height) {
    List<Integer> cand = new ArrayList<Integer>();
    int max = 0;
    final int len = height.length;
    for (int i = len - 1; i >= 0; i--) {
      if ((i < len - 1 && height[i] > height[len - 1]) || i == len - 1) {
        cand.add(i);
      }
    }
    for (int i = 0; i < len - 1; i++) {
      for (int j = 0; j < cand.size(); j++) {
        final int idx = cand.get(j);
        if (idx <= i) {
          break;
        }
        max = Math.max(max, Math.min(height[i], height[idx]) * (idx - i));
      }
    }
    return max;
  }

  public static int maxAreaBruteForce(int[] height) {
    final int len = height.length;
    int max = 0;
    for (int i = 0; i < len - 1; i++) {
      int hi = len - 1;
      for (int j = len - 1; j > i; j--) {
        if (height[j] <= height[hi] && j < hi) {
          continue;
        }
        int t = Math.min(height[i], height[j]) * (j - i);
        if (t > max) {
          max = t;
          hi = j;
        }
      }
    }
    return max;
  }
}
