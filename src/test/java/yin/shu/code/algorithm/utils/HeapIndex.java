package yin.shu.code.algorithm.utils;

/**
 * 堆获取父子节点的Index
 *
 * @author Aurora
 * @create 2018-03-28 21:52
 **/
public class HeapIndex {
    /**
     * 返回父节点在数组的下标
     * @param i
     * @return
     */
   public static int parent(int i){
        return (i-1)/2;
    }

    /**
     * 返回左节点在数组中的下标
     * @param i
     * @return
     */
   public static int left(int i){
        return 2*i+1;
    }

    /**
     * 返回又子节点在数组中的下标
     * @param i
     * @return
     */
    public static int right(int i){
        return 2*i +2;
    }
}
