package yin.shu.code.algorithm.divide;

import org.junit.Test;

import java.util.Random;

/**
 * 矩阵乘法
 *
 * @author Aurora
 * @create 2018-03-20 21:42
 **/
public class Matrix {

    /**
     * 生成N*N的矩阵
     * @param N
     * @return
     */
    int[][] createMatrix(int N){
        int[][] A= new int[N][N];
        int i ,j;
        for(i=0; i<N ;i++ ){
            for(j =0 ;j<N;j++){
                A[i][j] = (int)(Math.random()*10);
            }
        }
        return A;
    }

    /**
     * 打印矩阵
     * @param A
     * @param N
     */
    void printMatrix(int[][] A ,int N){
        for(int i= 0;i<N;i++){
            for(int j = 0;j<N;j++){
                if(j == (N-1)){
                    System.out.print(A[i][j]);
                    System.out.println();
                }else {
                    System.out.print(A[i][j]+ "  ");
                }
            }
        }
    }

    /**
     * 两个矩阵相乘 A ,B 都是 N*N矩阵
     * 复杂度O(N^3)
     * @param A
     * @param B
     * @return
     */
    int[][] multiMatrix(int[][] A,int[][] B){

        int len_A = A.length;
        int len_B = B.length;
        int[][] C = new int[len_A][len_A];
        if(len_A != len_B){
            return null;
        }
        int i ,j,k;
        for(i= 0; i< len_A;i++){
            for(j=0; j< len_A;j++){
                C[i][j] = 0;
                for ( k = 0; k< len_A;k++){
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return  C;
    }

    /**
     * 采用分治策略
     * 说明: C = [ C11  C12 ]  A = [A11 A12]  B=[B11 B12]
     *            C22  C22         A21 A22      B21  B22
     *       C11 = A11 * B11 + A12 * B21
     *       C12 = A11 * B12 + A12 * B22
     *       C21 = A21 * B11 + A22 * B21
     *       C22 = A21 * B12 + A22 *B22
     * 算法效率O()
     * @param A
     * @param B
     * @return
     */
    int[][] multiMatrixRecurence(int[][] A ,int[][] B){
        int len = A.length;
        int[][] C;
        if(len == 2){
            C = multiMatrix(A,B);
            return C;
        }else {
            C = new int[len][len];
        }

        //获取A的子矩阵
        int[][] A11 = getMatrix(A,0,0);
        int[][] A12 = getMatrix(A,0,len/2);
        int[][] A21 = getMatrix(A,len/2,0);
        int[][] A22 = getMatrix(A,len/2,len/2);

        //获取B的子矩阵
        int[][] B11 = getMatrix(B,0,0);
        int[][] B12 = getMatrix(B,0,len/2);
        int[][] B21 = getMatrix(B,len/2,0);
        int[][] B22 = getMatrix(B,len/2,len/2);


        //子矩阵相加
        int[][] C11 = matrixAdd(multiMatrixRecurence(A11,B11),multiMatrixRecurence(A12,B21));
        int[][] C12 = matrixAdd(multiMatrixRecurence(A11,B12),multiMatrixRecurence(A12,B22));
        int[][] C21 = matrixAdd(multiMatrixRecurence(A21,B11),multiMatrixRecurence(A22,B21));
        int[][] C22 = matrixAdd(multiMatrixRecurence(A21,B12),multiMatrixRecurence(A22,B22));

        C= mergeMatrix(C11,C12,C21,C22);
        return C;
    }

    /**
     * 合并子矩阵
     * @param C11
     * @param C12
     * @param C21
     * @param C22
     * @return
     */
    private int[][] mergeMatrix(int[][] C11, int[][] C12,
                                int[][] C21, int[][] C22) {
        if(C11.length != C12.length){
            return null;
        }
        if(C12.length != C21.length){
            return null;
        }
        if(C21.length != C22.length){
            return null;
        }
        int len = C11.length * 2;

        int[][] C = new int[len][len];

        for (int i = 0;i<len/2 ;i++){
            for (int j = 0;j<len/2;j++){
                C[i][j] = C11[i][j];
            }
        }

        for (int i = 0;i<len/2 ;i++){
            for (int j = 0;j<len/2;j++){
                C[i][j+len/2] = C12[i][j];
            }
        }

        for (int i = 0;i<len/2 ;i++){
            for (int j = 0;j<len/2;j++){
                C[i+len/2][j] = C21[i][j];
            }
        }

        for (int i = 0;i<len/2 ;i++){
            for (int j = 0;j<len/2;j++){
                C[i+len/2][j+len/2] = C22[i][j];
            }
        }
        return C;
    }

    /**
     * 获得子矩阵
     * @param A
     * @return
     */
    private int[][] getMatrix(int[][] A,int start_A,int start_B ) {
        int len = A.length /2;
        int[][] matrix = new int[len][len];
        for(int i = 0;i < len; i++){
            for(int j = 0; j < len; j++){
                matrix[i][j] = A[i+start_A][j+start_B];
            }
        }
        return matrix;
    }

    /**
     * 两个矩阵相加
     * @param A
     * @param B
     * @return
     */
    int[][] matrixAdd(int[][] A,int[][] B){
        if(A.length !=B.length){
            return null;
        }else {
            int len = A.length;
            int[][] C = new int[len][len];
            for(int i= 0;i<len;i++){
                for (int j = 0;j<len ;j++){
                    C[i][j] = A[i][j] + B[i][j];
                }
            }
            return C;
        }
    }


    @Test
    public void testMatrix(){
        int[][] A = createMatrix(4);
        int[][] B = createMatrix(4);
        printMatrix(A,A.length);
        System.out.println("===========");
        printMatrix(B,B.length);
        System.out.println("===========");
        int[][] C = multiMatrixRecurence(A,B);
        printMatrix(C,C.length);
    }


    @Test
    public void testRandom(){
/*        for(int i = 0;i<10;i++){
            System.out.println((int)(Math.random()*10)+1);
        }*/
        int[][] A11 = createMatrix(2);
        int[][] A12 = createMatrix(2);
        int[][] A21 = createMatrix(2);
        int[][] A22 = createMatrix(2);
        int[][] C = mergeMatrix(A11,A12,A21,A22);
        printMatrix(A11,A11.length);
        printMatrix(A12,A12.length);
        printMatrix(A21,A21.length);
        printMatrix(A22,A22.length);
        printMatrix(C,C.length);
    }
}
