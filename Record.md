
|       日期 |                题号 |       标签       |
|---------:|------------------:|:--------------:|
|  22.8.14 |          26, 剑指05 |    二分法，字符串     |
|  22.8.15 |           704， 35 |      二分法       |
|  22.8.22 |       34, 69, 367 |      二分法       |
|  22.8.23 |                27 |      二分法       |
|  22.8.24 |           283， 26 |      移除元素      |
|  22.8.25 |          844， 977 |      移除元素      |
| 22.10.14 |               209 |    长度最小子数组     |
| 22.10.18 |             *904* |      水果成篮      |
| 22.10.19 |              *76* |     最小覆盖子串     |
| 22.10.27 |            共计15道题 |      数组结束      |
| 22.10.27 |               203 |    链表，移除元素     |
| 22.10.29 |               707 |      设计链表      |
| 22.10.31 |           206, 24 | 翻转链表, 相交链表中的节点 |
|  22.11.1 |                19 |  删除链表的倒数第N个节点  |
| 22.11.14 |          160, 142 |   链表相交, 环形链表   |
| 22.11.17 | 242, 383, 49, 438 |      哈希表       |
| 22.11.17 |  349, 350, 202, 1 |      哈希表       |

| 23.1.30 |   |      哈希表       |





### 26 删除有序数组中的重复（二分法双指针）
```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int left = 0;
        for(int right = 1; right < nums.length; right++){
            if(nums[left] != nums[right]){//如果左右指针指向的数不同，此时才left++
                nums[++left] = nums[right];
            }
        }
        return left+1;
    }
}

class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int left = 0, right = 1;
        while (right < nums.length) {
            if(nums[left] != nums[right]){
                nums[left + 1] = nums[right];
                left++;
            }
            right++;
        }
        return left+1;
    }
}
```

### 剑指05 替换空格
```java
class Solution {
    public String replaceSpace(String s) {
        int length = s.length();
        char[] array = new char[length*3];
        int size =0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if(c == ' '){
                array[size++] = '%';
                array[size++] = '2';
                array[size++] = '0';
            }else {
                array[size++] = c;
            }
        }
        return new String(array, 0, size);
    }
}

class Solution {
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }
}
```


### 704 二分查找（二分法，双指针）
```java
class Solution {
    public int search(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length-1]) {
            return -1;
        }

        int left = 0, right = nums.length-1;
        while (left <= right) { //左闭右闭
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] > target) {
                right = mid - 1;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
```
```java
class Solution {
    public int search(int[] nums, int target) {
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0, right = nums.length; //左闭右开，所以需要注意right = nums.length 而不是right = nums.length-1
        while (left < right) {
            int mid = left + ((right -left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] > target) {
                right = mid;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }
}
```

### 35 搜索插入位置（二分法，双指针）
```java
//遍历查找
class Solution {
    public int searchInsert(int[] nums, int target) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i]>=target){
                return i;
            }
        }
        return nums.length;
    }
}
```
```java
//二分法查找 左闭右闭
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        if (target < nums[0]) {
            return 0;
        } else if (target > nums[right]) {
            return nums.length;
        }

        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid - 1;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return left; // left == right + 1
        //最后left == mid == right的时候,不管>还是<target,再次更新left/right的时候总会把target夹在中间
        //right, target, left 跳出while
        //得到插入位置left == right + 1
    }
}
```
```java
//左闭右开
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        if (target < nums[0]) {
            return 0;
        } else if (target > nums[right - 1]) {
            return nums.length;
        }

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] > target) {
                right = mid;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return right; // right == left
        //(left == mid, right) or (left, mid, target, right )
        //跳出循环的时候left == right, 且一定在target位置
    }
}
```

### 34 在排序数组中查找元素的第一个和最后一个位置(二分法)
```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return new int[]{-1, -1};
        }
        int left = searchLeftBound(nums, target);
        int right = searchRightBound(nums, target);
        if (left <= right) {
            return new int[]{left, right};
        }
        return new int[]{-1, -1};
    }

    public int searchRightBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + (right - left) / 2);
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }

    public int searchLeftBound(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + (right - left) / 2);
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right + 1;
    }
}
```
```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0 || target < nums[0] || target > nums[nums.length - 1]) {
            return new int[]{-1, -1};
        }

        int index = binarySearch(nums, target);
        if (index == -1) {
            return new int[]{-1, -1};
        }
        int left = index, right = index;

        while (left - 1 >= 0 && nums[left - 1] == target) {
            left--;
        }
        while (right + 1 <= nums.length - 1 && nums[right + 1] == target) {
            right++;
        }
        return new int[]{left, right};
    }

    public int binarySearch(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] > target) {
                right = mid - 1;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return  -1;
    }
}
```
### 69 x的平方根
```java
class Solution {
    //取X的一般做平方，每次将X除以2
    public int mySqrt(int x) {
        int left = 0, right = x, ans = -1;
        while (left <= right) {
            int mid = left + (right - left)/2;
            if ((long)mid * mid <= x) {
                ans = mid;//在<=mid的时候更新
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
```

### 367 有效的完全平方数
```java
class Solution {
    public boolean isPerfectSquare(int num) {
        int left = 0, right = num;
        while (left <= right) {
            int mid = left + (right - left)/2;
            long square = (long)mid * mid;
            if(square == num) {
                return true;
            }else if (square > num) {
                right = mid - 1;
            }else if (square < num) {
                left = mid + 1;
            }
        }
        return false;
    }
}
```
### 27 移除元素
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
}
```
```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int size = nums.length;
        int rightBoundIndex = size-1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == val){
                for(int j = rightBoundIndex; j > 0; j--){
                    if(nums[j] != val){
                        nums[i] = nums[j];
                        break;
                    }
                    rightBoundIndex--;
                }
                rightBoundIndex--;
                size--;
            }
        }
        return size;
    }
}
```

### 27 移动零
```java
class Solution {
//用原来的数组做新数组，遍历数组，当不为0的时候赋值在前面
    public void moveZeroes(int[] nums) {
        int s = 0, f = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[s++] = nums[i]; 
            }
        }
//遍历结束后把数组剩余的数字赋值为0
        for(int i = s; i < nums.length; i++){
            nums[i] = 0;
        }
    }
}
```
```java
class Solution {
    public void moveZeroes(int[] nums) {
        int slow = 0;
//遍历数组，当不为0的时候赋值在前面，为0的时候跳过
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                swap(nums, slow++, i);
            }
        }		
	}

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
```
### 844.比较含退格的字符串
```java
class Solution {
    public boolean backspaceCompare(String s, String t) {
        char[] S=s.toCharArray();
        char[] T=t.toCharArray();
        int curS=0;
        int curT=0;
        for(int i=0;i<S.length;i++){
            S[curS]=S[i];
            if(S[i]!='#'){
                curS++;
            }else{
                if(curS!=0){
                    curS--;
                }
                
            }
        }
        for(int i=0;i<T.length;i++){
            T[curT]=T[i];
            if(T[i]!='#'){
                curT++;
            }else{
                if(curT!=0){
                    curT--;
                }             
            }
        }
        if(curS!=curT){return false;}    //最后得到的数组大小不相同则肯定不符合
        if(curS==0){return true;}   //两个字符串都删除完
        for(int i=0;i<curS;i++){
            if(S[i]!=T[i]){
                return false;
            }
        }
        return true;
    }
}
```

### 977.有序数组的平方
```java
class Solution {
//两边是绝对值最大的，所以从两边开始判断
    public int[] sortedSquares(int[] nums) {
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] >= nums[right] * nums[right]) {
                result[index--] = nums[left] * nums[left];
                ++left;
            } else {
                result[index--] = nums[right] * nums[right];
                --right;
            }
        }
        return result;
    }
}
```

### 209.长度最小子数组
```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while(sum >= target){
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE? 0 : result;
    }
}
```

### 904.水果成篮
```java
class Solution {
    public int totalFruit(int[] tree) {
        if (tree == null || tree.length == 0) return 0;
        int n = tree.length;

        Map<Integer, Integer> map = new HashMap<>();
        int maxLen = 0, left = 0;
        for (int i = 0; i < n; i++) {
            map.put(tree[i], map.getOrDefault(tree[i], 0) + 1);  // 右边界
            while (map.size() > 2) {  // 不符合条件：水果种类大于2
                map.put(tree[left], map.get(tree[left]) - 1);
                    if (map.get(tree[left]) == 0) {
                        map.remove(tree[left]);
                    }
                left++;  // 左边界不断右移，直到map里面只有两个object
            }
            maxLen = Math.max(maxLen, i - left + 1); // 更新结果
        }
        return maxLen;
    }
}
```
```java
class Solution {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        if(n < 2) return n;
        int ans = 2, left = 0, right = 0;
        int[] freq = new int[n];
        int count = 0;
        while (right < n) {
            freq[fruits[right]]++;
            if(freq[fruits[right]] == 1) count++;
            right++;
            while (count > 2) {
                freq[fruits[left]]--;
                if(freq[fruits[left]] == 0) count--;
                left++;
            }
            ans = Math.max(ans, right-left);
        }
        return ans;
    }
}
```

### 76. 最小覆盖子串
```java
class Solution {
    Map<Character, Integer> osi = new HashMap<>();//存储字符串t中每个字符和每个字符出现的次数
    Map<Character, Integer> cnt = new HashMap<>();//动态存储截取的字符串中每个字符包含的个数

    public String minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char a = t.charAt(i);
            osi.put(a, osi.getOrDefault(a, 0) + 1);
        }
        int sLen = s.length();
        int l = 0, r = -1;
        int ansL = -1, ansR = -1, len = Integer.MAX_VALUE;
        while (r < sLen) {
            ++r;
            if (r < sLen && osi.containsKey(s.charAt(r))){
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) +1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                // 判断当前开头字符是否是t中所含字符
                if (osi.containsKey(s.charAt(l))) {// 开始滑动窗口
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) -1);
                }
                ++l;
            }
        }
        return ansL == -1? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        for (Map.Entry<Character, Integer> entry : osi.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            if (cnt.getOrDefault(key, 0) < value) {
                return false;
            }
        }
        return true;
    }
}
```
### 203. 移除链表元素
```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
```
```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode cur = head;
        while (cur != null) {
            while (cur.next != null && cur.next.val == val) {
                cur.next = cur.next.next;
            }
            cur = cur.next;
        }
        return head;
    }
}
```

```java
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while (head !=null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }

        ListNode pre = head;
        ListNode cur = head.next;//为了避免空指针异常，先判断head不为空

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return head;
        
    }
}
```
### 707. 设计链表
```java
class MyLinkedList {
    int size;
    ListNode head;
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
    }

    public int get(int index) {
        if (index < 0 || index > size - 1) {
            return -1;
        }

        ListNode cur = head;
        for (int i = 0; i <= index ; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        if (index < 0) {
            index = 0;
        }
        size++;
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        ListNode toAdd = new ListNode(val);
        toAdd.next = pre.next;
        pre.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        if(index == 0) {
            head = head.next;
            return;
        }
        ListNode pre = head;
        for (int i = 0; i < index ; i++) {
            pre=pre.next;
        }
        pre.next = pre.next.next;
    }
}
```
```java
//双链表实现
class ListNode{
    int val;
    ListNode next,prev;
    ListNode(int val){
        this.val = val;
    }
}
class MyLinkedList {
    int size;
    ListNode head, tail;
    public MyLinkedList() {
        size = 0;
        head = new ListNode(0);
        this.tail = new ListNode(0);
        //这一步非常关键，否则在加入头结点的操作中会出现null.next的错误！！！
        head.next=tail;
        tail.prev=head;
    }

    public int get(int index) {
        if (index < 0 || index > size - 1) {
            return -1;
        }
        ListNode cur = head;

        if (index > size/2) {
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        }else {
            cur = tail;
            for (int i = 0; i < size - index; i++) {
                cur = cur.prev;
            }
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }

        if (index < 0) {
            index = 0;
        }
        size++;
        ListNode pre = head;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        ListNode toAdd = new ListNode(val);
        toAdd.prev = pre;
        pre.next.prev = toAdd;
        toAdd.next = pre.next;
        pre.next = toAdd;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        size--;
        if(index == 0) {
            head = head.next;
            return;
        }
        ListNode pre = head;
        for (int i = 0; i < index ; i++) {
            pre=pre.next;
        }
        pre.next.next.prev = pre;
        pre.next = pre.next.next;
    }
}
```

206. 反转链表
```java
//双指针
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp;
        while ( cur != null) {
            temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}
```
```java
// 递归
class Solution {
    public ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }
    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode temp = cur.next;
        cur.next = prev;
        return reverse(cur, temp);
    }
}
```

### 24. 相交链表中的节点
```java
//虚拟头节点
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyNode = new ListNode();
        dummyNode.next = head;
        ListNode prev = dummyNode;

        while (prev.next != null && prev.next.next != null) {
            ListNode temp = head.next.next; // 缓存 next
            prev.next = head.next;          // 将 prev 的 next 改为 head 的 next
            head.next.next = head;          // 将 head.next(prev.next) 的next，指向 head
            head.next = temp;               // 将head 的 next 接上缓存的temp
            prev = head;                    // 步进1位
            head = head.next;               // 步进1位
        }
        return dummyNode.next;
    }
}
```
```java
// 递归版本
class Solution {
    public ListNode swapPairs(ListNode head) {
        // base case 退出提交
        if(head == null || head.next == null) return head;
        // 获取当前节点的下一个节点
        ListNode next = head.next;
        // 进行递归
        ListNode newNode = swapPairs(next.next);
        // 这里进行交换
        next.next = head;
        head.next = newNode;

        return next;
    }
}
```

### 19.删除链表的倒数第n个元素
```java
//思路： slow fast双指针，相差n，当fast到达尾部时，slow指向
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode fastNode = dummyNode;
        ListNode slowNode = dummyNode;

        for (int i = 0; i < n; i++) {
            fastNode = fastNode.next;
            }
        while (fastNode.next != null) {
            fastNode = fastNode.next;
            slowNode = slowNode.next;
        }
        slowNode.next = slowNode.next.next;
        return dummyNode.next; //注意返回时要把头节点排除掉
    }
}
```

### 160. 相交链表
```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curA = headA;
        ListNode curB = headB;
        int lengthA = 0;
        int lengthB = 0;
        while (curA.next != null) {
            lengthA++;
            curA = curA.next;
        }
        while (curB.next != null) {
            lengthB++;
            curB = curB.next;
        }
        curA = headA; //记得初始化
        curB = headB;
        if (lengthB > lengthA) {
            int temp = lengthA;
            lengthA = lengthB;
            lengthB = temp;
            ListNode tempNode = curA;
            curA = curB;
            curB = tempNode;
        }
        int gap = lengthA - lengthB;
        while (gap-- > 0) {
            curA = curA.next;
        }
        System.out.println(curA.val);
            while (curA != null) {
            if (curA == curB) {
                return curA;
            }
            curA = curA.next;
            curB = curB.next;
        }
        return null;
    }
}
```

### 142. 环形链表
```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ListNode index1 = head;
                ListNode index2 = fast;
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
}
```
```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        boolean isLoop = false;
        if(head == null){ 
            return null;
            }
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                isLoop = true;
                break;
            }
        }
        if(isLoop){
            slow = head;
            while(slow != fast){
                slow = slow.next;
                fast = fast.next;
                }
            return slow;
        }
        return null;
    }
}
```
### 242. 有效的字母异位数 hashmap
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }
}
```
```java
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
```
### 383. 赎金信 hashmap
```java
class Solution {
    public boolean canConstruct(String t, String s) {
        if (s.length() < t.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            if (--table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }
}
```
### 49. 字母异位词分组
```java
//以下的两种方法分别使用排序和计数作为哈希表的键
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
```
```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuilder sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
```
### 438. 找到字符串中所有的字母异位词
```java
//滑动窗口
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();

        if (sLen < pLen) {
            return new ArrayList<Integer>();
        }

        List<Integer> ans = new ArrayList<Integer>();
        int[] sCount = new int[26];
        int[] pCount = new int[26];
        for (int i = 0; i < pLen; ++i) {
            ++sCount[s.charAt(i) - 'a'];
            ++pCount[p.charAt(i) - 'a'];
        }

        if (Arrays.equals(sCount, pCount)) {
            ans.add(0);
        }

        for (int i = 0; i < sLen - pLen; ++i) {
            --sCount[s.charAt(i) - 'a'];
            ++sCount[s.charAt(i + pLen) - 'a'];

            if (Arrays.equals(sCount, pCount)) {
                ans.add(i + 1);
            }
        }

        return ans;
    }
}
```
```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        if(p.length() > s.length()) {
            return  list; // Base Condition
        } 
       
            int N=s.length(); // Array1 of s
            int M=p.length(); // Array2 of p
            int[]count = freq(p); // intialize only 1 time
            
            int[]currentCount = freq(s.substring(0, M)); // freq function, update every-time according to sliding window
            
            if(areSame(count,currentCount)) // areSame function
                list.add(0);
        
            int i;
            for(i=M;i<N;i++){ // going from 3 to 9 in above example
                currentCount[s.charAt(i-M) - 'a']--; // blue pointer, decrement frequency
                currentCount[s.charAt(i)-'a']++; // red pointer, increment frequency
                if(areSame(count,currentCount)){ // now check, both array are same
                    list.add(i-M+1); // if we find similar add their index in our list
                }
            }
        return list;
    }
    private boolean areSame(int[] x, int[] y){
        for(int i = 0; i < 26; i++){
            if(x[i] != y[i]) // compare all the frequency & doesnn't find any di-similar frequency return true otherwise false
                return false;
        }
        
        return true;
    }
  private int[] freq(String s){
        int[] count = new int[26]; // create array of size 26
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i) - 'a']++; // update acc. to it's frequency
        }
        
        return count; // and return count 
    }
}
```
### 349. 两个数组的交集
```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();

        for (int i: nums1) {
            set1.add(i);
        }
        for (int i: nums2) {
            if (set1.contains(i)) {
                resSet.add(i);
            }
        }
        return resSet.stream().mapToInt(x -> x).toArray();
    }
}
```
### 350. 两个数组的交集2
```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        //put the first array in the map
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num: nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }

        int[] intersection = new int[nums1.length];
        int index = 0;
        for (int num: nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                intersection[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                }else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(intersection, 0, index);
    }
}
```

### 202. 快乐数
```java
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> record = new HashSet<>();
        while (n!= 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNumber(n);
        }
        return n == 1;
    }

    private int getNextNumber(int n) {
        int res = 0;
        while (n > 0) {
            int temp = n%10;
            res = res + temp*temp;
            n = n/10;
        }
        return res;
    }
}
```
### 1. 两数之和
```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> storeNums = new HashMap<>(nums.length, 1);
            for (int i = 0; i < nums.length; i++) {
                if(!storeNums.containsKey(target - nums[i])) {
                    storeNums.put(nums[i], i);
                }else {
                    result[0] = storeNums.get(target - nums[i]);
                    result[1] = i;
                }
            }

        return result;
    }
}
```
