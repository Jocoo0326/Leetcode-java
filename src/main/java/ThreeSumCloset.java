
import java.util.Arrays;
class ThreeSumCloset {
  public static void main(String[] args) {
    Assert.assertEquals(threeSumCloset(new int[] {-1, 2, 1, -4}, 1), 2);
    Assert.assertEquals(threeSumCloset(new int[] {-3, -2, -5, 3, -4}, -1), -2);
  }

  // -4 -1 1 2
  // -5 -4 -3 -2 3
  public static int threeSumCloset(int[] nums, int target) {
    Arrays.sort(nums);
    int sum = nums[0] + nums[1] + nums[nums.length - 1];
    for (int i = 0; i < nums.length - 2; i++) {
      int lo = i + 1;
      int hi = nums.length - 1;
      int val = target - nums[i];
      while (lo < hi) {
        int t = nums[lo] + nums[hi];

        if (t == val) {
          return target;
        } else if (t < val) {
          lo++;
        } else {
          hi--;
        }
        if (Math.abs(val - t) < Math.abs(target - sum)) {
          sum = t + nums[i];
        }
      }
    }
    return sum;
  }
}
