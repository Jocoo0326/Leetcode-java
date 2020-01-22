class SearchInsertPosition {
  public static void main(String[] args) {
    Assert.assertEquals(searchInsert(new int[] {1, 3, 5, 6}, 5), 2);
    Assert.assertEquals(searchInsert(new int[] {1, 3, 5, 6}, 2), 1);
    Assert.assertEquals(searchInsert(new int[] {1, 3, 5, 6}, 7), 4);
    Assert.assertEquals(searchInsert(new int[] {1, 3, 5, 6}, 0), 0);
  }

  public static int searchInsert(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi) {
      int mid = (lo + hi) >>> 1;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return lo;
  }
}
