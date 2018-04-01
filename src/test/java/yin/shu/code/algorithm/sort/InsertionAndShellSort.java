package yin.shu.code.algorithm.sort;

import org.junit.Test;

/**
 * 插入排序和希尔排序
 *
 * @author Aurora
 * @create 2018-03-14 19:57
 **/
public class InsertionAndShellSort {
    /**
     * 插入排序
     * @param A 带排序的数组
     * @param N 数组的长度
     */
    void insertSort(int[] A ,int N){
        int i , j, temp;
        for(i = 1;i < N;i++){
            temp = A[i];
            for(j = i;j > 0;j--){
                if(temp < A[j-1] ){
                    A[j] = A[j-1];
                }else {
                    break;
                }
            }
            if(i != j){
                A[j] = temp;
            }
        }
        printArray(A,N);
    }

    /**
     * 希尔排序
     * @param A
     * @param N
     */
    void shellSort(int[] A,int N){
        int i ,j ,increment,temp;
        //设置希尔排序的增量为N/2,N/4,N/8.....
        for(increment = N/2;increment >0;increment /=2){
            for(i= increment;i<N;i++){
                temp = A[i];
                for(j=i;j>=increment;j-=increment){
                    if(temp < A[j-increment]){
                        A[j] = A[j-increment];
                    }else {
                        break;
                    }
                }
                if(i!= j){
                    A[j] = temp;
                }
            }
        }
        printArray(A,N);
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
    public void insertSortTest(){
        int[] A = {5,2,6,3,9,10,1};
        printArray(A,A.length);
        insertSort(A,A.length);
    }

    @Test
    public void shellSort(){
        int[] A = {5,2,6,3,9,10,1};
        printArray(A,A.length);
        shellSort(A,A.length);
    }
}
