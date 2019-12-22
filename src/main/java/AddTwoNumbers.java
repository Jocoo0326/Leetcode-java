class AddTwoNumbers {
  public static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
      val = x;
    }
  }

  public static void main(String[] args) {
    int[] leftNums = {2, 4, 3, 5};
    int[] rightNums = {5, 6, 4, 5};
    ListNode l = null, lm = null, r = null, rm = null;
    for (int i = 0; i < leftNums.length; i++) {
      ListNode num = new ListNode(leftNums[i]);
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
    for (int i = 0; i < rightNums.length; i++) {
      ListNode num = new ListNode(rightNums[i]);
      if (rm == null) {
        rm = num;
      } else {
        rm.next = num;
      }
      if (r == null) {
        r = rm;
      }
      rm = num;
    }
    ListNode result = addTwoNumbers(l, r);
    printListNode(result);
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
  }

  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode left = l1, right = l2, head = null, tail = null;
    int carry = 0;

    do {
      int l = left != null ? left.val : 0;
      int r = right != null ? right.val : 0;
      int sum = l + r + carry;
      carry = sum / 10;
      sum = sum % 10;
      ListNode num = new ListNode(sum);
      if (tail == null) {
        tail = num;
      } else {
        tail.next = num;
      }
      tail = tail.next;
      if (head == null) {
        head = tail;
      }
      left = left != null ? left.next : null;
      right = right != null ? right.next : null;
    } while (left != null || right != null || carry == 1);
    return head;
  }
}
