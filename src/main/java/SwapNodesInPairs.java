class SwapNodesInPairs {
  public static void main(String[] args) {
    int[] nodes = {2, 4, 3, 5, 7, 9, 10}; //, 4, 3, 5, 7, 8};
    ListNode l = ListNode.createListNode(nodes);
    Assert.assertEquals(swapPairs(l).toString(), "4->2->5->3->9->7->10");
  }

  public static ListNode swapPairs(ListNode head) {
    if (head == null)
      return null;
    ListNode dummyNode = new ListNode(0);
    ListNode result = head, p = head, tail = dummyNode;
    while (p != null && p.next != null) {
      ListNode node = p.next;
      final ListNode old = node.next;
      node.next = p;
      p.next = old;
      tail.next = node;
      tail = p;
      p = old;
    }
    if (p != null) {
      tail.next = p;
    }
    return dummyNode.next;
  }
}
