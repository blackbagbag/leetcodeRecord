//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 1055 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
//滑动窗口
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list = new ArrayList<Integer>();
        if(p.length() > s.length()) return  list; // Base Condition

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

//leetcode submit region end(Prohibit modification and deletion)
