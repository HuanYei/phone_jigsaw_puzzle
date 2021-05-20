package com.HY.dao;

import com.HY.mypingtu.R;

public class ShuJu_2x16 {
    public int[] pt( int[] x){
        for (int i=1;i<=16;i++){
            if (x[i-1]==0){
                x[i-1]= R.drawable.ys_x16_01;
            }else if (x[i-1]==1){
                x[i-1]=R.drawable.ys_x16_02;
            }else if (x[i-1]==2){
                x[i-1]=R.drawable.ys_x16_03;
            }else if (x[i-1]==3){
                x[i-1]=R.drawable.ys_x16_04;
            }else if (x[i-1]==4){
                x[i-1]=R.drawable.ys_x16_05;
            }else if (x[i-1]==5){
                x[i-1]=R.drawable.ys_x16_06;
            }else if (x[i-1]==6){
                x[i-1]=R.drawable.ys_x16_07;
            }else if (x[i-1]==7){
                x[i-1]=R.drawable.ys_x16_08;
            }else if (x[i-1]==8){
                x[i-1]=R.drawable.ys_x16_09;
            }else if (x[i-1]==9){
                x[i-1]=R.drawable.ys_x16_10;
            }else if (x[i-1]==10){
                x[i-1]=R.drawable.ys_x16_11;
            }else if (x[i-1]==11){
                x[i-1]=R.drawable.ys_x16_12;
            }else if (x[i-1]==12){
                x[i-1]=R.drawable.ys_x16_13;
            }else if (x[i-1]==13){
                x[i-1]=R.drawable.ys_x16_14;
            }else if (x[i-1]==14){
                x[i-1]=R.drawable.ys_x16_15;
            }else if (x[i-1]==15){
                x[i-1]=R.drawable.h1x16;
            }
        }

        return x;
    }
    public int[] ptture( ){
        int[] ptture=new int[16];
        ptture[0]=R.drawable.ys_x16_01;
        ptture[1]=R.drawable.ys_x16_02;
        ptture[2]=R.drawable.ys_x16_03;
        ptture[3]=R.drawable.ys_x16_04;
        ptture[4]=R.drawable.ys_x16_05;
        ptture[5]=R.drawable.ys_x16_06;
        ptture[6]=R.drawable.ys_x16_07;
        ptture[7]=R.drawable.ys_x16_08;
        ptture[8]=R.drawable.ys_x16_09;
        ptture[9]=R.drawable.ys_x16_10;
        ptture[10]=R.drawable.ys_x16_11;
        ptture[11]=R.drawable.ys_x16_12;
        ptture[12]=R.drawable.ys_x16_13;
        ptture[13]=R.drawable.ys_x16_14;
        ptture[14]=R.drawable.ys_x16_15;
        ptture[15]=R.drawable.h1x16;
        return ptture;
    }
    public int tu16(){
        return R.drawable.ys_x16_16;
    }
}
