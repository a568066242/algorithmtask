import java.util.Arrays;

/**
 * Created by zhongjian on 2016/11/21.
 * 选择问题
 */
public class Select {

    public static int TYPE_SELECTK = 1;
    public static int TYPE_SELECTK2 = 2;

    /**
     * 从数组A中选择第k小的元素（k>=1 and k<A.length）
     *
     * @param A 数组A
     * @param k 第k小
     * @return 第k小元素下标(0开始) ,即返回k-1
     */
    public static int selectK(int[] A, int k) {
        int low = 0;
        int high = A.length;
        int j;
        while (low < high) {
            j = QuickSort.partition(A, low, high);
            if (j == k - 1) {
                return j;
            } else if (j > k - 1) {
                high = j;
            } else {
                low = j + 1;
            }
        }
        return -1;
    }

    /**
     * 改进的选择算法。从low到high中找第k小的元素
     *
     * @param A    数组A
     * @param k    第k小
     * @param high 高位
     * @param low  低位
     * @param r    每组至多r个元素（>=1）
     * @return 第k小元素下标(0开始) ,即返回k-1
     */
    public static int selectK2(int[] A, int k, int low, int high, int r) {
        if ((high - low + 1) <= r) { //直接找
            InsertionSort.insertionSort(A, low, high);
            return low + k - 1;
        }

        while (true) {
            int n = high - low + 1;//元素数
            int group = n / r;//分组数
            int j = 0;
            if (group != 0) {
                for (int i = 0; i < group; i++) {
                    InsertionSort.insertionSort(A, low + i * r, low + (i + 1) * r - 1); //对每一组进行排序
                    Tool.interChange(A, low + i, low + i * r + r / 2 - 1); //将每一组 {i} 的中间元素放在 {low+i} 位置上，即收集到low到high的前部
                }
                j = selectK2(A, group / 2 + 1, low, low + group - 1, r);//求这些中间元素的中间值mm下标
            } else {//不可分组，直接调用
                j = selectK2(A, k, low, high, r);
            }
            Tool.interChange(A, low, j);//将mm放到low位置上
            j = high + 1;
            j = QuickSort.partition(A, low, j);//用mm进行划分
            if (j - low + 1 == k) {
                return j;
            } else if (j - low + 1 > k) {
//            return selectK2(A, k, low, j - 1, r);
                high = j - 1;
            } else {
//            return selectK2(A, k - (j - low + 1), j + 1, high, r);
                k = k - (j - low + 1);
                low = j + 1;
            }
        }
    }

    public static void run(int[] A, int k, int type, int r) {
        long start = System.currentTimeMillis();
        int x = 0;
        if (type == 1) {
            x = selectK(A, k);
        } else {
            x = selectK2(A, k, 0, A.length - 1, r);
        }
        System.out.println("use time:" + (System.currentTimeMillis() - start) + "ms");
    }


    public static void main(String[] args) {
        int[] A = Tool.randomArray(10000000, -10000000, 10000000);
        run(A, 10000000, Select.TYPE_SELECTK2, 20);
        run(new int[]{-111,8,1,3,6,7,-10,100,99,-100},10,Select.TYPE_SELECTK,0);
    }
}
