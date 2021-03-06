package yin.shu.code.algorithm.sort;

import org.junit.Test;

/**
 * 冒泡排序
 *
 * @author Aurora
 * @create 2018-03-14 21:17
 **/
public class BubbleSort {

    /**
     * 冒泡排序
     * @param A
     * @param N
     */
    void bubbleSort(int[] A,int N){
        int i,j,temp;
        for(i=0;i<N;i++){
            for(j = i+1;j< N;j++){
                if(A[i] > A[j]){
                    temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }
        }
        printArray(A,A.length);
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
    public void test(){
        int[] A = {5,2,6,3,9,10,1};
        printArray(A,A.length);
        bubbleSort(A,A.length);
    }
}
