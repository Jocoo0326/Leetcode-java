class ReverseNodeInKGroup {
  public static void main(String[] args) {
    Assert.assertEquals(
        reverseKGroup(ListNode.createListNode(new int[] {1, 2, 3, 4, 5}), 2).toString(),
        "2->1->4->3->5");
    Assert.assertEquals(
        reverseKGroup(ListNode.createListNode(new int[] {1, 2, 3, 4, 5}), 3).toString(),
        "3->2->1->4->5");
  }

  public static ListNode reverseKGroup(ListNode head, int k) {
    return reverseKGroupLoop(head, k);
  }

  public static ListNode reverseKGroupLoop(ListNode head, int k) {
    ListNode dummyHead = new ListNode(0), tail = dummyHead, h = head, p = head;

    while (true) {
      int count = 0;
      while (p != null) {
        count++;
        if (count == k) {
          break;
        }
        p = p.next;
      }
      if (count < k) {
        tail.next = h;
        break;
      }
      ListNode next = p.next;
      p.next = null;
      ListNode prev = null, cur = h;
      while (cur != null) {
        ListNode n = cur.next;
        cur.next = prev;
        prev = cur;
        cur = n;
      }
      tail.next = p;
      tail = h;
      p = next;
      h = p;
    }
    return dummyHead.next;
  }

  public static ListNode reverseKGroupRecursive(ListNode head, int k) {
    ListNode p = head;
    int count = 0;
    while (p != null) {
      count++;
      if (count == k) {
        break;
      }
      p = p.next;
    }
    if (count < k) {
      return head;
    }
    ListNode tail = reverseKGroup(p.next, k);
    p.next = null;
    ListNode prev = null, cur = head;
    while (cur != null) {
      ListNode n = cur.next;
      cur.next = prev;
      prev = cur;
      cur = n;
    }
    head.next = tail;
    return p;
  }
}
