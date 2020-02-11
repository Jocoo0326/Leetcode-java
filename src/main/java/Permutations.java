
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
class Permutations {
  public static void main(String[] args) {
    Assert.assertEquals(permute(new int[] {1, 2, 3}),
        Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 3, 2), Arrays.asList(2, 1, 3),
            Arrays.asList(2, 3, 1), Arrays.asList(3, 1, 2), Arrays.asList(3, 2, 1)));
  }

  public static List<List<Integer>> permuteIterative(int[] nums) {
    LinkedList<List<Integer>> ans = new LinkedList<>();
    ans.add(new LinkedList<>());
    int size = 0;
    while ((size = ans.peek().size()) < nums.length) {
      final List<Integer> list = ans.poll();
      for (int i = 0; i < size + 1; i++) {
        List<Integer> tempList = new LinkedList<>(list);
        tempList.add(i, nums[size]);
        ans.add(tempList);
      }
    }
    return ans;
  }

  public static List<List<Integer>> permute(int[] nums) {
    LinkedList<List<Integer>> ans = new LinkedList<>();
    permuteBacktrack(ans, new LinkedList<>(), nums);
    return ans;
  }

  private static void permuteBacktrack(
      List<List<Integer>> list, List<Integer> tempList, int[] nums) {
    if (tempList.size() == nums.length) {
      list.add(new LinkedList<>(tempList));
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (tempList.contains(nums[i])) {
        continue;
      }
      tempList.add(nums[i]);
      permuteBacktrack(list, tempList, nums);
      tempList.remove(tempList.size() - 1);
    }
  }
}
