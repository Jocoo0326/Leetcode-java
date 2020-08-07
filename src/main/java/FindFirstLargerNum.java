class FindFirstLargerNum {
  public static void main(String[] args) {
    int[] nums = {-1, 3, 3, 7, 10, 14, 14};
    Assert.assertEquals(find(nums, 9), 10);
    Assert.assertEquals(find(nums, 3), 7);
    Assert.assertEquals(find(nums, 10), 14);
    Assert.assertEquals(find(nums, 14), 14);

    int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
    Assert.assertEquals(findInRotatedSortArray(nums1, 0), 4);
    Assert.assertEquals(findInRotatedSortArray(nums1, 6), 2);
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

  public static int findInRotatedSortArray(int[] nums, int val) {
    int i = 0, j = nums.length - 1;
    while (i <= j) {
      int mid = (i + j) >>> 1;
      if (val > nums[mid]) {
        i = mid + 1;
      } else if (val < nums[mid]) {
        if (nums[i] > nums[j]) {
          if (val >= nums[i]) {
            j = mid - 1;
          } else {
            i = mid + 1;
          }
        } else {
          j = mid - 1;
        }
      } else {
        return i;
      }
    }
    return i;
  }
}
