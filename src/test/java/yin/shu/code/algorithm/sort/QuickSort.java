package yin.shu.code.algorithm.sort;

import yin.shu.code.algorithm.utils.PrintUtil;
import yin.shu.code.algorithm.utils.SortUtil;

/**
 * 快速排序
 *
 * @author Aurora
 * @create 2018-03-31 17:42
 **/
public class QuickSort {


    /**
     * 快速排序
     * O(NlogN)
     * 思想:
     *
     * quickSort(A,p,r)
     *  if(p < r)
     *      q = partition (A,p,r);
     *      quickSort(A,p,q-1);
     *      quickSort(A,q,r);
     *
     * partition(A,p,r)
     *      i = p-1;
     *      x = A[r];
     *      for j=p to r-1
     *          if(A[j] <= x)
     *              i =i+1;
     *              exchange A[j] with A[i];
     *      exchange A[i] with A[r];
     *      return i+1;
     *
     * @param A
     */
    static void quickSort(int[] A,int p,int r){
        if(p < r){
            int q= partition(A,p,r);
            quickSort(A,p,q-1);
            quickSort(A,q,r);
        }
    }

    /**
     * 调整A[p]至A[r]之间 数的大小。
     * @param A
     * @param p
     * @param r
     * @return
     */
     static int partition(int[] A, int p, int r) {
        int i = p-1;
        int x = A[r];
        for(int j = p;j < r;j++){
            if(A[j] <= x ){
                i = i+1;
                SortUtil.swap(A,i,j);
            }
        }
        SortUtil.swap(A,i+1,r);
         return  i+1;
     }


    public static void main(String[] args) {
        int[] A = {5,2,6,3,9,10,1};
        PrintUtil.printArray(A,A.length);
        quickSort(A,0,A.length -1);
        PrintUtil.printArray(A,A.length);
    }
}
