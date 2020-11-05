package code.recursion;

import constant.NumberConstant;

/**
 * 父亲将2520个橘子分给六个儿子，分完以后，父亲说：老大，将你
 * * 的橘子的1/8分给老二。老二拿到橘子以后连同原先的橘子的1/7分给
 * * 老三，老三拿到橘子以后连同原先的橘子的1/6分给老四，老四拿到
 * * 橘子以后连同原先橘子的1/5分给老五，老五拿到橘子以后连同
 * * 原先橘子的1/4分给老六，老六拿到橘子以后连同原先橘子的1/3分给
 * * 老大，结果大家手中的橘子一样多，问六兄弟原本手中有多少橘子？
 *
 * @author Jill W
 * @date 2020/11/05
 */
public class Orange {
    /**
     * orange_wj(240, 210, 1);
     *
     * @param origin        原本的橘子数
     * @param getOrange     获得的橘子树
     * @param brotherNumber 兄弟序号
     * @return 原本的橘子数
     */
    public static int orange(int origin, int getOrange, int brotherNumber) {
        if (brotherNumber > NumberConstant.six) {
            return 0;
        }
        // 老大比较特殊 要先给别人最后才能拿到
        if (brotherNumber == 1) {
            origin = 240;
            // 老大给老二的橘子数
            getOrange = origin / (9 - brotherNumber);
            System.out.println("第" + brotherNumber + "个人原来的橘子数为" + origin);
        } else {
            getOrange = (origin + getOrange) / (9 - brotherNumber);
        }
        int nextBrotherOrange = 0;
        nextBrotherOrange = 2520 / 6 * (8 - brotherNumber) / (7 - brotherNumber) - getOrange;
        System.out.println("第" + (brotherNumber + 1) + "个人原来的橘子数为" + nextBrotherOrange);
        brotherNumber++;
        return orange(nextBrotherOrange, getOrange, brotherNumber);
    }
}
