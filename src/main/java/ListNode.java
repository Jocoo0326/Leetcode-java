public class ListNode {
  int val;
  ListNode next;
  ListNode(int x) {
    val = x;
  }

  public static ListNode createListNode(int[] arr) {
    ListNode dummyHead = new ListNode(0), cur = dummyHead;
    for (int i = 0; i < arr.length; i++) {
      cur.next = new ListNode(arr[i]);
      cur = cur.next;
    }
    return dummyHead.next;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    ListNode p = this;
    while (p != null) {
      if (p != this) {
        sb.append("->");
      }
      sb.append(p.val);
      p = p.next;
    }
    return sb.toString();
  }

  public ListNode tail() {
    ListNode p = this;
    while (p != null) {
      if (p.next == null) {
        return p;
      }
      p = p.next;
    }
    return this;
  }
}
