class FindFirstAndLastPositionOfElementInSortedArray {
  public static void main(String[] args) {
    Assert.assertEquals(searchRange(new int[] {5, 7, 7, 8, 8, 10}, 8), new int[] {3, 4});
    Assert.assertEquals(searchRange(new int[] {5, 7, 7, 8, 8, 10}, 6), new int[] {-1, -1});
    Assert.assertEquals(searchRange(new int[] {1, 2, 3, 3, 3, 3, 4, 5, 9}, 3), new int[] {2, 5});
  }

  public static int[] searchRange(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1, left = -1, right = -1;
    while (lo <= hi) {
      int mid = (lo + hi) >>> 1;
      if (nums[mid] > target) {
        hi = mid - 1;
      } else if (nums[mid] < target) {
        lo = mid + 1;
      } else {
        int i = left = right = mid, oldHi = hi;
        hi = i - 1;
        while (lo <= hi) {
          mid = (lo + hi) >>> 1;
          if (nums[mid] == target) {
            left = mid;
            hi = mid - 1;
          } else if (nums[mid] < target) {
            lo = mid + 1;
          }
        }
        lo = i + 1;
        hi = oldHi;
        while (lo <= hi) {
          mid = (lo + hi) >>> 1;
          if (nums[mid] == target) {
            right = mid;
            lo = mid + 1;
          } else if (nums[mid] > target) {
            hi = mid - 1;
          }
        }
        break;
      }
    }
    return new int[] {left, right};
  }
}
