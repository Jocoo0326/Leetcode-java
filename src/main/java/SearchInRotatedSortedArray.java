class SearchInRotatedSortedArray {
  public static void main(String[] args) {
    Assert.assertEquals(search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0), 4);
    Assert.assertEquals(search(new int[] {4, 5, 6, 7, 0, 1, 2}, 3), -1);
  }

  public static int search(int[] nums, int target) {
    // return searchRecursive(nums, target, 0, nums.length - 1);
    // return searchNonRecursive(nums, target);
    // return searchExcluding(nums, target);
    return findInRotatedSortArray(nums, target);
  }

  public static int findInRotatedSortArray(int[] nums, int val) {
    int i = 0, j = nums.length - 1;
    while (i <= j) {
      int mid = (i + j) >>> 1;
      if (val > nums[mid]) {
        i = mid + 1;
      } else if (val < nums[mid]) {
        if (nums[i] > nums[j] && val < nums[i]) {
          i = mid + 1;
        } else {
          j = mid - 1;
        }
      } else {
        return i;
      }
    }
    return -1;
  }

  public static int searchExcluding(int[] nums, int target) {
    final int N = nums.length;
    int lo = 0, hi = N - 1;
    while (lo <= hi) {
      boolean targetLeftPart = target >= nums[lo];
      int mid = (lo + hi) >>> 1;
      boolean midLeftPart = nums[mid] >= nums[lo];
      if (targetLeftPart ^ midLeftPart) {
        if (targetLeftPart) {
          hi = mid - 1;
        } else {
          lo = mid + 1;
        }
      } else {
        if (target < nums[mid]) {
          hi = mid - 1;
        } else if (target > nums[mid]) {
          lo = mid + 1;
        } else {
          return mid;
        }
      }
    }
    return -1;
  }

  public static int searchNonRecursive(int[] nums, int target) {
    int lo = 0, hi = nums.length - 1;
    // find rotated position
    while (lo < hi) {
      int mid = (lo + hi) >>> 1;
      if (nums[mid] > nums[hi]) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }

    int rot = lo;
    lo = 0;
    hi = nums.length - 1;
    while (lo <= hi) {
      int mid = (lo + hi) >>> 1;
      int realMid = (rot + mid) % nums.length;
      if (nums[realMid] == target) {
        return realMid;
      } else if (nums[realMid] < target) {
        lo = mid + 1;
      } else {
        hi = mid - 1;
      }
    }
    return -1;
  }

  public static int searchRecursive(int[] nums, int target, int lo, int hi) {
    if (lo > hi) {
      return -1;
    }
    int mid = (lo + hi) >>> 1;
    if (nums[mid] == target) {
      return mid;
    }
    if (nums[lo] > nums[mid]) {
      int i = searchBinary(nums, target, mid + 1, hi);
      return i != -1 ? i : searchRecursive(nums, target, lo, mid - 1);
    }
    int i = searchBinary(nums, target, lo, mid - 1);
    return i != -1 ? i : searchRecursive(nums, target, mid + 1, hi);
  }

  private static int searchBinary(int[] nums, int target, int lo, int hi) {
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
    return -1;
  }
}
