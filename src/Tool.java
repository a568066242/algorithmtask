import java.util.Arrays;
import java.util.Random;

/**
 * Created by zhongjian on 2016/11/21.
 */
public class Tool {

    public static Random random = new Random();

    /**
     * 自动生成数组
     *
     * @param size    数组大小
     * @param minimun 数组中最小值(包括 )
     * @param maximum 数组中最大值(不包括 )
     * @return
     */
    public static int[] randomArray(int size, int minimun, int maximum) {
        if (maximum < minimun) {
            System.err.println("error: maximun < minumum");
            return null;
        }

        int[] A = new int[size];
        for (int i = 0; i < size; i++) {
            A[i] = random.nextInt(maximum - minimun) + minimun;
        }
        return A;

    }

    /**
     * 将A中下标i与下标j对应的元素互换
     * @param A
     * @param i
     * @param j
     */
    public static void interChange(int[] A, int i, int j) {
        int v = A[i];
        A[i] = A[j];
        A[j] = v;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(randomArray(100, 10, 20)));


    }
}
