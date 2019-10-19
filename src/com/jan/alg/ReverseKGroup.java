package com.jan.alg;

public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode result = pre;
        ListNode cur = head;
        ListNode next = getNext(head, k-1);
        while(true){
            if(next != null){
                ListNode nn = next.next;
                ListNode tt = null;
                ListNode t = cur;
                while(cur != nn){
                    ListNode tmp = cur.next;
                    cur.next = tt;
                    tt = cur;
                    cur = tmp;
                }
                pre.next = tt;
                pre = t;
                if(nn != null){
                    next = getNext(nn, k-1);
                    if(next == null){
                        t.next = nn;
                    }
                }else{
                    next = null;
                }
            }else{
                break;
            }
        }
        return result.next;
    }

    public ListNode getNext(ListNode cur, int k){
        System.out.println("===========enter" + cur.val);
        System.out.println();
        int num = 0;
        ListNode next = null;
        while(cur != null){
            if(num < k){
                cur = cur.next;
                num++;
            }else{
                next = cur;
                break;
            }
        }
        if(next != null){
            System.out.println("------------------out" + next.val);
        }else{
            System.out.println("------------------out null");
        }
        return next;
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
//        while (nn3 != null) {
//            System.out.print(nn3.val + "\t");
//            nn3 = nn3.next;
//        }
        ReverseKGroup rkr = new ReverseKGroup();
        ListNode list = rkr.reverseKGroup(nn3, 7);
        while (list != null) {
            System.out.print(list.val + "\t");
            list = list.next;
        }
    }
}
