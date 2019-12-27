
import java.util.Arrays;

class QuickSort {
  public static void main(String[] args) {
    final int[] original = {3, 5, -1, 2, 11, 7, 4, 2, 3};
    int[] copy = Arrays.copyOf(original, original.length);
    Arrays.sort(original);
    quickSort(copy, 0, copy.length - 1);
    Assert.assertEquals(Arrays.toString(copy), Arrays.toString(original));
  }

  public static void quickSort(int[] array, int l, int h) {
    int pivot = array[h];
    int i = l - 1;
    for (int j = l; j < h; j++) {
      if (array[j] < pivot) {
        i = i + 1;
        // swap
        int t = array[i];
        array[i] = array[j];
        array[j] = t;
      }
    }
    int t = array[++i];
    array[i] = array[h];
    array[h] = t;
    if (i - l > 1) {
      quickSort(array, l, i - 1);
    }
    if (h - i > 1) {
      quickSort(array, i + 1, h);
    }
  }
}
