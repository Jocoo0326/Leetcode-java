
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

class ThreeSum {
  public static void main(String[] args) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    List<Integer> elm = new ArrayList<>();
    elm.add(-1);
    elm.add(0);
    elm.add(1);
    res.add(elm);
    elm = new ArrayList<>();
    elm.add(-1);
    elm.add(-1);
    elm.add(2);
    res.add(elm);
    int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
    Assert.assertEquals(threeSum(nums), res);
  }

  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new LinkedList<>();
    if (nums == null || nums.length < 3) {
      return res;
    }
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 2; i++) {
      if (nums[i] > 0)
        break;
      if (i == 0 || (nums[i] != nums[i - 1])) {
        int lo = i + 1;
        int hi = nums.length - 1;
        int sum = -nums[i];
        while (lo < hi) {
          if (nums[lo] + nums[hi] == sum) {
            res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
            lo++;
            hi--;
          } else if (nums[lo] + nums[hi] > sum) {
            hi--;
          } else {
            lo++;
          }
        }
      }
    }
    return res;
  }

  public static List<List<Integer>> threeSumWithoutSort(int[] nums) {
    List<List<Integer>> res = new ArrayList<List<Integer>>();
    if (nums == null || nums.length < 3)
      return null;
    HashSet<List<Integer>> set = new HashSet<>();
    for (int i = 0; i < nums.length - 2; i++) {
      List<int[]> sub = twoSum(nums, i + 1, nums.length - 1, -nums[i]);
      for (int j = 0; j < sub.size(); j++) {
        final int[] a = sub.get(j);
        List<Integer> t = new ArrayList<>();
        if (nums[i] < a[0]) {
          t.add(nums[i]);
          t.add(a[0]);
          t.add(a[1]);
        } else if (nums[i] < a[1]) {
          t.add(a[0]);
          t.add(nums[i]);
          t.add(a[1]);
        } else {
          t.add(a[0]);
          t.add(a[1]);
          t.add(nums[i]);
        }
        if (!set.contains(t)) {
          set.add(t);
          res.add(t);
        }
      }
    }
    return res;
  }

  public static List<int[]> twoSum(int[] nums, int l, int r, int sum) {
    HashSet<Integer> set = new HashSet<>();
    int i = l;
    List<int[]> res = new ArrayList<>();
    while (i <= r) {
      int pair = sum - nums[i];
      boolean found = set.contains(pair);
      if (found) {
        res.add(pair < nums[i] ? new int[] {pair, nums[i]} : new int[] {nums[i], pair});
      } else {
        set.add(nums[i]);
      }
      i++;
    }
    return res;
  }
}
