package com.HY.dao;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.HY.mypingtu.R;
import com.HY.pojo.ImagePiece;
import com.HY.utils.ImageSplitter;

import java.util.List;

public class ShuJuZDYx16 {
    Bitmap bitmap=null;
    int width=0; int height=0;
    public ShuJuZDYx16(Bitmap bitmap, int width, int height){
    this.bitmap=bitmap;
    this.height=height;
    this.width=width;
    }
    public Bitmap[] pt(int[] x, Context context) {
        Bitmap[] bitmaps=new Bitmap[16];
        Bitmap[] ptture=ptture();
        for (int i=1;i<=16;i++){
            if (x[i-1]==0){
                bitmaps[i-1]=ptture[0];
            }else if (x[i-1]==1){
                bitmaps[i-1]=ptture[1];
            }else if (x[i-1]==2){
                bitmaps[i-1]=ptture[2];
            }else if (x[i-1]==3){
                bitmaps[i-1]=ptture[3];
            }else if (x[i-1]==4){
                bitmaps[i-1]=ptture[4];
            }else if (x[i-1]==5){
                bitmaps[i-1]=ptture[5];
            }else if (x[i-1]==6){
                bitmaps[i-1]=ptture[6];
            }else if (x[i-1]==7){
                bitmaps[i-1]=ptture[7];
            }else if (x[i-1]==8){
                bitmaps[i-1]=ptture[8];
            }else if (x[i-1]==9){
                bitmaps[i-1]=ptture[9];
            }else if (x[i-1]==10){
                bitmaps[i-1]=ptture[10];
            }else if (x[i-1]==11){
                bitmaps[i-1]=ptture[11];
            }else if (x[i-1]==12){
                bitmaps[i-1]=ptture[12];
            }else if (x[i-1]==13){
                bitmaps[i-1]=ptture[13];
            }else if (x[i-1]==14){
                bitmaps[i-1]=ptture[14];
            }else if (x[i-1]==15){
                bitmaps[i-1]=BitmapFactory.decodeResource(context.getResources(), R.drawable.hh);
            }
        }
        return bitmaps;
    }
    public Bitmap[] ptture(){
        Bitmap[] bitmaps=new Bitmap[16];
        List<ImagePiece> list=ImageSplitter.split(bitmap,4,4);
        for (int i=0;i<16;i++){
            bitmaps[i]=list.get(i).bitmap;
        }
        return bitmaps;
    }

    public Bitmap tu9() {
        Bitmap bitmap=ptture()[15];
        return bitmap;
    }





}
