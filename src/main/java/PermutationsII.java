
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
class PermutationsII {
  public static void main(String[] args) {
    Assert.assertEquals(permuteUnique(new int[] {1, 1, 2}),
        Arrays.asList(Arrays.asList(1, 1, 2), Arrays.asList(1, 2, 1), Arrays.asList(2, 1, 1)));
    Assert.assertEquals(permuteUnique(new int[] {1, 1, 2, 2}),
        Arrays.asList(Arrays.asList(1, 1, 2, 2), Arrays.asList(1, 2, 1, 2),
            Arrays.asList(1, 2, 2, 1), Arrays.asList(2, 1, 1, 2), Arrays.asList(2, 1, 2, 1),
            Arrays.asList(2, 2, 1, 1)));
    Assert.assertEquals(permuteUnique(new int[] {3, 3, 0, 3}),
        Arrays.asList(Arrays.asList(0, 3, 3, 3), Arrays.asList(3, 0, 3, 3),
            Arrays.asList(3, 3, 0, 3), Arrays.asList(3, 3, 3, 0)));
  }

  public static List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> ans = new LinkedList<>();
    helper(ans, new ArrayList<>(), nums, 0);
    return ans;
  }

  private static void helper(
      List<List<Integer>> res, List<Integer> permutation, int[] nums, int index) {
    // System.out.println("permutation " + permutation + " index " + index);

    if (index == nums.length) {
      res.add(new ArrayList<Integer>(permutation));
      return;
    }
    ArrayList<Integer> newOne = new ArrayList<>(permutation);
    newOne.add(nums[index]);
    helper(res, newOne, nums, index + 1);
    for (int newIndex = newOne.size() - 1; newIndex >= 1; newIndex--) {
      // System.out.println("in permutation " + permutation + " index " + index);

      if (newOne.get(newIndex) == newOne.get(newIndex - 1)) {
        break;
      }
      int tmp = newOne.get(newIndex);
      newOne.set(newIndex, newOne.get(newIndex - 1));
      newOne.set(newIndex - 1, tmp);
      // System.out.println("in for permutation " + newOne + " index " + newIndex);
      helper(res, newOne, nums, index + 1);
    }
  }

  public static List<List<Integer>> permuteUniqueBacktrack(int[] nums) {
    Arrays.sort(nums);
    List<List<Integer>> ans = new LinkedList<>();
    backtrack(ans, new LinkedList<>(), nums, new boolean[nums.length]);
    return ans;
  }

  private static void backtrack(
      List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
    // System.out.println("tempList " + tempList + " \tused " + Arrays.toString(used));
    if (tempList.size() == nums.length) {
      list.add(new LinkedList<>(tempList));
      return;
    } else {
      for (int i = 0; i < nums.length; i++) {
        if (used[i] || i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
          continue;
        }
        tempList.add(nums[i]);
        used[i] = true;
        backtrack(list, new LinkedList<>(tempList), nums, used);
        tempList.remove(tempList.size() - 1);
        used[i] = false;
      }
    }
  }
}
