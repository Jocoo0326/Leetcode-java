
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
class Subsets {
  public static void main(String[] args) {
    Assert.assertEquals(subsets(new int[] {1, 2, 3}),
        Arrays.asList(Arrays.asList(3), Arrays.asList(1), Arrays.asList(2), Arrays.asList(1, 2, 3),
            Arrays.asList(1, 3), Arrays.asList(2, 3), Arrays.asList(1, 2), Arrays.asList()));
  }

  public static List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new LinkedList<>();
    backtrack(ans, new LinkedList<>(), 0, nums);
    return ans;
  }

  public static List<List<Integer>> subsetsIterative(int[] nums) {
    List<List<Integer>> ans = new LinkedList<>();
    ans.add(new LinkedList<Integer>());
    for (int i = 0; i < nums.length; i++) {
      int size = ans.size();
      for (int j = 0; j < size; j++) {
        List<Integer> tempList = new LinkedList<>(ans.get(j));
        tempList.add(nums[i]);
        ans.add(tempList);
      }
    }
    return ans;
  }

  private static void backtrack(
      List<List<Integer>> list, List<Integer> tempList, int start, int[] nums) {
    list.add(new LinkedList<>(tempList));
    for (int i = start; i < nums.length; i++) { // collect all the solution with the same length
      tempList.add(nums[i]);
      backtrack(list, tempList, i + 1, nums);
      tempList.remove(tempList.size() - 1);
    }
  }
}
