package yin.shu.code.binary.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的后序遍历
 *
 * @author
 * @create 2018-01-28 22:09
 **/
public class postorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();

        Stack<PowerNode> stack  = new Stack<>();

        if(root != null){
            stack.push(new PowerNode(root,false));
        }

        while (!stack.isEmpty()){
            PowerNode curr = stack.peek();
            if(curr.visited){
                list.add(curr.node.val);
                stack.pop();
            }else {
                //如果是第一次访问，就将它的左右节点加入stack，并设置其已经访问了一次
                if(curr.node.right!=null) stack.push(new PowerNode(curr.node.right,false));
                if(curr.node.left!=null)  stack.push(new PowerNode(curr.node.left,false));
                curr.visited =true;
            }
        }

        return  list;
    }

    /**
     * 判断树节点是否是第一次访问
     *
     */
    private class PowerNode {
        TreeNode node;
        boolean visited;
        public PowerNode(TreeNode n, boolean v){
            this.node = n;
            this.visited = v;
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

        List<Integer> list = postorderTraversal(root);

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
            this.val = val;
        }
    }
}
