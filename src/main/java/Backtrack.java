
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
class Backtrack {
  public static void main(String[] args) {
    Assert.assertEquals(subset(new int[] {1, 2}),
        Arrays.asList(Arrays.asList(), Arrays.asList(1), Arrays.asList(2), Arrays.asList(1, 2)));
  }

  private static List<List<Integer>> subset(int[] nums) {
    List<List<Integer>> list = new LinkedList<>();
    subsetBacktrack(list, new LinkedList<>(), 0, nums);
    return list;
  }

  private static void subsetBacktrack(
      List<List<Integer>> list, List<Integer> tempList, int start, int[] nums) {
    list.add(tempList);
    for (int i = start; i < nums.length; i++) {
      tempList.add(nums[i]);
      subsetBacktrack(list, new LinkedList(tempList), i + 1, nums);
      tempList.remove(tempList.size() - 1);
    }
  }
}
