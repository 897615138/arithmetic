package code.recursion;

/**
 * 约瑟夫环
 * 已知 n 个人（以编号1，2，3…n分别表示）围坐在一张圆桌周围。
 * 从编号为 k 的人开始报数，数到 m 的那个人出圈；
 * 他的下一个人又从 1 开始报数，数到 m 的那个人又出圈；
 * 依此规律重复下去，直到剩余最后一个胜利者。
 *
 * @author Jill W
 * @date 2020/11/04
 */
public class Josephus {
    /**
     * 方法一：找规律
     * 一个人参加游戏的时候是1号胜出 初始序号为0输出为1
     * 就看作为序号为0~numberOfPeople-1的人参与报数
     * 共numberOfPeople人
     * 0 1 2 3 …… k-1 k k+1 …… n-2 n-1    n个人
     * 0 1 2 3 ……     k k+1 …… n-2 n-1    n-1个人 第k个人退出
     * 那么下面是按照原本第k+1个人开始排序，也就是
     * k k+1 …… n-2      n-1     0       1       2       ……  k-3 k-2
     * 然后再重新调整一下
     * 0 1   …… n-k+2    n-k+1   n-k     n-k+1   n-k+2   ……  n-3 n-2
     * 假设调整前的序号X调整后的序号x
     * 之间的关系是 X=(x+k)%n n-1个人的时候
     * 那么反过来 最后胜者为0号 1个人
     * 两个人的时候X=(0+k)%(1+1)
     * for (int i = 1; i <= numberOfPeople;i++)
     * {
     * numberOfWinner = (numberOfWinner + numberOfRule) % i;
     * }
     * 最后结果+1
     *
     * @param numberOfPeople 参与游戏的人数
     * @param numberOfRule   报数报到几退出
     * @return 出局的人号码
     */
    protected static Integer solution1(int numberOfPeople, int numberOfRule) {
        if (numberOfPeople == 1) {
            return 0;
        }
        int i = (solution1(numberOfPeople - 1, numberOfRule) + numberOfRule) % numberOfPeople;
        System.out.println("序号" + i);
        return i;
    }

    /**
     * 从i开始排序的solution1的算法
     *
     * @param numberOfPeople 人数
     * @param numberOfRule   规则
     * @param indexStart     开始下标
     * @return 获胜
     */
    protected static Integer solution2(int numberOfPeople, int numberOfRule, int indexStart) {
        Integer integer = solution1(numberOfPeople, numberOfRule);
        return integer + indexStart;
    }

    /**
     * 方法二 列数组+flag
     *
     * @param numberOfPeople 参与的人数
     * @param numberOfRule   规则
     */
    public static Integer solution3(int numberOfPeople, int numberOfRule) {
        int i;
        //point为指针 实际编号为point+1的值 默认第一个人已经报数
        int point = 0;
        int count = 0;
        //int 默认为0
        int[] game = new int[numberOfPeople];
        //经过(n-1)*n次报数后剩下最后一人
        for (i = 0; i < (numberOfPeople - 1) * numberOfRule; i++) {
            //如果不在游戏中就看下一个人是不是游戏中直到找到参与者
            while (game[point] > 0) {
                point = (point > numberOfPeople - 2) ? 0
                        : point + 1;
            }
            //报数，下一个人
            count += 1;
            //如果报到3 退出
            if (count == numberOfRule) {
                game[point] = 1;
                count = 0;
            }
            //指向下一个人
            point = (point > numberOfPeople - 2) ? 0
                    : point + 1;
        }
        //找到最后的人
        while (game[point] > 0) {
            point = (point > numberOfPeople - 2) ? 0 : point + 1;
        }
        return point + 1;
    }

    public static void main(String[] args) {
        //方法一
        System.out.println(solution1(5, 3));
        System.out.println(solution2(6, 2, 0));
        //方法二
        System.out.println(solution3(5, 3));
    }
}
