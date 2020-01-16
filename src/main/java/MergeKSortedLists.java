
import java.util.Comparator;
import java.util.PriorityQueue;
class MergeKSortedLists {
  public static void main(String[] args) {
    int[][] nums = {{1, 4, 5}, {1, 3, 4}, {2, 6}};
    ListNode[] lists = new ListNode[nums.length];
    for (int i = 0; i < nums.length; i++) {
      lists[i] = ListNode.createListNode(nums[i]);
      System.out.println(lists[i].toString());
    }
    Assert.assertEquals(mergeKLists(lists).toString(), "1->1->2->3->4->4->5->6");
  }

  // O(N log(k)) k is number of linked lists, N is total number of nodes in the final linked list
  public static ListNode mergeKLists(ListNode[] lists) {
    if (lists == null)
      return null;
    if (lists.length == 1) {
      return lists[0];
    }
    final Comparator<ListNode> c = new Comparator<ListNode>() {
      @Override
      public int compare(ListNode o1, ListNode o2) {
        return o1.val - o2.val;
      }
    };
    PriorityQueue<ListNode> queue = new PriorityQueue<>(c);
    for (int i = 0; i < lists.length; i++) {
      if (lists[i] != null)
        queue.add(lists[i]);
    }

    ListNode dummyHead = new ListNode(0), cur = dummyHead;
    while (true) {
      ListNode elm = queue.poll();
      if (elm == null)
        break;
      cur.next = new ListNode(elm.val);
      cur = cur.next;
      ListNode elmNext = elm.next;
      if (elmNext != null)
        queue.add(elmNext);
    }
    return dummyHead.next;
  }

  // O(kN)
  public static ListNode amergeKLists(ListNode[] lists) {
    if (lists == null)
      return null;
    if (lists.length == 1) {
      return lists[0];
    }
    ListNode[] p = new ListNode[lists.length];
    for (int i = 0; i < lists.length; i++) {
      p[i] = lists[i];
    }
    ListNode dummyHead = new ListNode(0), cur = dummyHead;
    while (!arrAllNull(p)) {
      int indexMin = minOfArray(p);
      cur.next = new ListNode(p[indexMin].val);
      cur = cur.next;
      p[indexMin] = p[indexMin].next;
    }
    return dummyHead.next;
  }

  public static int minOfArray(ListNode[] arr) {
    int idx = -1, min = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == null)
        continue;
      int val = arr[i].val;
      if (val < min) {
        idx = i;
        min = val;
      }
    }
    return idx;
  }

  public static boolean arrAllNull(ListNode[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] != null) {
        return false;
      }
    }
    return true;
  }
}
