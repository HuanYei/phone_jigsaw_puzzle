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
import com.HY.dao.ShuJu;
import com.HY.dao.ShuJu2;
import com.HY.dao.ShuJu3;
import com.HY.dao.ShuJuZDY;
import com.HY.utils.ImageSplitter;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main2Activity extends AppCompatActivity {
    int n=9;
    int timept=0;
    int baiban=0;
    int[] x = new int[n];
    int[] ptture=new int[9];
    String tu_id="";
    int[] tp=new int[9];
    Bitmap ptbitmap[]=new Bitmap[9];
    Bitmap ptturebitmap[]=new Bitmap[9];
    Bitmap tu9bitmap=null;
    String uri;
    ImageView pt1;
    ImageView pt2;
    ImageView pt3;
    ImageView pt4;
    ImageView pt5;
    ImageView pt6;
    ImageView pt7;
    ImageView pt8;
    ImageView pt9;
    int tu9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        pt1=findViewById(R.id.pt1);
        pt2=findViewById(R.id.pt2);
        pt3=findViewById(R.id.pt3);
        pt4=findViewById(R.id.pt4);
        pt5=findViewById(R.id.pt5);
        pt6=findViewById(R.id.pt6);
        pt7=findViewById(R.id.pt7);
        pt8=findViewById(R.id.pt8);
        pt9=findViewById(R.id.pt9);
        //启动定时器
        mHandler.postDelayed(r, 1000);
        //生成数
        for(int i = 0; i < n; i++)
        {
            x[i] = i;
        }
//        Random r = new Random();
//        for(int i = 0; i < n; i++)
//        {
//            int in = r.nextInt(n - i) + i;
//            int t = x[in];
//            x[in] = x[i];
//            x[i] = t;
//        }
        x=sjsss(x);
        //数据存储
        for (int i=0;i<tp.length;i++){
            tp[i]=x[i];
        }
        //判断白板在哪

        for (int i=0;i<tp.length;i++){
            if (tp[i]==8){
                baiban=i;
                Log.e("cnmaa","aa"+baiban);
            }
        }
        //end

        //end
        //获得T_id
        Intent intent=getIntent();
        tu_id=intent.getStringExtra("p_id");
        uri=intent.getStringExtra("url");
        //end
        if (tu_id.equals("1")){

            ShuJu shuJu=new ShuJu();
            x=shuJu.pt(x);
            ptture=shuJu.ptture();
            runX();
            tu9=shuJu.tu9();
        }else if (tu_id.equals("2")){
            ShuJu2 shuJu=new ShuJu2();
            x=shuJu.pt(x);
            ptture=shuJu.ptture();
            runX();
            tu9=shuJu.tu9();
        }else if (tu_id.equals("3")){
            ShuJu3 shuJu=new ShuJu3();
            x=shuJu.pt(x);
            ptture=shuJu.ptture();
            runX();
            tu9=shuJu.tu9();
        }else if (tu_id.equals("4")){

            Uri ur=Uri.parse(uri);
            Bitmap bitmap=null;
            try {
                if (intent.getStringExtra("psucai").equals("2")){
                    bitmap=BitmapFactory.decodeFile(uri);
                }else{
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(ur));}
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            ShuJuZDY shuJu=new ShuJuZDY(bitmap,pt1.getWidth(),pt1.getHeight());
            Log.e("dax",pt1.getWidth()+""+pt1.getHeight());
            ptbitmap=shuJu.pt(x,Main2Activity.this);
            ptturebitmap=shuJu.ptture();
            runY();
            tu9bitmap=shuJu.tu9();
        }
        Log.e("cnmaa","aa"+baiban);
        //点击事件1

        pt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==1||baiban==3){
                    if (tu_id.equals("4")){
                        Bitmap abd =null;
                        abd = ptbitmap[baiban];
                        ptbitmap[baiban] = ptbitmap[0];
                        ptbitmap[0]=abd;
                        baiban = 0;
                        Busu++;
                        runY();
                        EndX();
                    }else {
                        int abd = 0;
                        abd = x[baiban];
                        x[baiban] = x[0];
                        x[0] = abd;
                        baiban = 0;
                        Busu++;
                        runX();
                        EndX();
                    }
                }
            }
        });
        //end
        //点击事件2
        pt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==0||baiban==2||baiban==4){
                    if (tu_id.equals("4")){
                        Bitmap abd =null;
                        abd = ptbitmap[baiban];
                        ptbitmap[baiban] = ptbitmap[1];
                        ptbitmap[1]=abd;
                        baiban = 1;
                        Busu++;
                        runY();
                        EndX();
                    }else {
                        int abd = 0;
                        Busu++;
                        abd = x[baiban];
                        x[baiban] = x[1];
                        x[1] = abd;
                        baiban = 1;
                        runX();
                        EndX();
                    }
                }
            }
        });
        //end
        //点击事件3
        pt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==1||baiban==5){
                    if (tu_id.equals("4")){
                        Bitmap abd =null;
                        abd = ptbitmap[baiban];
                        ptbitmap[baiban] = ptbitmap[2];
                        ptbitmap[2]=abd;
                        baiban = 2;
                        Busu++;
                        runY();
                        EndX();
                    }else {
                        int abd = 0;
                        Busu++;
                        abd = x[baiban];
                        x[baiban] = x[2];
                        x[2] = abd;
                        baiban = 2;
                        runX();
                        EndX();
                    }
                }
            }
        });
        //end
        //点击事件4
        pt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==0||baiban==4||baiban==6){
                    if (tu_id.equals("4")){
                        Bitmap abd =null;
                        abd = ptbitmap[baiban];
                        ptbitmap[baiban] = ptbitmap[3];
                        ptbitmap[3]=abd;
                        baiban = 3;
                        Busu++;
                        runY();
                        EndX();
                    }else {
                        int abd = 0;
                        Busu++;
                        abd = x[baiban];
                        x[baiban] = x[3];
                        x[3] = abd;
                        baiban = 3;
                        runX();
                        EndX();
                    }
                }
            }
        });
        //end
        //点击事件5
        pt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==1||baiban==7||baiban==5||baiban==3){
                    if (tu_id.equals("4")){
                        Bitmap abd =null;
                        abd = ptbitmap[baiban];
                        ptbitmap[baiban] = ptbitmap[4];
                        ptbitmap[4]=abd;
                        baiban = 4;
                        Busu++;
                        runY();
                        EndX();
                    }else {
                        int abd = 0;
                        Busu++;
                        abd = x[baiban];
                        x[baiban] = x[4];
                        x[4] = abd;
                        baiban = 4;
                        runX();
                        EndX();
                    }
                }
            }
        });
        //end
        //点击事件6
        pt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==2||baiban==4||baiban==8){
                    if (tu_id.equals("4")){
                        Bitmap abd =null;
                        abd = ptbitmap[baiban];
                        ptbitmap[baiban] = ptbitmap[5];
                        ptbitmap[5]=abd;
                        baiban = 5;
                        Busu++;
                        runY();
                        EndX();
                    }else {
                        int abd = 0;
                        Busu++;
                        abd = x[baiban];
                        x[baiban] = x[5];
                        x[5] = abd;
                        baiban = 5;
                        runX();
                        EndX();
                    }
                }
            }
        });
        //end
        //点击事件7
        pt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==3||baiban==7){
                    if (tu_id.equals("4")){
                        Bitmap abd =null;
                        abd = ptbitmap[baiban];
                        ptbitmap[baiban] = ptbitmap[6];
                        ptbitmap[6]=abd;
                        baiban = 6;
                        Busu++;
                        runY();
                        EndX();
                    }else {
                        int abd = 0;
                        Busu++;
                        abd = x[baiban];
                        x[baiban] = x[6];
                        x[6] = abd;
                        baiban = 6;
                        runX();
                        EndX();
                    }
                }
            }
        });
        //end
        //点击事件8
        pt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==6||baiban==8||baiban==4){
                    if (tu_id.equals("4")){
                        Bitmap abd =null;
                        abd = ptbitmap[baiban];
                        ptbitmap[baiban] = ptbitmap[7];
                        ptbitmap[7]=abd;
                        baiban = 7;
                        Busu++;
                        runY();
                        EndX();
                    }else {
                        int abd = 0;
                        Busu++;
                        abd = x[baiban];
                        x[baiban] = x[7];
                        x[7] = abd;
                        baiban = 7;
                        runX();
                        EndX();
                    }
                }
            }
        });
        //end
        //点击事件9
        pt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==7||baiban==5){
                    if (tu_id.equals("4")){
                        Bitmap abd =null;
                        abd = ptbitmap[baiban];
                        ptbitmap[baiban] = ptbitmap[8];
                        ptbitmap[8]=abd;
                        baiban = 8;
                        Busu++;
                        runY();
                        EndX();
                    }else {
                        Busu++;
                        int abd = 0;
                        abd = x[baiban];
                        x[baiban] = x[8];
                        x[8] = abd;
                        baiban = 8;
                        runX();
                        EndX();
                    }
                }
            }
        });
        //end

    }
    private int Busu=0;
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
    }
    public void runY(){
        pt1.setImageBitmap(ptbitmap[0]);
        pt2.setImageBitmap(ptbitmap[1]);
        pt3.setImageBitmap(ptbitmap[2]);
        pt4.setImageBitmap(ptbitmap[3]);
        pt5.setImageBitmap(ptbitmap[4]);
        pt6.setImageBitmap(ptbitmap[5]);
        pt7.setImageBitmap(ptbitmap[6]);
        pt8.setImageBitmap(ptbitmap[7]);
        pt9.setImageBitmap(ptbitmap[8]);
    }
    public void EndX(){
        if (timept==60&&timept<=63){
            Toast.makeText(Main2Activity.this,"过了一分钟咯，要加油了",Toast.LENGTH_LONG).show();
        }
        int jj=0;
        for (int i=0;i<9;i++){
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
        if (jj>=8){
            if (tu_id.equals("4")){
                pt9.setImageBitmap(tu9bitmap);
            }else {
                pt9.setImageDrawable(getResources().getDrawable(tu9));
            }
            Toast.makeText(Main2Activity.this,"恭喜您用时"+timept+"秒完成",Toast.LENGTH_LONG).show();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");// HH:mm:ss
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
            Paixu.paixu(phb);
            new Handler().postDelayed(new Runnable() {

                 @Override
        public void run() {
                     finish();
                                 //do something
           }
        }, 2000);    //延时1s执行
        }
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
        public int[] sjsss(int[] ai){

            int weizhi=8;
            int tihuan=0;
            for (int i = 0; i <99; i++) {
                int sj1=(int)(Math.random()*4+1);
                if (sj1==1) {
                    int pd=weizhi;
                    if (weizhi==2||weizhi==1||weizhi==0) {
                        pd=weizhi;
                    }else {
                        pd=weizhi-3;
                    }

                    tihuan=ai[weizhi];
                    ai[weizhi]=ai[pd];
                    ai[pd]=tihuan;
                    weizhi=pd;
                }else if (sj1==2) {
                    int pd=weizhi;
                    if (weizhi==6||weizhi==7||weizhi==8) {
                        pd=weizhi;
                    }else {
                        pd=weizhi+3;
                    }

                    tihuan=ai[weizhi];
                    ai[weizhi]=ai[pd];
                    ai[pd]=tihuan;
                    weizhi=pd;
                }else if (sj1==3) {
                    int pd=weizhi;
                    if (weizhi==0||weizhi==3||weizhi==6) {
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
                    if (weizhi==2||weizhi==5||weizhi==8) {
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
}
