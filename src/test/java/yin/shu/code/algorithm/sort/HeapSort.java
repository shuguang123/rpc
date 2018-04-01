package yin.shu.code.algorithm.sort;

import org.junit.Test;

/**
 * 堆排序
 *
 * @author Aurora
 * @create 2018-03-17 19:33
 **/
public class HeapSort {

    /**
     * 堆排序
     *
     * @param A
     * @param N
     */
    void heapSort(int A[] ,int N){

        /*调整堆*/
        for(int i= N/2 -1; i>=0;i--){

            //从第一个非叶子节点,从下至上，从左至右调整
            adjustHeap(A,i,N);
        }

        printArray(A, N);

        //调整结构，交换堆顶和堆尾部结构
        for(int k = N-1; k > 0; k--){
            swap(A,0,k);
            adjustHeap(A,0,k);
            printArray(A, N);
        }
        printArray(A, N);
    }

    /**
     * 调整大顶堆
     *
     * @param A  数组
     * @param k  当前的父节点
     * @param N   数组长度
     */
    void adjustHeap(int[] A,int k,int N){

        int temp = A[k];
        for(int i = 2*k +1 ;i < N; i = i*2+1){
            if(i+1 < N && A[i] < A[i+1]){
                i = i+1;
            }
            //子节点值大于父节点的值
            if(A[i] > temp){
                A[k] = A[i];
                k = i;
            }else {
                break;
            }
        }
        A[k]= temp;   //将temp放到最终的位置
    }

    /**
     * 交换数组中两个位置的值
     * @param A
     * @param a
     * @param b
     */
    void swap(int[] A,int a,int b){
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    /**
     * 打印数组
     * @param A
     * @param N
     */
    void printArray(int[] A,int N){
        for(int i =0;i<N;i++){
            System.out.print(A[i]+"\t");
        }
        System.out.println();
    }


    @Test
    public void test() {
        int[] A = {3,2,9,8,10,4,5,1,7,6,15,14,17};
        printArray(A,A.length);
        heapSort(A,A.length);
    }
}
