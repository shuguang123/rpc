package yin.shu.code.binary.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历
 *
 * @author
 * @create 2018-01-28 21:21
 **/
public class inorderTraversal {

    private List<Integer> list = new ArrayList<>();


    /**
     * 采用递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal_reverse(TreeNode root) {

        if(root.left != null){
            inorderTraversal(root.left);
        }

        list.add(root.val);

        if(root.right != null){
            inorderTraversal(root.right);
        }

        return list;
    }


    /**
     * 非递归策略
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack  = new Stack<>();


        if(root != null){
            pushAllLeft(stack,root);
        }

        while (!stack.isEmpty()){
            TreeNode node = stack.pop();

            list.add(node.val);

            if(node.right !=null){

                //如果右子树不为空，全部入栈
                pushAllLeft(stack,node.right);
            }
        }

        return list;
    }

    /**
     * 将左子树的左节点全部入栈
     * @param stack
     * @param root
     */
    private void pushAllLeft(Stack<TreeNode> stack, TreeNode root) {
        stack.push(root);
        while(root.left != null){
            root = root.left;
            stack.push(root);
        }
    }


    @Test
    public void test(){
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(4);
        TreeNode right = new TreeNode(5);
        TreeNode right_left = new TreeNode(6);
        root.left = left;
        root.right = right;
        right.left = right_left;

        List<Integer> list = inorderTraversal(root);

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
    }


    /**
     * Definition for a binary tree node.
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val =val;
        }
    }
}
