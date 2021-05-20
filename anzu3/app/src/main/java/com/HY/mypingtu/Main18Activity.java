package com.HY.mypingtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.HY.Sql.PHB;
import com.HY.dao.Paixu;
import com.HY.dao.ShuJu;
import com.HY.fwq.Fwqdizhi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main18Activity extends AppCompatActivity {
private String pid;

 private    Timer timer;

    //判断对手是否拼好
    boolean pdduis=false;
    boolean pdzj=false;
    //白板
    int baiban;
    //步数
    int Busu=0;
    //时间
    int timept=0;
    //图9
    int tu9;
//定义数组
    int [] x=new int[9];
    int [] y=new int[9];
    int [] tp=new int[9];
    int[] ptture=new int[9];

//控件
    ImageView pt1;ImageView pt2;ImageView pt3;ImageView pt4;ImageView pt5;ImageView pt6;ImageView pt7;ImageView pt8;ImageView pt9;
    ImageView yhpt1;ImageView yhpt2;ImageView yhpt3;ImageView yhpt4;ImageView yhpt5;ImageView yhpt6;ImageView yhpt7;ImageView yhpt8;ImageView yhpt9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);
 //绑定控件
        pt1=findViewById(R.id.pt1);pt2=findViewById(R.id.pt2);pt3=findViewById(R.id.pt3);pt4=findViewById(R.id.pt4);
        pt5=findViewById(R.id.pt5);pt6=findViewById(R.id.pt6);pt7=findViewById(R.id.pt7);pt8=findViewById(R.id.pt8);pt9=findViewById(R.id.pt9);
        yhpt1=findViewById(R.id.yhpt1);yhpt2=findViewById(R.id.yhpt2);yhpt3=findViewById(R.id.yhpt3);yhpt4=findViewById(R.id.yhpt4);
        yhpt5=findViewById(R.id.yhpt5);yhpt6=findViewById(R.id.yhpt6);yhpt7=findViewById(R.id.yhpt7);yhpt8=findViewById(R.id.yhpt8);yhpt9=findViewById(R.id.yhpt9);
//数组赋值
        ShuJu shuJu=new ShuJu();
        ptture=shuJu.ptture();
        tu9=shuJu.tu9();
        //启动定时器
        mHandler.postDelayed(r, 1000);
//pid
        Intent intent=getIntent();

        pid=intent.getStringExtra("pid");



        pt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==1||baiban==3){
                    int abd=0;
                    abd=x[baiban];
                    x[baiban]=x[0];
                    x[0]=abd;
                    int abc=0;
                    abc=tp[baiban];
                    tp[baiban]=tp[0];
                    tp[0]=abc;
                    baiban=0;
                    Busu++;
                    //041372865
                    runX();
                    EndX();
                    Setbu();
                }
            }
        });
        //end
        //点击事件2
        pt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (baiban==0||baiban==2||baiban==4){
                    int abd=0;
                    Busu++;
                    abd=x[baiban];
                    x[baiban]=x[1];
                    x[1]=abd;
                    int abc=0;
                    abc=tp[baiban];
                    tp[baiban]=tp[1];
                    tp[1]=abc;
                    baiban=1;
                    runX();
                    EndX();
                    Setbu();
                }
            }
        });
        //end
        //点击事件3
        pt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==1||baiban==5){
                    int abd=0;
                    Busu++;
                    abd=x[baiban];
                    x[baiban]=x[2];
                    x[2]=abd;
                    int abc=0;
                    abc=tp[baiban];
                    tp[baiban]=tp[2];
                    tp[2]=abc;
                    baiban=2;
                    runX();
                    EndX();
                    Setbu();
                }
            }
        });
        //end
        //点击事件4
        pt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==0||baiban==4||baiban==6){
                    int abd=0;
                    Busu++;
                    abd=x[baiban];
                    x[baiban]=x[3];
                    x[3]=abd;
                    int abc=0;
                    abc=tp[baiban];
                    tp[baiban]=tp[3];
                    tp[3]=abc;
                    baiban=3;
                    runX();
                    EndX();
                    Setbu();
                }
            }
        });
        //end
        //点击事件5
        pt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==1||baiban==7||baiban==5||baiban==3){
                    int abd=0;
                    Busu++;
                    abd=x[baiban];
                    x[baiban]=x[4];
                    x[4]=abd;
                    int abc=0;
                    abc=tp[baiban];
                    tp[baiban]=tp[4];
                    tp[4]=abc;
                    baiban=4;
                    runX();
                    EndX();
                    Setbu();
                }
            }
        });
        //end
        //点击事件6
        pt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==2||baiban==4||baiban==8){
                    int abd=0;
                    Busu++;
                    abd=x[baiban];
                    x[baiban]=x[5];
                    x[5]=abd;
                    int abc=0;
                    abc=tp[baiban];
                    tp[baiban]=tp[5];
                    tp[5]=abc;
                    baiban=5;
                    runX();
                    EndX();
                    Setbu();
                }
            }
        });
        //end
        //点击事件7
        pt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==3||baiban==7){
                    int abd=0;
                    Busu++;
                    abd=x[baiban];
                    x[baiban]=x[6];
                    x[6]=abd;
                    int abc=0;
                    abc=tp[baiban];
                    tp[baiban]=tp[6];
                    tp[6]=abc;
                    baiban=6;
                    runX();
                    EndX();
                    Setbu();
                }
            }
        });
        //end
        //点击事件8
        pt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==6||baiban==8||baiban==4){
                    int abd=0;
                    Busu++;
                    abd=x[baiban];
                    x[baiban]=x[7];
                    x[7]=abd;
                    int abc=0;
                    abc=tp[baiban];
                    tp[baiban]=tp[7];
                    tp[7]=abc;
                    baiban=7;
                    runX();
                    EndX();
                    Setbu();
                }
            }
        });
        //end
        //点击事件9
        pt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baiban==7||baiban==5){
                    Busu++;
                    int abd=0;
                    abd=x[baiban];
                    x[baiban]=x[8];
                    x[8]=abd;
                    int abc=0;
                    abc=tp[baiban];
                    tp[baiban]=tp[8];
                    tp[8]=abc;
                    baiban=8;
                    runX();
                    EndX();
                    Setbu();
                }
            }
        });
        //end


//获取图片初始位置事件
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    String   httpUrl = Fwqdizhi.dizhi+"/playbu/"+pid;
                    Log.e("ss","ss"+httpUrl);
                    HttpClient client=new DefaultHttpClient();
                    HttpGet httpGet=new HttpGet(httpUrl);
                    HttpResponse httpResponse=client.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode()==200){
                        HttpEntity httpEntity=httpResponse.getEntity();
                        String content= EntityUtils.toString(httpEntity,"UTF-8");
                        Message msg=new Message();
                        msg.what=1;
                        msg.obj=content;
                        handler.sendMessage(msg);
                    }
                }catch (Exception e){
                    Message msg=new Message();
                    msg.what=404;
                    handler.sendMessage(msg);
                }
            }
        };
        t.start();

        //获取对手实时数据
        timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    String   httpUrl = Fwqdizhi.dizhi+ "/getbu/"+pid;
                    HttpClient client=new DefaultHttpClient();
                    HttpGet httpGet=new HttpGet(httpUrl);
                    HttpResponse httpResponse=client.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode()==200){
                        HttpEntity httpEntity=httpResponse.getEntity();
                        String content=EntityUtils.toString(httpEntity,"UTF-8");
                        Message msg=new Message();
                        msg.what=2;
                        msg.obj=content;
                        handler.sendMessage(msg);

                    }
                }catch (Exception e){
                    Message msg=new Message();
                    msg.what=404;
                    handler.sendMessage(msg);
                }
            }
        },0,100);//每隔一秒使用handler发送一下消息,也就是每隔一秒执行一次,一直重复执行
// (2) 使用handler处理接收到的消息
    }

    //404
    int ci=0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                String sju=msg.obj.toString();
                int [] c=new int[9];
                for (int i=0; i<9;i++){
                    c[i]=sju.charAt(i)-'0';
                }

                for (int i=0;i<tp.length;i++){
                    tp[i]=c[i];
                    Log.e("Shuju",tp[i]+"");
                    if (tp[i]==8){
                        baiban=i;
                    }
                }
                ShuJu shuJu=new ShuJu();
                x=shuJu.pt(c);

                runX();
            }else if (msg.what==2){
                String sju=msg.obj.toString();
                if (sju.equals("012345678")){
                    if (pdzj){
                        Intent intent=new Intent(Main18Activity.this,PlayOUTActivity.class);
                        intent.putExtra("zjbusu",Busu);
                        intent.putExtra("zjptime",timept);
                        intent.putExtra("pid",pid);
                        timer.cancel();
                        finish();
                        startActivity(intent);
                    }
                }
                int [] c=new int[9];
                for (int i=0; i<9;i++){
                    c[i]=sju.charAt(i)-'0';
                }
                ShuJu shuJu=new ShuJu();
                y=shuJu.pt(c);
                runY();
            }else if(msg.what == 404) {
                ci++;
                if (ci==20) {
                    Toast.makeText(Main18Activity.this, "请检查网络", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main18Activity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    };
    public void Setbu(){
        String shuju="";
        for (int i=0; i<9;i++){
            shuju+=tp[i];
        }
        Log.e("值",shuju);
        final String finalShuju = shuju;
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    String   httpUrl =Fwqdizhi.dizhi+ "/setbu/"+ finalShuju+"/"+pid+"/"+Busu+"/"+timept;
                    Log.e("ss","ss"+httpUrl);
                    HttpClient client=new DefaultHttpClient();
                    HttpGet httpGet=new HttpGet(httpUrl);
                    HttpResponse httpResponse=client.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode()==200){
                        HttpEntity httpEntity=httpResponse.getEntity();
                        String content= EntityUtils.toString(httpEntity,"UTF-8");
                        Message msg=new Message();
                        msg.what=3;
                        msg.obj=content;
                        handler.sendMessage(msg);
                    }
                }catch (Exception e){
                    Log.e("结果","cw");
                }
            }
        };
        t.start();
    }
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
        yhpt1.setImageDrawable(getResources().getDrawable(y[0]));
        yhpt2.setImageDrawable(getResources().getDrawable(y[1]));
        yhpt3.setImageDrawable(getResources().getDrawable(y[2]));
        yhpt4.setImageDrawable(getResources().getDrawable(y[3]));
        yhpt5.setImageDrawable(getResources().getDrawable(y[4]));
        yhpt6.setImageDrawable(getResources().getDrawable(y[5]));
        yhpt7.setImageDrawable(getResources().getDrawable(y[6]));
        yhpt8.setImageDrawable(getResources().getDrawable(y[7]));
        yhpt9.setImageDrawable(getResources().getDrawable(y[8]));
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

    public void EndX(){
        if (timept==60&&timept<=63){
            Toast.makeText(Main18Activity.this,"过了一分钟咯，要加油了",Toast.LENGTH_LONG).show();
        }
        int jj=0;
        for (int i=0;i<9;i++){
            if (x[i]==ptture[i]){
                jj++;
            }
        }
        if (jj==9){
            pdzj=true;
            pt9.setImageDrawable(getResources().getDrawable(tu9));
            Toast.makeText(Main18Activity.this,"恭喜您用时"+timept+"秒完成",Toast.LENGTH_LONG).show();
        }
    }
}
