class MedianOfTwoSortedArrays {
  public static void main(String[] args) {
    int[] nums1 = {2, 3, 4};
    int[] nums2 = {};
    Assert.assertEquals(findMedianSortedArrays(nums1, nums2), 3.0);

    int[] nums3 = {1, 2};
    int[] nums4 = {3, 4};
    Assert.assertEquals(findMedianSortedArrays(nums3, nums4), 2.5);

    int[] nums5 = {1, 3, 5};
    int[] nums6 = {2, 4, 6};
    Assert.assertEquals(findMedianSortedArrays(nums5, nums6), 3.5);
  }

  public static double afindMedianSortedArrays(int[] nums1, int[] nums2) {
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

  public static double bfindMedianSortedArrays(int[] A, int[] B) {
    int m = A.length;
    int n = B.length;
    if (m > n) { // to ensure m<=n
      int[] temp = A;
      A = B;
      B = temp;
      int tmp = m;
      m = n;
      n = tmp;
    }
    int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
    while (iMin <= iMax) {
      int i = (iMin + iMax) / 2;
      int j = halfLen - i;

      if (i < iMax && B[j - 1] > A[i]) { // i < m -> j > 0;i>=0 -> j<=n
        iMin = i + 1; // i is too small
      } else if (i > iMin && A[i - 1] > B[j]) { // i>0 -> j < (m+n+1)/2<=(2n+1)/2
        iMax = i - 1; // i is too big
      } else { // i is perfect
        int maxLeft = 0;
        if (i == 0) {
          maxLeft = B[j - 1];
        } else if (j == 0) {
          maxLeft = A[i - 1];
        } else {
          maxLeft = Math.max(A[i - 1], B[j - 1]);
        }
        if ((m + n) % 2 == 1) {
          return maxLeft;
        }

        int minRight = 0;
        if (i == m) {
          minRight = B[j];
        } else if (j == n) {
          minRight = A[i];
        } else {
          minRight = Math.min(B[j], A[i]);
        }

        return (maxLeft + minRight) / 2.0;
      }
    }
    return 0.0;
  }

  public static double findMedianSortedArrays(int[] A, int[] B) {
    int m = A.length, n = B.length;
    if (m > n) {
      int[] tmp = A;
      A = B;
      B = tmp;
    }
    m = A.length;
    n = B.length;
    int l = 0, h = m, halfLen = (m + n + 1) >> 1;
    boolean odd = (m + n) % 2 == 1;
    while (l <= h) {
      int i = (l + h) >> 1;
      int j = halfLen - i;
      if (i > 0 && A[i - 1] > B[j]) { // i > 0-> j < n
        h = i - 1;
      } else if (i < m && B[j - 1] > A[i]) { // i < m->j>(n-m+1)/2->j>0
        l = i + 1;
      } else {
        int leftMax = 0;
        if (i == 0) {
          leftMax = B[j - 1];
          ;
        } else if (j == 0) {
          leftMax = A[i - 1];
        } else {
          leftMax = Math.max(A[i - 1], B[j - 1]);
        }
        if (odd) {
          return leftMax;
        }
        int rightMin = 0;
        if (i == m) {
          rightMin = B[j];
        } else if (j == n) {
          rightMin = A[i];
        } else {
          rightMin = Math.min(A[i], B[j]);
        }

        return (leftMax + rightMin) * 0.5;
      }
    }
    return 0.0;
  }
}
