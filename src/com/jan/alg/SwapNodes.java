package com.jan.alg;

public class SwapNodes {
    public ListNode swapPairs(ListNode head) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode result = pre;
        ListNode cur = head;
        if(head == null || head.next == null) return head;
        if(head.next.next == null){
            ListNode cur1 = head.next;
            cur1.next = head;
            head.next = null;
            return cur1;
        }
        while(cur != null){
            if(cur.next != null){
                ListNode tmp = cur.next.next;
                pre.next = cur.next;
                cur.next.next = cur;
                cur.next = tmp;
                pre = cur;
                cur = tmp;
            }else{
                break;
            }
        }
        return result.next;
    }

    public static void main(String[] args) {
        int[] arr2 = {1, 3, 5, 8, 9, 22};
        ListNode node2 = new ListNode(-3);
        ListNode nn3 = node2;
        int k = 0;
        while(k < arr2.length){
            ListNode n = new ListNode(arr2[k++]);
            node2.next = n;
            node2 = node2.next;
        }
        SwapNodes sn = new SwapNodes();
        ListNode list = sn.swapPairs(nn3);
        while(list != null){
            System.out.print(list.val + "\t");
            list = list.next;
        }
    }
}
