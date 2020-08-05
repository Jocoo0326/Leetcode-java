class FindFirstLargerNum {
  public static void main(String[] args) {
    int[] nums = {-1, 3, 3, 7, 10, 14, 14};
    Assert.assertEquals(find(nums, 9), 10);
    Assert.assertEquals(find(nums, 3), 7);
    Assert.assertEquals(find(nums, 10), 14);
    Assert.assertEquals(find(nums, 14), 14);
  }

  private static int find(int[] nums, int val) {
    int i = 0, j = nums.length - 1;
    while (i < j) {
      int mid = (i + j) >>> 1;
      if (nums[mid] > val) {
        j = mid;
      } else if (nums[mid] <= val) {
        i = mid + 1;
      }
    }
    return nums[i];
  }
}
