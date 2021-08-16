package cn.sust.algorithm.sparseArray;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class SparseArray {
    public static void main(String[] args) throws FileNotFoundException {
        /**
         * 二维数组转换成稀疏数组
         */
        //创建一个原始的二维数组11*11
        //0 表示无棋子 1 表示黑子 2 表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始二维数组
        System.out.println("二维数组");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.print(data+"\t\t");
            }
            System.out.println();
        }
        //1.遍历二维数组 得到非0个数
        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0) {
                    sum++;
                }
            }
        }
        //2.创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;  //二维数组的行
        sparseArr[0][1] = 11;  //二维数组的列
        sparseArr[0][2] = sum; //二维数组非0元素个数

        //3.遍历二维数组，将原始数组中的非0元素赋值到稀疏数组中
        int count = 0;//记录是第几个非零数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println("由二维数组得到的稀疏数组");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.print(data+"\t\t");
            }
            System.out.println();
        }
        /**
         * 稀疏数组转换成二维数组
         */
        //1.读取稀疏数组的第一行，创建一个二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.从第二行开始遍历稀疏数组
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //3.输出二维数组
        System.out.println("由稀疏数组转换的二维数组");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.print(data+"\t\t");
            }
            System.out.println();
        }
    }
}
