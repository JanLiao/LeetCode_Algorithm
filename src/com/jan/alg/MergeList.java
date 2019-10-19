package com.jan.alg;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

public class MergeList {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode cur = null;
        int min = Integer.MAX_VALUE;
        int idx = 0;
        for (int i = 0; i < lists.length; i++) {
            ListNode list = lists[i];
            if(list != null){
                if(list.val <= min){
                    min = list.val;
                    cur = list;
                    idx = i;
                }
            }
        }
        if(cur == null) return cur;
        lists[idx] = lists[idx].next;
        ListNode result = cur;
        while(true){
            min = Integer.MAX_VALUE;
            ListNode tmp = null;
            idx = 0;
            int sum = 0;
            for (int i = 0; i < lists.length; i++) {
                ListNode list = lists[i];
                if(list != null){
                    //System.out.println(list.val + " ,min = " + min);
                    if(list.val <= min){
                        min = list.val;
                        tmp = list;
                        idx = i;
                    }
                }else{
                    sum++;
                }
            }
            if(sum == lists.length){
                break;
            }
            cur.next = tmp;
            cur = cur.next;
            //System.out.println("idx = " + idx);
            lists[idx] = lists[idx].next;
        }
        return result;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode list = null;
        ListNode result = null;
        while(l1 != null && l2 != null){
            if(l1.val >= l2.val){
                if(list == null){
                    list = new ListNode(l2.val);
                    result = list;
                    l2 = l2.next;
                }else{
                    ListNode ll = new ListNode(l2.val);
                    list.next = ll;
                    list = list.next;
                    l2 = l2.next;
                }
            }else{
                if(list == null){
                    list = new ListNode(l1.val);
                    result = list;
                    l1 = l1.next;
                }else{
                    ListNode ll = new ListNode(l1.val);
                    list.next = ll;
                    list = list.next;
                    l1 = l1.next;
                }
            }
        }
        while(l1 != null){
            list.next = l1;
            l1 = l1.next;
            list = list.next;
        }
        while(l2 != null){
            list.next = l2;
            l2 = l2.next;
            list = list.next;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {-1,2,4, 8, 9};
        int[] arr1 = {0, 2, 3, 4, 10};
        int[] arr2 = {1, 3, 5, 8, 9, 22};
        int i = 0;
        ListNode node = new ListNode(-5);
        ListNode nn1 = node;
        ListNode node1 = new ListNode(-4);
        ListNode nn2 = node1;
        ListNode node2 = new ListNode(-3);
        ListNode nn3 = node2;
        while(i < arr.length){
            ListNode n = new ListNode(arr[i++]);
            node.next = n;
            node = node.next;
        }
        int j = 0;
        while(j < arr1.length){
            ListNode n = new ListNode(arr1[j++]);
            node1.next = n;
            node1 = node1.next;
        }
        int k = 0;
        while(k < arr2.length){
            ListNode n = new ListNode(arr2[k++]);
            node2.next = n;
            node2 = node2.next;
        }
        MergeList ml = new MergeList();
        ListNode list = ml.mergeTwoLists(nn1, nn2);
        while(list != null){
            System.out.print(list.val + "\t");
            list = list.next;
        }
        System.out.println();
        ListNode[] narr = new ListNode[3];
        narr[0] = nn1;
        narr[1] = nn2;
        narr[2] = nn3;
        ListNode list1 = ml.mergeKLists(narr);
        while(list1 != null){
            System.out.print(list1.val + "\t");
            list1 = list1.next;
        }
    }
}
