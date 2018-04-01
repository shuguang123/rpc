package yin.shu.code.algorithm.sort;

/**
 * MergeSort 归并排序
 *
 * @author Aurora
 * @create 2018-03-11 21:23
 **/
public class MergeSort {
    public static void main(String[] args) {
        int[] a = {2,5,6,13,15};
        int[] b = {1,3,6,9,14};
        int[] c = new int[10] ;
//        mergeArray(a,b,5,5,c);

        int[] unsort = {2,4,3,7,5,9,6,12,11};
//        int[] unsort = {2,5,3};
        mergeSort(unsort,9);
//        System.out.println(c.length);
    }

    /**
     * 合并两个已经排序的数组 a 和 b 到 c 中
     * @param a  已经排序的数组a
     * @param b  已经排序的数组b
     * @param n  数组 a 的长度
     * @param m  数组 b  的长度
     * @param c  数组 c
     */
    static void  mergeArray(int[] a, int[] b, int n ,int m,int c[]){

        printArray(a);
        printArray(b);
        int i ,j ,k;
        i = j = k = 0;
        while (i<n && j < m){
            if(a[i] < b[j]){
                c[k++] = a[i++];
            }else {
                c[k++] = b[j++];
            }
        }

        //处理 数组 a 或者 数组 b 中存在 剩余数，复制到 c中
        while (i < n){
            c[k++] = a[i++];
        }
        while (j < n){
            c[k++] = b[j++];
        }
        printArray(c);
    }

    /**
     * 归并排序
     * @param  sortArray 待排序数组
     * @param length 数组长度
     */
    static void mergeSort(int[] sortArray,int length ){
       mSort(sortArray,new int[length],0,length -1);

    }

    /**
     * 递归 排序数组
     *
     * @param sortArray 带排序数组
     * @param left 数组 的起始位置
     * @param right 数组的 终止为止
     */
    static void mSort(int[] sortArray ,int[] temp,int left ,int right){

        if(left < right){
            int center = (left + right) /2;
            mSort(sortArray,temp,left,center);
            mSort(sortArray,temp,center+1,right);
            mergeArray(sortArray,temp,left,center+1,right);
        }
        printArray(sortArray);
    }

    /**
     * 合并排序sortArray
     * @param a  带排序的数组
     * @param leftPos
     * @param rightPos
     * @param rightEnd
     */
    private static void mergeArray(int[] a,int[] temp,int leftPos,int rightPos,int rightEnd) {

        int leftEnd = rightPos -1;
        int temPos = leftPos;
        int numEle = rightEnd - leftPos + 1; //元素个数

        while(leftPos <= leftEnd && rightPos <=rightEnd ){
            if(a[leftPos] <= a[rightPos]){
                temp[temPos++] = a[leftPos++];
            }else {
                temp[temPos++] = a[rightPos++];
            }
        }

        while(leftPos <= leftEnd){
            temp[temPos++] = a[leftPos++];
        }

        while (rightPos <= rightEnd){
            temp[temPos++] = a[rightPos++];
        }

        //把 数组 temp 覆盖到原数组中
        for(int m = 0;m<numEle;m++){
            a[rightEnd] = temp[rightEnd];
            rightEnd--;
        }
    }


    static void printArray(int temp[]){
        for(int i= 0;i < temp.length;i++){
            System.out.print(temp[i] +"\t");
        }
        System.out.println();
    }
}

