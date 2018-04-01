package yin.shu.code.algorithm.divide;

import org.junit.Test;

/**
 * 求最大子序列
 *
 * @author Aurora
 * @create 2018-03-19 21:51
 **/
public class MaxSubSum {

    /**
     * 算出每个子序列的和，即算出序列中第i个到第j个数的和(j>=i)，并进行比较
     * 算法:O(N^3)
     * @param A 数组
     * @param N 长度
     * @return 最大子序列和
     */
    int getMaxCount(int[] A,int N){
        int i , j ,k , sum ,maxSum;
        maxSum =0;
        for(i = 0;i < N; i++ ){
            for(j =0; j < N; j++ ){
                sum =0;
                for(k =i;k <=j;k++){
                    sum += A[k];
                }
                if(sum > maxSum){
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }


    /**
     * 对 上边算法的改进 。避免重复循环
     * 算法:O(N^2)
     * @param A 数组A
     * @param N 数组的长度
     * @return
     */
    int getMaxCount_2(int[] A,int N){
        int i , j , sum ,maxSum;
        maxSum =0;
        for( i =0;i < N;i++){
            sum = 0;
            for(j = 0; j< N;j++){
                sum+=A[j];
            }
            if( sum > maxSum ){
                maxSum = sum;
            }
        }
        return maxSum;
    }

    /**
     * 把问题分成两个大致相等的子问题，然后递归地对它们求解，
     * 这是“分”的部分。“治”阶段将两个子问题的解修补到一起并可能
     * 再做些少量的附加工作，最后得到整个问题的解。
     * 算法：O(NlgN)
     * @param A 数组
     * @param left 数组左下标
     * @param right 数组右下标
     * @return
     */
    int getMaxCount_3(int[] A,int left ,int right){

        //只有一个数的时候
        if(left == right){
            if(A[left] > 0){
               return  A[left];
            }else {
                return 0;
            }
        }

        int center = (left + right) /2;

        //左子序列最大
        int maxLeftSum = getMaxCount_3(A,left,center);

        //右子序列最大
        int maxRightSum = getMaxCount_3(A,center+1,right);

        //求包含center 的最大左子序列
        int maxleftBorderSum = 0;
        int leftBorderSum = 0;
        for(int k = center; k>=left; k--){
            leftBorderSum +=A[k];
            if(maxleftBorderSum < leftBorderSum){
                maxleftBorderSum = leftBorderSum;
            }
        }

        //求包含center+1 的最大右子序列
        int maxRightBorderSum = 0;
        int rightBoderSum = 0;
        for(int l = center +1;l<=right;l++){
            rightBoderSum += A[l];
            if(maxRightBorderSum < rightBoderSum){
                maxRightBorderSum = rightBoderSum;
            }
        }

        int maxBorderSum =  maxRightBorderSum +maxleftBorderSum;

        //返回 maxLeftSum maxRightSum 和 maxBorderSum 最大的值
        return maxLeftSum > maxRightSum ?
                (maxLeftSum > maxBorderSum ? maxLeftSum : maxBorderSum) :
                (maxRightSum > maxBorderSum ? maxRightSum : maxBorderSum);
    }

    /**
     * 算法思想：设a[i]为和最大序列的起点，则如果a[i]是负的，
     * 那么它不可能代表最优序列的起点，
     * 因为任何包含a[i]作为起点的子序列都可以通过a[i+1]作起点而得到改进。
     * 算法O(N)
     * @param A
     * @param N
     * @return
     */
    int getMaxCount_4(int[] A,int N){
        int i,sum=0 ,maxSum=0;
        for (i= 0;i<N;i++){
            sum +=A[i];
            if(sum > maxSum){
                maxSum = sum;
            }else if(sum < 0) {
                sum = 0;
            }
        }
        return  maxSum;
    }


    @Test
    public void  test(){
        int[] A = {2,-1,4,-3,6,7,5};
        int a = getMaxCount_4(A,A.length);
        System.out.println(a);
    }
}
