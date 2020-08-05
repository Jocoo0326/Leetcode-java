
import java.util.ArrayDeque;
class PrintTreeInDepth {
  public static void main(String[] args) {
    TreeNode<Integer> root = new TreeNode<>(16);
    int[] nums = {13, 10, 15, 20, 22, 21, 26};
    for (int i = 0; i < nums.length; i++) {
      insert(root, nums[i]);
    }
    System.out.println(levelTraverse(root));
  }

  public static <T extends Comparable<T>> String levelTraverse(TreeNode<T> root) {
    ArrayDeque<TreeNode<T>> q = new ArrayDeque<>();
    q.addLast(root);
    StringBuilder sb = new StringBuilder();
    while (!q.isEmpty()) {
      final TreeNode<T> e = q.pollFirst();
      if (sb.length() > 0) {
        sb.append(", ");
      }
      sb.append(e.value);
      if (e.left != null) {
        q.addLast(e.left);
      }
      if (e.right != null) {
        q.addLast(e.right);
      }
    }
    return sb.toString();
  }

  static class TreeNode<T extends Comparable<T>> {
    TreeNode<T> left;
    TreeNode<T> right;
    T value;

    TreeNode(T value) { this.value = value; }
  }

  static <T extends Comparable<T>> TreeNode<T> insert(TreeNode<T> root, T value) {
    TreeNode<T> p = root, pp = null;
    int compare = 0;
    while (p != null) {
      pp = p;
      compare = p.value.compareTo(value);
      if (compare > 0) {
        p = p.left;
      } else if (compare < 0) {
        p = p.right;
      } else {
        // equals
      }
    }
    if (compare > 0) {
      pp.left = new TreeNode<T>(value);
    } else if (compare < 0) {
      pp.right = new TreeNode<T>(value);
    } else {
      pp.value = value;
    }
    return root;
  }
}
