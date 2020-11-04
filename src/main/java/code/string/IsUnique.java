package code.string;

import java.util.HashSet;
import java.util.Set;

/**
 * @author JillW
 * @date 2020/09/30
 * 字符是否重复
 * <p>
 * String.indexOf
 * <p>
 * 1.判断字符串是否为空 !!!!这里就不重复写了
 * if (StrUtil.isNullOrUndefined(str)){
 * log.error("string.null.or.undefined");
 * }
 * 字符串的字符范围
 * 如果是26个字母(固定大写或者小写)组成的 长度只要>26就一定重复 其他的以此类推
 * 如果是ASCII字符集 128种可能 16个字节的位运算
 * 如果是unicode 没有字符范围 就有很多方法了
 */
public class IsUnique {
    private static final Integer ASCII_NUMBER = 128;
    private static final Integer LETTERS = 52;

    /**
     * ASCII码
     * java自己的char与int的转换
     *
     * @param str 字符串
     * @return 是否相同
     */
    public static boolean isUnique1(String str) {
        //36.6

        if (str.length() > ASCII_NUMBER) {
            return false;
        }
        int[] arr = new int[128];
        for (int i = 0; i < str.length(); i++) {
            //把字符和数组关联
            if (arr[str.charAt(i)] != 0) {
                return false;
            }
            arr[str.charAt(i)]++;
        }
        return true;
    }

    /**
     * 26英文字母,大小写
     * 位运算 Long
     *
     * @param str 字符串
     * @return 是否相同
     */
    public static boolean isUnique2(String str) {
        //36.3
        if (str.length() > LETTERS) {
            return false;
        }
        long bits = 0;
        int size = str.length();
        for (int i = 0; i < size; i++) {
            int move = str.charAt(i) - 'A';
            //有重复的，直接返回false
            //标记当前位置有这个字符
            if ((bits & (1L << move)) != 0) {
                return false;
            } else {
                bits |= (1L << move);
            }
        }
        return true;
    }

    /**
     * 两层循环判断
     *
     * @param str 字符串
     * @return 是否相同
     */
    public static boolean isUnique3(String str) {
        //36.4
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 用Set的特性
     *
     * @param str 字符串
     * @return 是否相同
     */
    public static boolean isUnique4(String str) {
        //36.8
        Set<Character> set = new HashSet<>();
        //如果有重复的直接返回false
        for (int i = 0; i < str.length(); i++) {
            if (!set.add(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * String.indexOf
     *
     * @param str 字符串
     * @return 是否相同
     */
    public static boolean isUnique5(String str) {
        //36.3
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //查看后面是否有当前字符
            if (str.indexOf(c, i + 1) != -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * String.lastIndexOf
     *
     * @param str 字符串
     * @return 返回
     */
    public static boolean isUnique6(String str) {
        //36.5
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //判断当前字符从前面和后面索引是否相同，如果不相同，
            //说明有重复的字符，直接返回false
            if (i != str.lastIndexOf(c)) {
                return false;
            }
        }
        return true;
    }


    public static boolean isUnique7(String str) {
        //实测很慢
        return str.chars().distinct().count() == str.length();
    }
}
