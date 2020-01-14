class MergeTwoSortedLists {
  public static void main(String[] args) {
    Assert.assertEquals(
        mergeTwoLists(ListNode.createListNode(new int[] {1, 2, 3, 4, 5}), null).toString(),
        "1->2->3->4->5");
    Assert.assertEquals(mergeTwoLists(ListNode.createListNode(new int[] {1, 2, 4}),
                            ListNode.createListNode(new int[] {1, 3, 4}))
                            .toString(),
        "1->1->2->3->4->4");
  }

  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    return mergeTwoListsRecursive(l1, l2);
  }

  public static ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
    if (l1 == null || l2 == null) {
      return l1 == null ? l2 : l1;
    }
    if (l1.val <= l2.val) {
      l1.next = mergeTwoListsRecursive(l1.next, l2);
      return l1;
    } else {
      l2.next = mergeTwoListsRecursive(l1, l2.next);
      return l2;
    }
  }

  public static ListNode mergeTwoListsLoop(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0), p1 = l1, p2 = l2, cur = dummyHead;
    while (p1 != null && p2 != null) {
      if (p1.val <= p2.val) {
        cur.next = p1;
        p1 = p1.next;
      } else {
        cur.next = p2;
        p2 = p2.next;
      }
      cur = cur.next;
    }
    if (p1 != null) {
      cur.next = p1;
    } else if (p2 != null) {
      cur.next = p2;
    }
    return dummyHead.next;
  }
}
