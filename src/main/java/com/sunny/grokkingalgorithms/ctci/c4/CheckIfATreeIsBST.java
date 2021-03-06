package com.sunny.grokkingalgorithms.ctci.c4;

import com.sunny.grokkingalgorithms.ctci.c4.util.BinaryTreeNode;
import com.sunny.grokkingalgorithms.ctci.c4.util.TreeUtils;

/**
 * Created by sundas on 3/19/2018.
 */
public class CheckIfATreeIsBST {

  /*
  CHeck if a a binary tree is BST or not
   */


  /**
   *
   * @param root
   * @return
   */
  public static boolean isBST(BinaryTreeNode root){
    if(root == null){
      return true;
    }
    int maxOfLeftSubTree = max(root.left);
    int minOfRightSubTree = min(root.right);
    if(maxOfLeftSubTree <= root.data && minOfRightSubTree > root.data){
      return true;
    }
    else{
      return false;
    }
  }

  /**
   * Max of this tree
   *
   * @param root
   * @return
   */
  public static int max(BinaryTreeNode root){
    if(root == null){
      return Integer.MIN_VALUE;
    }
    int left = max(root.left);
    int right = max(root.right);
    int max = Math.max(left,right);
    max = Math.max(max,root.data);
    return max;
  }

  /**
   * Min of this true
   *
   * @param root
   * @return
   */
  public static int min(BinaryTreeNode root){
    if(root == null){
      return Integer.MAX_VALUE;
    }
    int left = min(root.left);
    int right = min(root.right);
    int min = Math.min(left, right);
    min = Math.min(min, root.data);
    return min;
  }


  /**
   * Alternate implementation
   *
   * @param root
   * @param min
   * @param max
   * @return
   */
  public static boolean isBST(BinaryTreeNode root, int min,int max){
    if(root == null){
      return true;
    }
    if(root.data < min || root.data > max){
      return false;
    }
    /*
    BST invariant
     */
    return isBST(root.left,min,(root.data - 1)) && isBST(root.right,(root.data+1), max);
  }

  /**
   *
   * @param args
   */
  public static void main(String[] args) {
    BinaryTreeNode root = TreeUtils.createBST();
    TreeUtils.inorder(root);
    TreeUtils.performBfs(root);
    System.out.println(isBST(root));
    System.out.println(isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE));
    root = TreeUtils.createBalancedBinaryTree();
    System.out.println(isBST(root));
    System.out.println(isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE));
    root = TreeUtils.createUnBalancedBinaryTree();
    System.out.println(isBST(root));
    System.out.println(isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE));
  }
}