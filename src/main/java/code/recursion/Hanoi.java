package code.recursion;

/**
 * 汉诺塔
 *
 * @author Jill W
 * @date 2020/11/04
 */
public class Hanoi {

    /**
     * 用递推的思想，n层诺塔的移动实际上是n-1层汉诺塔移动好之后
     * *         三个柱子分别是 1 n-1 0个
     * *         A上N-1全部移动到B 1 n-1 0
     * *         再A->C 0 n-1 1
     * *         B->C
     * *         因为A柱子上的盘最大可视空
     *
     * @param layerNumber 层数
     * @param a           A柱
     * @param b           B柱
     * @param c           C柱
     */
    public void hanoi(int layerNumber, char a, char b, char c) {
        if (layerNumber == 1) {
            //只有一层的时候只移动一次
            move(a, c);
        } else {//把A上的n-1移动到B上
            hanoi(layerNumber - 1, a, c, b);
            //把A上的一个移动到C
            move(a, c);
            //把B上的n-1移动到C
            hanoi(layerNumber - 1, b, a, c);
        }
    }

    /**
     * 移动过程显示
     *
     * @param origin      原来所在柱子
     * @param destination 要转移到的柱子
     */
    public void move(char origin, char destination) {

        System.out.println("Direction:" + origin + "--->" + destination);
    }

    public void start() {
        int layersOfHanoi = 5;
        int times = (int) Math.pow(2.0, layersOfHanoi) - 1;
        System.out.println("需要移动" + times + "次");
        hanoi(layersOfHanoi, 'A', 'B', 'C');
    }

}
