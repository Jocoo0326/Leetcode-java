class AddTwoNumbers {
  public static void main(String[] args) {
    int[] leftNums = {2, 4, 3, 5};
    int[] rightNums = {5, 6, 4, 5};
    ListNode l = ListNode.createListNode(leftNums), r = ListNode.createListNode(rightNums);
    ListNode result = addTwoNumbers(l, r);
    Assert.assertEquals(result.toString(), "7->0->8->0->1");
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode left = l1, right = l2, dummyHead = new ListNode(0), cur = dummyHead;
    int carry = 0;

    do {
      int l = left != null ? left.val : 0;
      int r = right != null ? right.val : 0;
      int sum = l + r + carry;
      carry = sum / 10;
      sum = sum % 10;
      cur.next = new ListNode(sum);
      cur = cur.next;
      left = left != null ? left.next : null;
      right = right != null ? right.next : null;
    } while (left != null || right != null || carry == 1);
    return dummyHead.next;
  }
}
