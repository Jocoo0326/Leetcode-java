
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
class FourSum {
  public static void main(String[] args) {
    Assert.assertEquals(fourSum(new int[] {1, 0, -1, 0, -2, 2}, 0),
        Arrays.asList(
            Arrays.asList(-1, 0, 0, 1), Arrays.asList(-2, -1, 1, 2), Arrays.asList(-2, 0, 0, 2)));
    Assert.assertEquals(fourSum(new int[] {-1, -1, -1, -1, 1, 1, 1, 1}, 0),
        Arrays.asList(Arrays.asList(-1, -1, 1, 1)));
    Assert.assertEquals(fourSum(new int[] {-1, 0, -5, -2, -2, -4, 0, 1, -2}, -9),
        Arrays.asList(Arrays.asList(-5, -4, -1, 1), Arrays.asList(-5, -4, 0, 0),
            Arrays.asList(-5, -2, -2, 0), Arrays.asList(-4, -2, -2, -1)));
  }

  public static List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> res = new LinkedList<>();
    if (nums == null || nums.length < 4) {
      return res;
    }
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 3; i++) {
      if (i > 0 && nums[i] == nums[i - 1])
        continue;
      for (int j = i + 1; j < nums.length - 2; j++) {
        if (j > i + 1 && nums[j] == nums[j - 1])
          continue;
        int lo = j + 1, hi = nums.length - 1, rest = target - nums[i] - nums[j];
        while (lo < hi) {
          int s = nums[lo] + nums[hi];
          if (s == rest) {
            res.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]));
            while (++lo < hi && nums[lo] == nums[lo - 1]) {
            }
            while (lo < --hi && nums[hi] == nums[hi + 1]) {
            }
          } else if (s < rest) {
            lo++;
          } else {
            hi--;
          }
        }
      }
    }
    return res;
  }
}
