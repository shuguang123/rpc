package yin.shu.code.algorithm.utils;

/**
 * 排序工具类
 *
 * @author Aurora
 * @create 2018-03-31 18:00
 **/
public class SortUtil {
    /**
     * 交换数组中两个位置的值
     * @param A 数组A
     * @param a index
     * @param b index
     */
   public static void swap(int[] A,int a,int b){
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }

    /**
     * 获取数组中的最大值
     * @param A
     * @return
     */
    public static int getMaxnum(int[] A){

        int max =A[0];
        for(int i = 0; i< A.length;i++){
            if(A[i] > max){
                max = A[i];
            }
        }

        return max;
    }

    /**
     * 获取数组中的最小值
     * @param A
     * @return
     */
    public static int getMinnum(int[] A){

        int min =A[0];
        for(int i = 0; i< A.length;i++){
            if(A[i] < min){
                min = A[i];
            }
        }

        return min;
    }


    /**
     * 获取数组中最大和最小值
     * 时间复杂度O(3n/2)
     * @param A
     * @return
     */
    public static int[] getMaxAndMinNum(int[] A){
        int len = A.length;
        if(len < 1){
            throw  new RuntimeException("数组长度小2");
        }
        int[] reArray = new int[2];
        int max = 0;
        int min = 0;

        if(A[0] < A[1]){
            max = A[1];
            min = A[0];
        }else {
            max = A[0];
            min = A[1];
        }
        for( int i = 2; i< A.length;i =i+2){

            //数组长度为奇数的情形
            if(A.length == i+1){
                if(A[i] < min){
                    min = A[i];
                }
                if(A[i] > max){
                    max = A[i];
                }
                break ;
            }


            //先比较A[i] 与A[i+1]大小，然后比较 A[i] 与min 和 A[i+1] 和 max大小
            if(A[i] < A[i+1]){
                if((A[i]) < min){
                    min = A[i];
                }
                if(A[i+1] > max){
                    max = A[i+1];
                }
            } else {
                if((A[i]) > max){
                    max = A[i];
                }
                if(A[i+1] < min){
                    min = A[i+1];
                }
            }

        }
        A[0] = min;
        A[1] = max;
        return A;
    }
}
