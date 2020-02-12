
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
class CombinationSumII {
  public static void main(String[] args) {
    Assert.assertEquals(combinationSum2(new int[] {10, 1, 2, 7, 6, 1, 5}, 8),
        Arrays.asList(Arrays.asList(1, 7), Arrays.asList(1, 2, 5), Arrays.asList(2, 6),
            Arrays.asList(1, 1, 6)));

    Assert.assertEquals(combinationSum2(new int[] {2, 5, 2, 1, 2}, 5),
        Arrays.asList(Arrays.asList(1, 2, 2), Arrays.asList(5)));
  }

  public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> ans = new LinkedList<>();
    backtrack(ans, new LinkedList<>(), candidates, target, 0, 0);
    return ans;
  }

  private static void backtrack(List<List<Integer>> ans, List<Integer> list, int[] candidates,
      int target, int sum, int index) {
    if (sum == target) {
      ans.add(new LinkedList<>(list));
    }
    for (int i = index; i < candidates.length; i++) {
      if (sum + candidates[i] > target) {
        break;
      }
      if (i > index && candidates[i - 1] == candidates[i]) {
        continue;
      }
      list.add(candidates[i]);
      backtrack(ans, new LinkedList<>(list), candidates, target, sum + candidates[i], i + 1);
      list.remove(list.size() - 1);
    }
  }
}
