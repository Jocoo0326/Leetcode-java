class ReverseLinkedList {
  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4, 5};
    ListNode h = ListNode.createListNode(nums);
    // Assert.assertEquals(reverseList(h).toString(), "5->4->3->2->1");
    Assert.assertEquals(reverseListRecursive(h).toString(), "5->4->3->2->1");
  }

  public static ListNode reverseList(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode cur = null, p = head;
    while (p != null) {
      ListNode old = p.next;
      p.next = cur;
      cur = p;
      p = old;
    }
    return cur;
  }

  public static ListNode reverseListRecursive(ListNode head) {
    if (head == null || head.next == null)
      return head;
    final ListNode old = head.next;
    ListNode tail = reverseListRecursive(old);
    head.next.next = head;
    head.next = null;
    return tail;
  }
}
