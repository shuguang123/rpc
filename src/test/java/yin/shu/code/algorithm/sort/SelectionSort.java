package yin.shu.code.algorithm.sort;

import org.junit.Test;

/**
 * 选择排序
 *
 * @author Aurora
 * @create 2018-03-14 21:07
 **/
public class SelectionSort {

    /**
     * 选择排序
     *
     * @param A
     * @param N
     */
    void selectSort(int[] A,int N) {
        int i , j, min, temp;
        for(i = 0; i< N;i++){
            min = i;
            for(j = i+1;j< N; j++){
                if(A[j] < A[min]){
                    min = j;
                }
            }

            //交换 A[i] 和 A[min]
            if(A[i] != A[min]){
                temp = A[i];
                A[i] = A[min];
                A[min] = temp;
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
        selectSort(A,A.length);
    }
}
