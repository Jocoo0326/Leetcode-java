class MedianOfTwoSortedArrays {
  public static void main(String[] args) {
    int[] nums1 = {1, 3};
    int[] nums2 = {2};
    Assert.assertEquals(findMedianSortedArrays(nums1, nums2), 2.0);

    int[] nums3 = {1, 2};
    int[] nums4 = {3, 4};
    Assert.assertEquals(findMedianSortedArrays(nums3, nums4), 2.5);
  }

  public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
      int[] p = (nums1 == null || nums1.length == 0) ? nums2 : nums1;
      int len = p.length;
      if (len % 2 == 0) {
        return (p[len / 2 - 1] + p[len / 2]) * 0.5;
      } else {
        return p[len / 2] * 1.0;
      }
    }
    int len = nums1.length + nums2.length;
    boolean takeTwo = len % 2 == 0;
    int count = 0;
    int end = (len / 2) + 1;
    int i = 0, j = 0;
    int[] sum = {0, 0};

    while (count < end) {
      int l1 = i < nums1.length ? nums1[i] : Integer.MAX_VALUE;
      int l2 = j < nums2.length ? nums2[j] : Integer.MAX_VALUE;
      if (l1 < l2) {
        i++;
      } else {
        j++;
      }
      if (count == ((len / 2) - 1) && takeTwo) {
        sum[0] = Math.min(l1, l2);
      }
      if (count == len / 2) {
        sum[1] = Math.min(l1, l2);
      }
      count++;
    }

    return (sum[0] + sum[1]) * (takeTwo ? 0.5 : 1.0);
  }
}
