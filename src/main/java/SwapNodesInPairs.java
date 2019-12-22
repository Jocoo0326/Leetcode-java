class SwapNodesInPairs {
  public static void main(String[] args) {
    int[] nodes = {2}; //, 4, 3, 5, 7, 8};
    ListNode l = null, lm = null;
    for (int i = 0; i < nodes.length; i++) {
      ListNode num = new ListNode(nodes[i]);
      if (lm == null) {
        lm = num;
      } else {
        lm.next = num;
      }
      if (l == null) {
        l = lm;
      }
      lm = num;
    }
    printListNode(swapPairs(l));
  }

  public static void printListNode(ListNode head) {
    ListNode p = head;
    while (p != null) {
      if (p != head) {
        System.out.print("->");
      }
      System.out.print(p.val);
      p = p.next;
    }
    System.out.print("\n");
  }

  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
      val = x;
    }
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
