
import java.util.HashMap;

class TwoSum {
  public static void main(String[] args) {
    int[] inputs = {2, 7, 11, 15};
    int target = 22;
    System.out.println(twoSum(inputs, target)[0]);
    System.out.println(twoSum(inputs, target)[1]);
  }

  public static int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int pair = 0;
    Integer index;
    for (int i = 0; i < nums.length; i++) {
      pair = target - nums[i];
      index = map.get(pair);
      if (index == null) {
        map.put(nums[i], i);
        continue;
      }
      return new int[] {index, i};
    }
    return new int[] {0, 0};
  }
}
