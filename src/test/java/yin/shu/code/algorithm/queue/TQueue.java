package yin.shu.code.algorithm.queue;

/**
 * 优先队列结构
 *
 * @author Aurora
 * @create 2018-03-27 9:43
 **/
public interface TQueue {

    /**
     * 返回堆中最大的数
     * @return
     */
    int maximum();

    /**
     * 向队列中插入一个数
     * @param s
     */
    void insert(int s);

    /**
     * 将 s的值增加到d
     * @param i
     * @param d
     */
    void increase_key(int i,int d);
}
