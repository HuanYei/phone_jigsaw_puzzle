package com.HY.dao;

import com.HY.mypingtu.R;

public class ShuJu3 {
    public int[] pt( int[] x){
        for (int i=1;i<=9;i++){
            if (x[i-1]==0){
                x[i-1]=R.drawable.girl_x9_01;
            }else if (x[i-1]==1){
                x[i-1]=R.drawable.girl_x9_02;
            }else if (x[i-1]==2){
                x[i-1]=R.drawable.girl_x9_03;
            }else if (x[i-1]==3){
                x[i-1]=R.drawable.girl_x9_04;
            }else if (x[i-1]==4){
                x[i-1]=R.drawable.girl_x9_05;
            }else if (x[i-1]==5){
                x[i-1]=R.drawable.girl_x9_06;
            }else if (x[i-1]==6){
                x[i-1]=R.drawable.girl_x9_07;
            }else if (x[i-1]==7){
                x[i-1]=R.drawable.girl_x9_08;
            }else if (x[i-1]==8){
                x[i-1]=R.drawable.hh;
            }
        }

        return x;
    }
    public int[] ptture( ){
       int[] ptture=new int[9];
        ptture[0]=R.drawable.girl_x9_01;
        ptture[1]=R.drawable.girl_x9_02;
        ptture[2]=R.drawable.girl_x9_03;
        ptture[3]=R.drawable.girl_x9_04;
        ptture[4]=R.drawable.girl_x9_05;
        ptture[5]=R.drawable.girl_x9_06;
        ptture[6]=R.drawable.girl_x9_07;
        ptture[7]=R.drawable.girl_x9_08;
        ptture[8]=R.drawable.hh;
        return ptture;
    }
    public int tu9(){
        return R.drawable.girl_x9_09;
    }
}
