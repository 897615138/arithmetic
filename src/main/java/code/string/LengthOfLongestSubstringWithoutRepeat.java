package code.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JillW
 * @date 2020/09/30
 * 无重复字符的最长子串
 * 时间复杂度：O(N)，其中 NN 是字符串的长度。左指针和右指针分别会遍历整个字符串一次。
 * <p>
 * 空间复杂度：O(∣Σ∣)，其中 Σ 表示字符集（即字符串中可以出现的字符），∣Σ∣ 表示字符集的大小。在本题中没有明确说明字符集，因此可以默认为所有 ASCII 码在 [0, 128)[0,128) 内的字符，即
 * |\Sigma| = 128∣Σ∣=128。我们需要用到哈希集合来存储出现过的字符，而字符最多有 |\Sigma|∣Σ∣ 个，因此空间复杂度为 O(|\Sigma|)O(∣Σ∣)。
 */
public class LengthOfLongestSubstringWithoutRepeat {
    private static final String TEST="hello1world";
    private static int lengthOfLongestSubstringWithoutRepeat() {
        // 哈希集合HashSet，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = TEST.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            // 左指针向右移动一格，移除一个字符
            if (i != 0) {
                occ.remove(TEST.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(TEST.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(TEST.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串长度
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int i = lengthOfLongestSubstringWithoutRepeat();
        System.out.println(i);
    }
}
