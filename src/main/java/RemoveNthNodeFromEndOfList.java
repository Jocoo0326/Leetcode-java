
import java.util.LinkedList;
class RemoveNthNodeFromEndOfList {
  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4, 5};
    Assert.assertEquals(
        removeNthFromEnd(ListNode.createListNode(nums), 1).toString(), "1->2->3->4");
    Assert.assertEquals(
        removeNthFromEnd(ListNode.createListNode(nums), 2).toString(), "1->2->3->5");
    Assert.assertEquals(
        removeNthFromEnd(ListNode.createListNode(nums), 3).toString(), "1->2->4->5");
    Assert.assertEquals(
        removeNthFromEnd(ListNode.createListNode(nums), 4).toString(), "1->3->4->5");
    Assert.assertEquals(
        removeNthFromEnd(ListNode.createListNode(nums), 5).toString(), "2->3->4->5");
  }

  public static ListNode removeNthFromEnd(ListNode head, int n) {
    return removeNthFromEndTwoPointers(head, n);
  }

  // time: O(L) space: O(1)
  public static ListNode removeNthFromEndTwoPointers(ListNode head, int n) {
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    ListNode fast = dummyHead, slow = dummyHead;
    for (int i = 0; i < n; i++) {
      fast = fast.next;
    }
    while (fast.next != null) {
      fast = fast.next;
      slow = slow.next;
    }
    slow.next = slow.next.next;
    return dummyHead.next;
  }

  // time: O(L) space: O(n)
  public static ListNode removeNthFromEndQueue(ListNode head, int n) {
    ListNode dummyHead = new ListNode(0);
    dummyHead.next = head;
    LinkedList<ListNode> queue = new LinkedList<>();
    ListNode cur = dummyHead;
    while (cur != null) {
      queue.add(cur);
      if (queue.size() > n + 1) {
        queue.remove();
      }
      cur = cur.next;
    }

    ListNode prev = queue.peek(), target = prev.next, next = target.next;
    prev.next = next;
    return dummyHead.next;
  }
}
