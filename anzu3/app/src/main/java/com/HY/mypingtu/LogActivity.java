package com.HY.mypingtu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.HY.fwq.Fwqdizhi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.Timer;

public class LogActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView loging;
    private TextView registering;
    private EditText usersr;
    private EditText pwdsr;
    private SharedPreferences sp;
    private SharedPreferences.Editor ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        loging=findViewById(R.id.loging);
        registering=findViewById(R.id.registering);
        usersr=findViewById(R.id.usersr);
        pwdsr=findViewById(R.id.pwdsr);
        loging.setOnClickListener(this);
        registering.setOnClickListener(this);
        sp=getSharedPreferences("first",MODE_PRIVATE);
        ed=sp.edit();


    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.loging:

            yzdl.start();
            break;
        case R.id.registering:
            Intent intent2=new Intent(LogActivity.this,RegisterActivity.class);
            startActivity(intent2);
            break;
    }
    }

    Thread yzdl= new Thread() {
        @Override
        public void run() {
            Log.e("结果","sssssss1");
            try {
                String   httpUrl = Fwqdizhi.dizhi+ "/log/"+usersr.getText().toString()+"/"+pwdsr.getText().toString();
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
                    Log.e("结果","dz");
                }
            }catch (Exception e){
                Log.e("结果","cw");
            }
        }
    };
    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 处理消息时需要知道是成功的消息还是失败的消息
            switch (msg.what) {
                case 1:
                    Log.e("结果",""+msg.obj);
                    if (msg.obj.toString().equals("失败")){
                        Toast.makeText(LogActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LogActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        String jg=msg.obj.toString();
                        String ptname=jg.substring(jg.indexOf("ptname=")+7,jg.indexOf(", ptpwd"));
                        String sex=jg.substring(jg.indexOf("sex=")+4,jg.indexOf(", money"));
                        String money=jg.substring(jg.indexOf("money=")+6,jg.indexOf(", phone"));
                        ed.putString("ptname",ptname);
                        ed.putString("sex",sex);
                        ed.putString("money",money);
                        ed.commit();
                        Log.e("ptname",ptname);
                        Log.e("sex",sex);
                        finish();
                    }
                    break;
                default:
                    break;
            }

        }
    };
}
