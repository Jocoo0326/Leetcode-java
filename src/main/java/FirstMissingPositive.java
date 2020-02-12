
import java.util.Arrays;
class FirstMissingPositive {
  public static void main(String[] args) {
    Assert.assertEquals(firstMissingPositive(new int[] {1, 2, 0}), 3);
    Assert.assertEquals(firstMissingPositive(new int[] {3, 4, -1, 1}), 2);
    Assert.assertEquals(firstMissingPositive(new int[] {7, 8, 9, 11, 12}), 1);
    Assert.assertEquals(firstMissingPositive(new int[] {3, 4, 2, 1}), 5);
    Assert.assertEquals(firstMissingPositive(new int[] {0, 2, 2, 1, 1}), 3);
  }

  // move number to its right place
  public static int firstMissingPositive(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
      // i -> A[i] i -> i+1=A[x]
      while (nums[i] > 0 && nums[i] < nums.length + 1 && nums[nums[i] - 1] != nums[i]) {
        swap(nums, i, nums[i] - 1);
      }
    }
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != i + 1) {
        return i + 1;
      }
    }
    return nums.length + 1;
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
  }
}
