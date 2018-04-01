package yin.shu.code.algorithm.queue;

import org.junit.Test;
import yin.shu.code.algorithm.utils.PrintUtil;

/**
 * @author Aurora
 * @create 2018-03-27 15:03
 **/
public class QueueTest{

    //测试
    @Test
    public void test(){
        int[] A ={1,3,4,7,10,2,5};
        PrintUtil.printArray(A,A.length);
        System.out.println("初始化数组===========");

        TPriorityQueue queue = new TPriorityQueue(A);
        int[] temp = queue.getTQueue();
        int heapSize = queue.getHeapSize();
        System.out.println(heapSize);
        PrintUtil.printArray(temp,heapSize+1);

        System.out.println("获取最大值数组===============");
        int max = queue.maximum();
        System.out.println("max: "+ max);
        temp = queue.getTQueue();
        heapSize = queue.getHeapSize();
        System.out.println("heapSize: "+ heapSize);
        PrintUtil.printArray(temp,heapSize+1);

        System.out.println("增大队列中一个数据的值===============");
        queue.increase_key(5,6);
        PrintUtil.printArray(queue.getTQueue(),queue.getHeapSize()+1);

        System.out.println("在队列中插入一个数据的值===============");
        queue.insert(90);
        PrintUtil.printArray(queue.getTQueue(),queue.getHeapSize()+1);
    }
}