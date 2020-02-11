
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
class CombinationSum {
  public static void main(String[] args) {
    Assert.assertEquals(combinationSum(new int[] {2, 3, 6, 7}, 7),
        Arrays.asList(Arrays.asList(7), Arrays.asList(2, 2, 3)));
    Assert.assertEquals(combinationSum(new int[] {2, 3, 5}, 8),
        Arrays.asList(Arrays.asList(2, 2, 2, 2), Arrays.asList(2, 3, 3), Arrays.asList(3, 5)));
  }

  public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    ans = new LinkedList<>();
    List<Integer> val = new LinkedList<>();
    backtrack(candidates, 0, target, val);
    return ans;
  }

  private static List<List<Integer>> ans = new LinkedList<>();
  private static LinkedList<Integer> makecopy(List<Integer> res) {
    LinkedList<Integer> val = new LinkedList<>();
    for (Integer i : res) {
      val.add(i);
    }
    return val;
  }
  private static boolean backtrack(int[] candidates, int sum, int target, List<Integer> res) {
    boolean b = false;
    for (int cand : candidates) {
      LinkedList<Integer> backup = makecopy(res);
      Integer last = backup.peekLast();
      last = last == null ? 0 : last;
      if (last <= cand) {
        if (sum + cand < target) {
          backup.add(cand);
          if (backtrack(candidates, sum + cand, target, backup)) {
            b = true;
          }
        } else if (sum + cand == target) {
          backup.add(cand);
          ans.add(backup);
          b = true;
        }
      }
    }
    return b;
  }
}
