package yin.shu.code.algorithm.utils;

/**
 * 打印工具
 *
 * @author Aurora
 * @create 2018-03-27 13:43
 **/
public class PrintUtil {
    /**
     * 打印数组
     * @param A
     * @param N
     */
    public static void printArray(int[] A,int N){
        for(int i =0;i<N;i++){
            System.out.print(A[i]+"\t");
        }
        System.out.println();
    }
}
