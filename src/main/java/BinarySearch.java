class BinarySearch {
  public static void main(String[] args) {
    int[] arr = {1, 2, 4, 5, 7, 9, 10};
    Assert.assertEquals(binarySearch(arr, 4), 2);
    Assert.assertEquals(binarySearch(arr, 3), -3);
    Assert.assertEquals(binarySearch(arr, 9), 5);
    Assert.assertEquals(binarySearch(arr, 7), 4);
    Assert.assertEquals(binarySearch(arr, 100), -8);
  }

  public static int binarySearch(int[] array, int key) {
    int low = 0;
    int high = array.length - 1;

    while (low <= high) {
      int mid = (low + high) >>> 1; // works for integer overflow cause for '>>>'
      // int mid = low + (high - low) / 2;
      if (array[mid] < key) {
        low = mid + 1;
      } else if (array[mid] > key) {
        high = mid - 1;
      } else {
        return mid;
      }
    }
    return -(low + 1);
  }
}
