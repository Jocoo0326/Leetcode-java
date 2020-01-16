
import java.util.Arrays;

class RemoveDuplicatesFromSortedArray {
  public static void main(String[] args) {
    assertArray(new int[] {1, 1, 2}, new int[] {1, 2});
    assertArray(new int[] {0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, new int[] {0, 1, 2, 3, 4});
  }

  public static void assertArray(int[] nums, int[] arr) {
    int[] result = new int[removeDuplicates(nums)];
    System.arraycopy(nums, 0, result, 0, result.length);
    Assert.assertEquals(result, arr);
  }

  public static int removeDuplicates(int[] nums) {
    if (nums == null || nums.length < 2) {
      return nums == null ? 0 : nums.length;
    }
    int tail = 1, i = 1;
    while (i < nums.length) {
      if (nums[i++] != nums[tail - 1]) {
        nums[tail++] = nums[i - 1];
      }
    }
    return tail;
  }
}
