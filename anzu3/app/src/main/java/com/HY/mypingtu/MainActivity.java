package com.HY.mypingtu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.HY.Sql.DBDao;
import com.HY.Sql.MyApplication;
import com.HY.Sql.PHB;
import com.HY.dao.StreamUtil;
import com.HY.fwq.Fwqdizhi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    private String pid;
    int tu_ID=1;
    private TextView username;
    private TextView usermoney;
    private TextView sucai;
    private TextView sangchen;
    private TextView sangchen_tb;
    private TextView sucai_tb;
    private RelativeLayout dibu;
    private RelativeLayout toubu2;
    private DrawerLayout drawerLayout;
    private TextView user_tx;
    private TextView phb_tb;
    private TextView sz_tb;
    private TextView PHB;
    private LinearLayout phblinearLayout;
    //?????????
    EditText ppmsr;
    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView paly=findViewById(R.id.paly);
        final ImageView img=(ImageView)findViewById(R.id.bjt);
        final ImageView up=(ImageView)findViewById(R.id.up);
        final ImageView donw=(ImageView)findViewById(R.id.don);
        PHB=findViewById(R.id.phb);
        TextView QKPHB=findViewById(R.id.qkphb);
        TextView Mosi=findViewById(R.id.mosi);

        phblinearLayout=findViewById(R.id.phbview);
        //????????????
        username=findViewById(R.id.username);
        usermoney=findViewById(R.id.usermoney);
        //??????????????????
        drawerLayout=findViewById(R.id.drawerLayout);
        //??????
        dibu=findViewById(R.id.dibu);
        dibu.getBackground().setAlpha(100);
        toubu2=findViewById(R.id.toubu2);
        toubu2.getBackground().setAlpha(180);
        //?????????
        TextView textView=findViewById(R.id.ppyx);
        ppmsr=findViewById(R.id.ppmsr);
        Button ppmqd=findViewById(R.id.ppmqd);
        Button ppmqx=findViewById(R.id.ppmqx);
        //????????????
        user_tx=findViewById(R.id.user_tx);
        phb_tb=findViewById(R.id.phb_tb);
        sz_tb=findViewById(R.id.sz_tb);
        sucai=findViewById(R.id.sucai);
        user_tx.setOnClickListener(this);
        phb_tb.setOnClickListener(this);
        sz_tb.setOnClickListener(this);
        sucai.setOnClickListener(this);
        sucai_tb=findViewById(R.id.sucai_tb);
        sangchen=findViewById(R.id.sangchen);
        sangchen_tb=findViewById(R.id.sangchen_tb);
        sucai_tb.setOnClickListener(this);
        sangchen.setOnClickListener(this);
        sangchen_tb.setOnClickListener(this);
        sp=getSharedPreferences("first",MODE_PRIVATE);
        ed=sp.edit();
        boolean pdms=true;
        if (sp.getBoolean("pd",true)){
            pdms=true;
            Mosi.setText("????????????");;
        }else {
            pdms=false;
            Mosi.setText("????????????");;
        }
        //??????????????????
        super.onRestart();
        username.setText(sp.getString("ptname","?????????"));
        usermoney.setText("?????????"+sp.getString("money","0000"));
        List<com.HY.Sql.PHB> listsize= DBDao.getInstance().query();
        if (listsize.size()!=10){
            DBDao.getInstance().clearAll();
            for(int i=1;i<=10;i++){
                PHB phb=new PHB(0,i,"0000","00","00");
                DBDao.getInstance().insert(phb);
            }
        }
        List<com.HY.Sql.PHB> listsize2= DBDao.getInstance().query2();
        if (listsize2.size()!=10){
            DBDao.getInstance().clearAll2();
            for(int i=1;i<=10;i++){
                PHB phb=new PHB(0,i,"0000","00","00");
                DBDao.getInstance().insert2(phb);
            }
        }
//????????????
        username.setText(sp.getString("ptname","?????????"));
        usermoney.setText(sp.getString("money","0000"));
        Log.e("swws","ss: "+listsize.size());
        Log.e("swws","ss2: "+listsize2.size());

       tu_ID=sp.getInt("tu_id",1);
        if (tu_ID==1){
            img.setImageDrawable(getResources().getDrawable(R.drawable.pkq2));
        }else if (tu_ID==2){
            img.setImageDrawable(getResources().getDrawable(R.drawable.ys));

        }else if (tu_ID==3){
            img.setImageDrawable(getResources().getDrawable(R.drawable.girl));

        }
//?????????
        String[] data=new String[11];
        //????????????
        data[0]="??????   ??????   ??????   ??????         ??????";
        List<PHB> list=null;
        if (pdms){
            list= DBDao.getInstance().query();
        }else {
            list= DBDao.getInstance().query2();
        }

        for (int i=0;i<list.size();i++){
            String mc=list.get(i).getMc()+"";
            if (mc.length()==1){
                mc="0"+mc;
            }
            String time=list.get(i).getTime()+"";
            if (time.length()==1){
                time="0"+time;
            }
            String tu="";
            switch (list.get(i).getMosi()){
                case "1":
                    tu="?????????";
                    break;
                case "2":
                    tu="??????   ";
                    break;
                case "3":
                    tu="??????   ";
                    break;
                case "4":
                    tu="?????????";
                    break;
                default:
                    tu="??????";
                    break;
            }
            data[i+1]=" "+mc+"      "+time+"      "+list.get(i).getBusu()+"      "+tu +"      "+list.get(i).getDay();
        }
         final ListView listView=findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);//???????????????ArrayAapeter
        listView.setAdapter(adapter);


        paly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=null;
                if (sp.getBoolean("pd",true)){
                   i=new Intent(MainActivity.this,Main2Activity.class);
                }else {
                    i=new Intent(MainActivity.this,Main16Activity.class);
                }
                ed.putInt("tu_id",tu_ID);
                ed.commit();
                i.putExtra("p_id",""+tu_ID);
                startActivity(i);
            }
        });



        //?????????
        donw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PHB.setText("?????????");
                phblinearLayout.setVisibility(View.GONE);
                tu_ID+=1;
                if (tu_ID>3){
                    Intent intent=new Intent(MainActivity.this,ZDYActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(0, 0);//????????????
                    tu_ID=3;
                }
                if (tu_ID==1){
                    img.setImageDrawable(getResources().getDrawable(R.drawable.pkq2));
                }else if (tu_ID==2){
                    img.setImageDrawable(getResources().getDrawable(R.drawable.ys));

                }else if (tu_ID==3){
                    img.setImageDrawable(getResources().getDrawable(R.drawable.girl));

                }


            }
        });
        //?????????
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PHB.setText("?????????");
                phblinearLayout.setVisibility(View.GONE);
                tu_ID-=1;
                if (tu_ID<1){
                    Toast.makeText(MainActivity.this,"?????????????????????????????????",Toast.LENGTH_LONG).show();
                    tu_ID=1;
                }
                if (tu_ID==1){
                    img.setImageDrawable(getResources().getDrawable(R.drawable.pkq2));
                }else if (tu_ID==2){
                    img.setImageDrawable(getResources().getDrawable(R.drawable.ys));

                }else if (tu_ID==3){
                    img.setImageDrawable(getResources().getDrawable(R.drawable.girl));

                }

            }
        });

        //???????????????
        img.setOnTouchListener(new View.OnTouchListener() {
            private float startX, startY, offsetX, offsetY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    //??????
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    //??????
                    case MotionEvent.ACTION_UP:
                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                        if (offsetX < -5) { // left
                            tu_ID+=1;
                            if (tu_ID>3){
                                Intent intent=new Intent(MainActivity.this,ZDYActivity.class);
                                startActivity(intent);
                                finish();
                                overridePendingTransition(0, 0);//????????????
                                tu_ID=3;
                            }
                            if (tu_ID==1){
                                img.setImageDrawable(getResources().getDrawable(R.drawable.pkq2));
                            }else if (tu_ID==2){
                                img.setImageDrawable(getResources().getDrawable(R.drawable.ys));

                            }else if (tu_ID==3){
                                img.setImageDrawable(getResources().getDrawable(R.drawable.girl));

                            }
                            //?????????
                            } else if (offsetX > 5) { // right
                            tu_ID-=1;
                            if (tu_ID<1){
                                Toast.makeText(MainActivity.this,"?????????????????????????????????",Toast.LENGTH_LONG).show();
                                tu_ID=1;
                            }
                            if (tu_ID==1){
                                img.setImageDrawable(getResources().getDrawable(R.drawable.pkq2));
                            }else if (tu_ID==2){
                                img.setImageDrawable(getResources().getDrawable(R.drawable.ys));

                            }else if (tu_ID==3){
                                img.setImageDrawable(getResources().getDrawable(R.drawable.girl));

                            }
                            }
                        } else {
                         if (offsetY < -5) { // up

                            } else if (offsetY > 5) { // down

                            }
                          }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        //?????????
        final Context context=this;

        PHB.setOnClickListener(new View.OnClickListener() {
            int pdphb=1;
            @Override
            public void onClick(View v) {
                pdphb++;
                if (pdphb%2!=0){
                    PHB.setText("?????????");
                    phblinearLayout.setVisibility(View.GONE);
                }else {
                    PHB.setText("??????");
                    phblinearLayout.setVisibility(View.VISIBLE);
                }
//                Toast.makeText(MainActivity.this,"ss",Toast.LENGTH_SHORT).show();
            }
        });
        //??????
        QKPHB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(MainActivity.this,Main3Activity.class);
               startActivity(intent);
            }
        });


        Thread t = new Thread() {
            @Override
            public void run() {

                Log.e("??????","sssssss1");
                try {
                    String   httpUrl = Fwqdizhi.dizhi+ "/dl";
                    Log.e("ss","ss"+httpUrl);
                    HttpClient client=new DefaultHttpClient();
                    HttpGet httpGet=new HttpGet(httpUrl);
                    HttpResponse httpResponse=client.execute(httpGet);

                    if (httpResponse.getStatusLine().getStatusCode()==200){
                        HttpEntity httpEntity=httpResponse.getEntity();
                        String content=EntityUtils.toString(httpEntity,"UTF-8");
                        Message msg=new Message();
                        msg.what=1;
                        msg.obj=content;
                        handler.sendMessage(msg);
                        Log.e("??????","dz");
                    }
                }catch (Exception e){
                    Log.e("??????","cw");
                }
            }
        };

        //???????????????

        final Thread t2 = new Thread() {
            @Override
            public void run() {

                try {
                    String   httpUrl = Fwqdizhi.dizhi+ "/playppm/"+ppmsr.getText()+"/"+readData("pid");
                    Log.e("ss","??????"+httpUrl);
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

                        Log.e("ppm????????????","dz");
                    }
                }catch (Exception e){
                    Message msg=new Message();
                    msg.what=404;
                    handler.sendMessage(msg);
                }
            }
        };


        if (readData("pid").equals("no")){
            t.start();
        }
        Log.e("pid",""+readData("pid"));

//????????????

        //????????????????????????
        final LinearLayout ppmLayout=findViewById(R.id.ppm);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readData("pid").equals("no")){
                    Toast.makeText(MainActivity.this, "???????????????????????????", Toast.LENGTH_SHORT).show();
                }else {
                    ppmLayout.setVisibility(View.VISIBLE);

                }
            }
        });
        //??????
        ppmqx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ppmLayout.setVisibility(View.GONE);
            }
        });
        //??????
        ppmqd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2.start();
                ppmLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // ??????????????????????????????????????????????????????????????????
            switch (msg.what) {
                case 1:
                    Log.e("??????",""+msg.obj);
                    saveData("pid",msg.obj.toString());
                    break;
                case 404:
                    Toast.makeText(MainActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    overridePendingTransition(0, 0);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    break;
                case 2:
                  Intent intent1=new Intent(MainActivity.this,JiaZaiActivity.class);
                  intent1.putExtra("ppm",ppmsr.getText().toString());
                  intent1.putExtra("pid",readData("pid"));
                  startActivity(intent1);
                    finish();
                    break;
                case 0:

                    break;
                default:
                    break;
            }

        }
    };




    public void saveData(String key,String value) {
        // ??????SharedPreferences??????
        // ????????????,??????putInt??????putString??????????????????????????????????????????~
        //?????????????????????????????????key-value????????????????????????????????????
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * ??????????????????????????????????????????????????????-1
     */
    public String readData(String key) {

        String v=sp.getString(key, "0");
        if ( !v.equals("0") ) {
            return v;
        } else {
            return "no";
        }
    }
    int pdphb=1;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.user_tx:
                if (sp.getString("ptname","?????????").equals("?????????")){
                    Intent intent=new Intent(MainActivity.this,LogActivity.class);
                    startActivity(intent);
                }else
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.phb_tb:
                pdphb++;
                if (pdphb%2!=0){
                    PHB.setText("?????????");
                    phblinearLayout.setVisibility(View.GONE);
                }else {
                    PHB.setText("??????");
                    phblinearLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.sz_tb:
                Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
                break;
            case R.id.sucai:
                qux();
                Intent intent1=new Intent(MainActivity.this,SUcaiActivity.class);
                startActivity(intent1);
                finish();
            break;
            case R.id.sucai_tb:
                qux();
                Intent intent2=new Intent(MainActivity.this,SUcaiActivity.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.sangchen:
                Toast.makeText(MainActivity.this,"?????????????????????????????????",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sangchen_tb:
                Toast.makeText(MainActivity.this,"?????????????????????????????????",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void qux() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{"android.permission.CAMERA",
                    "android.permission.READ_EXTERNAL_STORAGE",
                    "android.permission.WRITE_EXTERNAL_STORAGE"},101);
        }
    }

}
