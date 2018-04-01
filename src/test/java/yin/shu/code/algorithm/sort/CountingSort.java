package yin.shu.code.algorithm.sort;

import yin.shu.code.algorithm.utils.PrintUtil;
import yin.shu.code.algorithm.utils.SortUtil;

/**
 * 计数排序(数组元素 值 大于等于0)
 * 时间复杂度:
 * O(n+k)
 * 思想:
 *      假设排序数组是A[1...N],定义两个数组B[1....N],C[0...k];
 *      其中 k 为数组 A 中最大值
 *      let C[0,k]
 *      for i to k
 *          c[i] = 0
 *
 *      for j = 1 to N
 *          C[A[j]] = C[A[j]] +1  //将数组A 每个元素出现的次数存入C中
 *
 *      //将小于A中每个元素的个数存入 C 中
 *      for i = 1 to k
 *          C[i] = C[i-1] + C[i]
 *
 *     for j= N  down to 1
 *     B[C[A[j]]] = A[j]
 *     C[A[j]] = C[A[j]] - 1
 * @author Aurora
 * @create 2018-03-31 19:22
 **/
public class CountingSort {

    /**
     * 计数排序
     * @param A
     */
    public static int[] countingSort(int[] A){
        int[] B= new int[A.length];
        int max = SortUtil.getMaxnum(A);
        int[] C = new int[max+1];
        //将 数组 C元素初始值设为0
        for(int i = 0; i< C.length; i++){
            C[i] =0;
        }

        //将数组A 中每个元素值 放入 C中
        for(int j = 0; j <A.length;j++ ){
            C[A[j]] = C[A[j]] + 1;
        }

        //数组C中存放所有小于等于 A[i] 个数
        for(int k = 1; k< C.length; k++){
            C[k] = C[k] + C[k-1];
        }

        for( int m = A.length -1 ; m >= 0; m--){
            B[C[A[m]]-1] = A[m];
            C[A[m]] -=1;
        }

        return B;
    }

    public static void main(String[] args) {
        int[] A = {5,88,6,3,9,10,1};
        PrintUtil.printArray(A,A.length);
        int[] B = countingSort(A);
        PrintUtil.printArray(B,B.length);

        int[] temp = SortUtil.getMaxAndMinNum(A);
        System.out.println((temp[0] + "  "+ temp[1]));
    }
}
