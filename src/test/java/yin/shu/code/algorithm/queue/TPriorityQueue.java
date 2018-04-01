package yin.shu.code.algorithm.queue;

import org.junit.Test;
import yin.shu.code.algorithm.utils.HeapIndex;
import yin.shu.code.algorithm.utils.PrintUtil;

import java.util.Arrays;

/**
 * 优先队列
 *
 * @author Aurora
 * @create 2018-03-27 9:42
 **/
public class TPriorityQueue implements TQueue {

    private int heapSize;

    private int[] tQueue;

    private int increlen = 10;

    private THeap heap = new THeap();

    public TPriorityQueue(){}

    public TPriorityQueue(int[] A){
        this.heapSize = A.length -1;
        this.tQueue = A;
        heap.build_max_heap(tQueue);
    }

    public int[] getTQueue(){
        return  tQueue;
    }

    public int getHeapSize(){
        return heapSize;
    }

    @Override
    public int maximum() {
        if(heapSize < 0){
            throw new RuntimeException("堆为空");
        }
        int max = tQueue[0];
        tQueue[0] = tQueue[heapSize];
        heapSize-=1;
        heap.max_heapify(tQueue,heapSize,0);
        return max;
    }

    @Override
    public void insert(int s) {
        heapSize++;
        if(heapSize >= tQueue.length){
            int b[] = new int[tQueue.length + 10];
            System.arraycopy(tQueue,0,b,0,tQueue.length);
            tQueue = b;
        }

        tQueue[heapSize] = s;
        increase_key(heapSize,s);
    }

    @Override
    public void increase_key(int i, int d) {

        if(heapSize < 0){
            throw new RuntimeException("队列为空");
        }

        if(heapSize <i){
            throw new RuntimeException("队列下标越界");
        }
        if( d < tQueue[i] ){
            throw new RuntimeException("增大的值不能小于当前值");
        }
        tQueue[i] = d;
        while(HeapIndex.parent(i) >=0 && tQueue[HeapIndex.parent(i)] <tQueue[i]){
            heap.swap(tQueue,HeapIndex.parent(i),i);
            i = HeapIndex.parent(i);
        }
    }
}
