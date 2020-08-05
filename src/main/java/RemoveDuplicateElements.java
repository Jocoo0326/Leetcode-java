class RemoveDuplicateElements {
  public static void main(String[] args) {
    int[] nums1 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
    int[] nums2 = {0, 1, 2, 3, 4, 2, 2, 3, 3, 4};
    Assert.assertEquals(removeDuplicates(nums1), 5);
    Assert.assertEquals(nums1, nums2);
  }

  public static int removeDuplicates(int[] nums) {
    int j = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[j-1] < nums[i]) {
	nums[j++] = nums[i];
      }
    }
    return j;
  }
}
