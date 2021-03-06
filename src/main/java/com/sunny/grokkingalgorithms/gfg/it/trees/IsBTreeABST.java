package com.sunny.grokkingalgorithms.gfg.it.trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by sundas on 2/26/2018.
 */
class Node {
  int data;
  Node left;
  Node right;
}
public class IsBTreeABST {

  /*
  Find if a tree is BST
   */



  private static Node previous;
  boolean checkBSTInorderOption(Node root) {
    return checkBSTInorder(root);
  }

  boolean checkBSTInorder(Node root){
    if(root == null){
      return true;
    }
    boolean leftBST = checkBSTInorder(root.left);
    if(leftBST){
      if(previous != null && previous.data >= root.data){
        return false;
      }
      previous = root;
      boolean rightBST = checkBSTInorder(root.right);
      if(leftBST && rightBST){
        return true;
      }
    }
    return false;
  }


  boolean checkBSTAlternate(Node root){
    return checkBSTAlternateUtil(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
  }


  boolean checkBSTAlternateUtil(Node root,int min,int max){
    if(root == null){
      return true;
    }

    if(root.data < min || root.data > max){
      return false;
    }

    return checkBSTAlternateUtil(root.left,min,root.data - 1) && checkBSTAlternateUtil(root.right,(root.data + 1),max);

  }

  boolean checkBST(Node root) {
    if(root == null){
      return true;
    }
    boolean left = checkBST(root.left);
    if(left){
      boolean right = checkBST(root.right);
      if(right){
        int maxLeft = findMax(root.left,Integer.MIN_VALUE);
        int minRight = findMin(root.right,Integer.MAX_VALUE);
        return root.data > maxLeft && root.data < minRight;
      }
    }
    return false;
  }

  int findMax(Node root,int maxValue){
    if(root == null){
      return maxValue;
    }
    if(root.data > maxValue){
      maxValue = root.data;
    }
    int leftMax = findMax(root.left,maxValue);
    int rightMax = findMax(root.right,maxValue);
    return Math.max(leftMax,rightMax);
  }

  /**
   *
   * @param root
   * @param minValue
   * @return
   */
  int findMin(Node root,int minValue){
    if(root == null){
      return minValue;
    }
    if(root.data < minValue){
      minValue = root.data;
    }
    int leftMin = findMin(root.left,minValue);
    int rightMin = findMin(root.right,minValue);
    return Math.min(leftMin,rightMin);
  }

}
