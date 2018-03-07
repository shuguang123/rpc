package yin.shu.code.binary.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的前序遍历.
 *
 * @author
 * @create 2018-01-28 20:39
 **/
public class preorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        List<Integer> list = new ArrayList<Integer>();
        if(root != null){
            stack.push(root);
        }
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.val);
            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }

        return list;
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

        List<Integer> list = preorderTraversal(root);

        Iterator<Integer> iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
    }


    /**
     * Definition for a binary tree node.
     *
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


