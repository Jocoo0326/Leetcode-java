class RemoveElement {
  public static void main(String[] args) {
    assertArray(new int[] {3, 2, 2, 3}, 3, new int[] {2, 2});
    assertArray(new int[] {0, 1, 2, 2, 3, 0, 4, 2}, 2, new int[] {0, 1, 3, 0, 4});
  }

  public static void assertArray(int[] nums, int val, int[] arr) {
    int[] result = new int[removeElement(nums, val)];
    System.arraycopy(nums, 0, result, 0, result.length);
    Assert.assertEquals(result, arr);
  }

  public static int removeElement(int[] nums, int val) {
    if (nums == null) {
      return 0;
    }
    int tail = 0, i = 0;
    while (i < nums.length) {
      if (nums[i++] != val) {
        nums[tail++] = nums[i - 1];
      }
    }
    return tail;
  }

  public static int aremoveElement(int[] nums, int val) {
    if (nums == null) {
      return 0;
    }
    int i = 0, n = nums.length;
    while (i < n) {
      if (nums[i] == val) {
        nums[i] = nums[n - 1];
        n--;
      } else {
        i++;
      }
    }
    return n;
  }
}
