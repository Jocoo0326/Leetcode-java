
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
class SubsetsII {
  public static void main(String[] args) {
    Assert.assertEquals(subsetsWithDup(new int[] {1, 2, 2}),
        Arrays.asList(Arrays.asList(2), Arrays.asList(1), Arrays.asList(), Arrays.asList(1, 2, 2),
            Arrays.asList(2, 2), Arrays.asList(1, 2)));

    Assert.assertEquals(subsetsWithDup(new int[] {4, 4, 4, 1, 4}),
        Arrays.asList(Arrays.asList(), Arrays.asList(1), Arrays.asList(1, 4),
            Arrays.asList(1, 4, 4), Arrays.asList(1, 4, 4, 4), Arrays.asList(1, 4, 4, 4, 4),
            Arrays.asList(4), Arrays.asList(4, 4), Arrays.asList(4, 4, 4),
            Arrays.asList(4, 4, 4, 4)));
  }

  public static List<List<Integer>> subsetsWithDup(int[] nums) {
    List<List<Integer>> ans = new LinkedList<>();
    Arrays.sort(nums);
    backtrack(ans, new LinkedList<>(), 0, nums);
    return ans;
  }

  private static void backtrack(
      List<List<Integer>> list, List<Integer> tempList, int start, int[] nums) {
    list.add(new LinkedList<>(tempList));
    for (int i = start; i < nums.length; i++) {
      if (i > start && nums[i - 1] == nums[i]) {
        continue;
      }
      tempList.add(nums[i]);
      backtrack(list, tempList, i + 1, nums);
      tempList.remove(tempList.size() - 1);
    }
  }
}
