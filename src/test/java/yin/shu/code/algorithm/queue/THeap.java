package yin.shu.code.algorithm.queue;

import org.junit.Test;
import yin.shu.code.algorithm.utils.PrintUtil;

/**
 * 大堆排序步骤
 *
 * @author Aurora
 * @create 2018-03-27 11:06
 **/
public class THeap {

    public Index index = new Index();
    /**
     * 获取节点在数组中的下标
     */
    class Index{
        /**
         * 返回父节点在数组的下标
         * @param i
         * @return
         */
        int Parent(int i){
            return (i-1)/2;
        }

        /**
         * 返回左节点在数组中的下标
         * @param i
         * @return
         */
        int Left(int i){
            return 2*i+1;
        }

        /**
         * 返回又子节点在数组中的下标
         * @param i
         * @return
         */
        int Right(int i){
            return 2*i +2;
        }

    }


    /**
     * 维护最大堆
     * @param A
     * @param i
     */
    void max_heapify(int[] A,int heapSize,int i){
        int largest;
        int l = index.Left(i);
        int r = index.Right(i);

        if(l <= heapSize && A[l] > A[i]){
            largest = l;
        }else {
            largest = i;
        }

        if(r <= heapSize && A[r] > A[largest]){
            largest = r;
        }

        if( largest != i){
            swap(A,i,largest);
            max_heapify(A,heapSize,largest);
        }
    }

    /**
     * 建立最大堆
     * @param A
     */
    void build_max_heap(int[] A){
        int heapSize = A.length -1;  //堆数组下标最大值
        int len = A.length;
        int i;
        for(i = len/2 -1;i>=0;i--){
            max_heapify(A,heapSize,i);
        }
    }

    /**
     * 堆排序 ：
     * 1.build_max_heap 将数组构造成最大堆
     * 2.交换A[0] 与 A[n]
     * 3.从堆中去掉A[n]
     * 4.构造数组A[1,2,3,....,n-1]的最大堆
     * 5.max_heapify(A,heapSize-1,1); 维护堆操作
     * 6.A[1] = A[heapSize] 交换堆中第一个数和最后一个数
     * 7.重复 5 和6
     * 8.获得排序后的数组
     * @param A
     */
    void heapSort(int[] A){
        //构造最大堆
        build_max_heap(A);

        //堆的大小
        int heapSize = A.length -1;

        //数组的长度
        int len = A.length;

        for(int i= len - 1;i>= 1;i-- ){
            swap(A,0,i);
            heapSize -= 1 ;
            max_heapify(A,heapSize-1,0);
        }
    }

    /**
     * 交换数组中两个值
     * @param A
     * @param i
     * @param largest
     */
   void swap(int[] A, int i, int largest) {
        int temp = A[i];
        A[i]= A[largest];
        A[largest] = temp;
    }


    public static void main(String[] args) {
/*        int i = 1;
        i = i<<100;
        System.out.println(i);*/

    }

    @Test
    public void  test(){
        int[] A ={1,3,4,7,10,2,5};
        PrintUtil.printArray(A,A.length);
        System.out.println("===========");
        heapSort(A);
        PrintUtil.printArray(A,A.length);
    }
}
