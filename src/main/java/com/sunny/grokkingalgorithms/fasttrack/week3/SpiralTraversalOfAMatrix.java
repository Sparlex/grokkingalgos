package com.sunny.grokkingalgorithms.fasttrack.week3;

/**
 * Created by sundas on 7/3/2018.
 */
public class SpiralTraversalOfAMatrix {


  /*
  It is actually spiral traversal
   */

  /**
   *
   * @param input
   */
  public static void spiralTraversalAlternate(int[][] input){
    int k = 0;
    int l = 0;
    int m = input.length - 1;
    int n = input[0].length - 1;
    while(k <= m && l <= n){
      for(int j = l ; j <= n ; j++){
        System.out.print(input[k][j] + " ");
      }
      k++;
      for(int i = k; i <= m ; i++){
        System.out.print(input[i][n] + " ");
      }
      n--;
      if(k <= m){
        for(int j = n ; j >= l ; j--){
          System.out.print(input[m][j] + " ");
        }
        m--;
      }
      if(l <= n){
        for(int i = m; i >= k; i--){
          System.out.print(input[i][l] + " ");
        }
        l++;
      }
    }
  }

  /**
   *
   * @param input
   */
  public static void spiralTraversal(int[][] input){
    int layers = input.length/2;
    if(input.length % 2 != 0){
      layers = layers + 1;
    }
    int count = 0;
    int i = 0;
    int j = 0;
    int rowBound = input.length;
    int colBound = input[0].length;
    //System.out.println(colBound);
    while(count < layers){
      for(;j<colBound;j++){
        System.out.print(input[i][j] + " ");
      }
      j--;
      i++;
      int prevI = i;
      for(;i<rowBound;i++){
        System.out.print(input[i][j] + " ");
      }
      i--;
      if(i <= prevI){
        //break;
      }
      j--;
      for(;j>=count;j--){
        System.out.print(input[i][j] + " ");
      }
      j++;
      i--;
      for(;i>=count;i--){
        if(i != count) {
          System.out.print(input[i][j] + " ");
        }
      }
      i+=2;
      j++;
      count++;
      rowBound--;
      colBound--;
      System.out.println();
    }
  }


  /**
   *
   * @param args
   */
  public static void main(String[] args) {
    int[][] input = new int[][]{
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,16}
    };
    spiralTraversal(input);
    System.out.println();
    System.out.println();
    input = new int[][]{
        {1,2,3,4,5,6,7},
        {8,9,10,11,12,13,14},
        {15,16,17,18,19,20,21},
        {22,23,24,25,26,27,28},
        {29,30,31,32,33,34,35},
        {36,37,38,39,40,41,42}
    };
    spiralTraversal(input);
    System.out.println();
    System.out.println();
    int a[][] = { {1,  2,  3,  4,  5,  6},
        {7,  8,  9,  10, 11, 12},
        {13, 14, 15, 16, 17, 18}
    };
    spiralTraversal(a);
    System.out.println();
    System.out.println();
    spiralTraversalAlternate(a);
  }


}
