
import java.util.Arrays;
class NextPermutation {
  public static void main(String[] args) {
    assertArray(new int[] {1, 1}, new int[] {1, 1});
    assertArray(new int[] {1, 2, 3}, new int[] {1, 3, 2});
    assertArray(new int[] {1, 3, 2}, new int[] {2, 1, 3});
    assertArray(new int[] {2, 3, 1}, new int[] {3, 1, 2});
    assertArray(new int[] {3, 2, 1}, new int[] {1, 2, 3});
    assertArray(new int[] {1, 1, 5}, new int[] {1, 5, 1});
    assertArray(new int[] {1, 5, 1}, new int[] {5, 1, 1});
    assertArray(new int[] {5, 1, 1}, new int[] {1, 1, 5});
    assertArray(new int[] {1, 2, 5, 4, 3}, new int[] {1, 3, 2, 4, 5});
    assertArray(new int[] {3, 2, 5, 4, 1}, new int[] {3, 4, 1, 2, 5});
  }

  private static void assertArray(int[] nums, int[] expectNums) {
    nextPermutation(nums);
    Assert.assertEquals(nums, expectNums);
  }

  public static void nextPermutation(int[] nums) {
    if (nums == null) {
      return;
    }
    int i = nums.length - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
      i--;
    }
    if (i > -1) {
      int j = nums.length - 1;
      while (j >= 0 && nums[j] <= nums[i]) {
        j--;
      }
      swap(nums, i, j);
    }
    reverse(nums, i + 1);
  }

  private static void swap(int[] nums, int i, int j) {
    int temp = nums[j];
    nums[j] = nums[i];
    nums[i] = temp;
  }

  private static void reverse(int[] nums, int i) {
    int j = nums.length - 1;
    while (i < j) {
      swap(nums, i, j);
      i++;
      j--;
    }
  }
}
