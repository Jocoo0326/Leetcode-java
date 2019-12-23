class LinkedListCycle {
  public static void main(String[] args) {
    Assert.assertEquals(hasCycle(ListNode.createListNode(new int[] {1})), false);
    ListNode h1 = ListNode.createListNode(new int[] {1, 2, 3, 4});
    h1.tail().next = h1.next;
    Assert.assertEquals(hasCycle(h1), true);

    ListNode h2 = ListNode.createListNode(new int[] {1, 2, 3, 4});
    h2.tail().next = h2;
    Assert.assertEquals(hasCycle(h2), true);
  }

  public static boolean hasCycle(ListNode head) {
    ListNode fast = head, slow = head;
    while (fast != null && fast.next != null && slow != null) {
      fast = fast.next.next;
      slow = slow.next;
      if (fast == slow) {
        // System.out.println(fast.val);
        // s + t = 0 (mod L)
        // s: length of fast behind slow when slow enter in cycle
        // t: steps of meeting first after slow enter in cycle
        // L: cycle length
        return true;
      }
    }
    return false;
  }
}
