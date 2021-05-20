package com.HY.mypingtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.HY.Sql.PHB;
import com.HY.dao.Paixu;
import com.HY.dao.ShuJuZDY;
import com.HY.dao.ShuJuZDYx16;
import com.HY.dao.ShuJu_2x16;
import com.HY.dao.ShuJu_3x16;
import com.HY.dao.ShuJux16;
import com.HY.utils.ImageSplitter;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main16Activity extends AppCompatActivity {
    //定义控件
    ImageView pt1;ImageView pt2;ImageView pt3;ImageView pt4;ImageView pt5;ImageView pt6;
    ImageView pt7;ImageView pt8;ImageView pt9;ImageView pt10;ImageView pt11;ImageView pt12;
    ImageView pt13;ImageView pt14;ImageView pt15;ImageView pt16;
    //定义时间
    int timept=0;
    //
    int n=16;
    int Busu=0;
    int x[]=new int[n];
    int tp[]=new int[n];
    int[] ptture=new int[n];
    Bitmap ptbitmap[]=new Bitmap[16];
    Bitmap ptturebitmap[]=new Bitmap[16];
    Bitmap tu9bitmap=null;
    String uri;
    int baiban=0;
    String tu_id="";
    int tu16;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main16);
        //绑定控件
        pt1=findViewById(R.id.pt1);pt2=findViewById(R.id.pt2);pt3=findViewById(R.id.pt3);pt4=findViewById(R.id.pt4);pt5=findViewById(R.id.pt5);
        pt6=findViewById(R.id.pt6);pt7=findViewById(R.id.pt7);pt8=findViewById(R.id.pt8);pt9=findViewById(R.id.pt9);pt10=findViewById(R.id.pt10);
        pt11=findViewById(R.id.pt11);pt12=findViewById(R.id.pt12);pt13=findViewById(R.id.pt13);pt14=findViewById(R.id.pt14);pt15=findViewById(R.id.pt15);
        pt16=findViewById(R.id.pt16);
        //启动定时器
        mHandler.postDelayed(r, 1000);
        //生成数
        for(int i = 0; i < n; i++)
        {
            x[i] = i;
        }
        x=sjsss(x);
        //数据存储

        for (int i=0;i<tp.length;i++){
            Log.e("ss","jg"+x[i]);
            tp[i]=x[i];
        }
        //判断白板在哪

        for (int i=0;i<tp.length;i++){
            if (tp[i]==15){
                baiban=i;
                Log.e("cnmaa","aa"+baiban);
            }
        }
        //获得T_id
        Intent intent=getIntent();
        tu_id=intent.getStringExtra("p_id");
        uri=intent.getStringExtra("url");
        //end
        if (tu_id.equals("1")){

            ShuJux16 shuJu=new ShuJux16();
            x=shuJu.pt(x);
            ptture=shuJu.ptture();
            for (int i=0;i<tp.length;i++){
                Log.e("ss","jg2:"+x[i]);

            }
            runX();
            tu16=shuJu.tu16();
        }else if (tu_id.equals("2")){
            ShuJu_2x16 shuJu=new ShuJu_2x16();
            x=shuJu.pt(x);
            ptture=shuJu.ptture();
            for (int i=0;i<tp.length;i++){
                Log.e("ss","jg2:"+x[i]);

            }
            runX();
            tu16=shuJu.tu16();
        }
        else if (tu_id.equals("3")){
            ShuJu_3x16 shuJu=new ShuJu_3x16();
            x=shuJu.pt(x);
            ptture=shuJu.ptture();
            for (int i=0;i<tp.length;i++){
                Log.e("ss","jg2:"+x[i]);

            }
            runX();
            tu16=shuJu.tu16();
        }else if (tu_id.equals("4")){
            Uri ur=Uri.parse(uri);
            Bitmap bitmap=null;
            try {
                if (intent.getStringExtra("psucai").equals("2")){
                    bitmap=BitmapFactory.decodeFile(uri);
                }else
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(ur));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ShuJuZDYx16 shuJu=new ShuJuZDYx16(bitmap,pt1.getWidth(),pt1.getHeight());
            Log.e("dax",pt1.getWidth()+""+pt1.getHeight());
            ptbitmap=shuJu.pt(x,Main16Activity.this);
            ptturebitmap=shuJu.ptture();
            runY();
            tu9bitmap=shuJu.tu9();
        }
            //点击事件

        //点击事件1

        pt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==1||baiban==4){
                    tudjsj(0);

                }
            }
        });
        //end
        //点击事件2

        pt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==0||baiban==2||baiban==5){
                    tudjsj(1);
                }
            }
        });
        //end

        //点击事件3
        pt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==1||baiban==3||baiban==6){
                    tudjsj(2);
                }
            }
        });
        //end

        //点击事件4
        pt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==2||baiban==7){
                    tudjsj(3);
                }
            }
        });
        //end
        //点击事件5
        pt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==0||baiban==5||baiban==8){
                    tudjsj(4);
                }
            }
        });
        //end
        //点击事件6
        pt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==4||baiban==6||baiban==1||baiban==9){
                    tudjsj(5);
                }
            }
        });
        //end
        //点击事件7
        pt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==5||baiban==7||baiban==2||baiban==10){
                    tudjsj(6);
                }
            }
        });
        //end
        //点击事件8
        pt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==6||baiban==3||baiban==11){
                    tudjsj(7);
                }
            }
        });
        //end
        //点击事件9
        pt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==9||baiban==4||baiban==12){
                    tudjsj(8);
                }
            }
        });
        //end
        //点击事件10
        pt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==8||baiban==10||baiban==5||baiban==13){
                    tudjsj(9);
                }
            }
        });
        //end
        //点击事件11
        pt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==9||baiban==11||baiban==6||baiban==14){
                    tudjsj(10);
                }
            }
        });
        //end
        //点击事件12
        pt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==10||baiban==7||baiban==15){
                    tudjsj(11);
                }
            }
        });
        //end
        //点击事件13
        pt13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==13||baiban==8){
                    tudjsj(12);
                }
            }
        });
        //end
        //点击事件14
        pt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==12||baiban==14||baiban==9){
                    tudjsj(13);
                }
            }
        });
        //end
        //点击事件15
        pt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==13||baiban==15||baiban==10){
                    tudjsj(14);
                }
            }
        });
        //end
        //点击事件16
        pt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==14||baiban==11){
                    tudjsj(15);

                }
            }
        });
        //end
        //长按直接完成
        pt3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (tu_id.equals("4")){
                    for (int i=0;i<16;i++){
                        ptbitmap[i]=ptturebitmap[i];
                        runY();
                    }
                }else {
                    for (int i=0;i<16;i++){
                        x[i]=ptture[i];
                        runX();
                    }
                }


                    EndX();
                return false;
            }
        });
    }

//填充图片
    public void runX(){
        pt1.setImageDrawable(getResources().getDrawable(x[0]));
        pt2.setImageDrawable(getResources().getDrawable(x[1]));
        pt3.setImageDrawable(getResources().getDrawable(x[2]));
        pt4.setImageDrawable(getResources().getDrawable(x[3]));
        pt5.setImageDrawable(getResources().getDrawable(x[4]));
        pt6.setImageDrawable(getResources().getDrawable(x[5]));
        pt7.setImageDrawable(getResources().getDrawable(x[6]));
        pt8.setImageDrawable(getResources().getDrawable(x[7]));
        pt9.setImageDrawable(getResources().getDrawable(x[8]));
        pt10.setImageDrawable(getResources().getDrawable(x[9]));
        pt11.setImageDrawable(getResources().getDrawable(x[10]));
        pt12.setImageDrawable(getResources().getDrawable(x[11]));
        pt13.setImageDrawable(getResources().getDrawable(x[12]));
        pt14.setImageDrawable(getResources().getDrawable(x[13]));
        pt15.setImageDrawable(getResources().getDrawable(x[14]));
        pt16.setImageDrawable(getResources().getDrawable(x[15]));
    }
    public void runY(){
        pt1.setImageBitmap(ptbitmap[0]);pt2.setImageBitmap(ptbitmap[1]);pt3.setImageBitmap(ptbitmap[2]);pt4.setImageBitmap(ptbitmap[3]);
        pt5.setImageBitmap(ptbitmap[4]);pt6.setImageBitmap(ptbitmap[5]);pt7.setImageBitmap(ptbitmap[6]);pt8.setImageBitmap(ptbitmap[7]);
        pt9.setImageBitmap(ptbitmap[8]);pt10.setImageBitmap(ptbitmap[9]);pt11.setImageBitmap(ptbitmap[10]);pt12.setImageBitmap(ptbitmap[11]);
        pt13.setImageBitmap(ptbitmap[12]);pt14.setImageBitmap(ptbitmap[13]);pt15.setImageBitmap(ptbitmap[14]);pt16.setImageBitmap(ptbitmap[15]);

    }
    Handler mHandler = new Handler();
    Runnable r = new Runnable() {

        @Override
        public void run() {
            //do something
            timept++;
            //每隔1s循环执行run方法
            mHandler.postDelayed(this, 1000);
        }
    };
    //打乱数组
    public int[] sjsss(int[] ai){

        int weizhi=15;
        int tihuan=0;
        for (int i = 0; i <200; i++) {
            int sj1=(int)(Math.random()*4+1);
            if (sj1==1) {
                int pd=weizhi;
                if (weizhi==2||weizhi==1||weizhi==0||weizhi==3) {
                    pd=weizhi;
                }else {
                    pd=weizhi-4;
                }

                tihuan=ai[weizhi];
                ai[weizhi]=ai[pd];
                ai[pd]=tihuan;
                weizhi=pd;
            }else if (sj1==2) {
                int pd=weizhi;
                if (weizhi==15||weizhi==14||weizhi==13||weizhi==12) {
                    pd=weizhi;
                }else {
                    pd=weizhi+4;
                }

                tihuan=ai[weizhi];
                ai[weizhi]=ai[pd];
                ai[pd]=tihuan;
                weizhi=pd;
            }else if (sj1==3) {
                int pd=weizhi;
                if (weizhi==0||weizhi==4||weizhi==8||weizhi==12) {
                    pd=weizhi;
                }else {
                    pd=weizhi-1;
                }

                tihuan=ai[weizhi];
                ai[weizhi]=ai[pd];
                ai[pd]=tihuan;
                weizhi=pd;
            }else if (sj1==4) {
                int pd=weizhi;
                if (weizhi==3||weizhi==7||weizhi==11||weizhi==15) {
                    pd=weizhi;
                }else {
                    pd=weizhi+1;
                }

                tihuan=ai[weizhi];
                ai[weizhi]=ai[pd];
                ai[pd]=tihuan;
                weizhi=pd;
            }

        }
        return ai;
    }
//判断是否成功拼好
    public void EndX(){
        if (timept==60&&timept<=63){
            Toast.makeText(Main16Activity.this,"过了一分钟咯，要加油了",Toast.LENGTH_LONG).show();
        }
        int jj=0;
        for (int i=0;i<16;i++){
            if (tu_id.equals("4")){
                if (ImageSplitter.compare2Image(ptbitmap[i],ptturebitmap[i])){
                    jj++;
                }
            }else {
                if (x[i]==ptture[i]){
                    jj++;
                }
            }
        }
        if (jj>=15){
            if (tu_id.equals("4")){
                pt9.setImageBitmap(tu9bitmap);
            }else {
                pt9.setImageDrawable(getResources().getDrawable(tu16));
            }
            Toast.makeText(Main16Activity.this,"恭喜您用时"+timept+"秒完成",Toast.LENGTH_LONG).show();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm");// HH:mm:ss
//获取当前时间
            Date date = new Date(System.currentTimeMillis());
            String day=simpleDateFormat.format(date);
            PHB phb=new PHB();
            phb.setDay(day);
            phb.setTime(timept);
            phb.setMc(1);
            String bu=Busu+"";
            if (bu.length()==1){
                bu="0"+bu;
            }
            phb.setBusu(bu);
            phb.setMosi(tu_id);
            Paixu.paixu2(phb);

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    finish();

                    //do something
                }
            }, 2000);    //延时1s执行
        }
    }
    private void  tudjsj(int i){
        if (tu_id.equals("4")){
            Bitmap abd =null;
            abd = ptbitmap[baiban];
            ptbitmap[baiban] = ptbitmap[i];
            ptbitmap[i]=abd;
            baiban = i;
            Busu++;
            runY();
            EndX();
        }else {
            int abd = 0;
            abd = x[baiban];
            x[baiban] = x[i];
            x[i] = abd;
            baiban = i;
            Busu++;
            runX();
            EndX();
        }
    }
}
