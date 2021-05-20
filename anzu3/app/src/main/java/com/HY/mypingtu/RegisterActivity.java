package com.HY.mypingtu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.HY.fwq.Fwqdizhi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    //控件
    private EditText username;private EditText usersex;private EditText userphone;
    private Button phonebt;private EditText yzm;private EditText userpwd;
    private ImageView ok;EditText userpwd2;
    //数据
    private String dxyzm="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //绑定控件
        bangding();
    }
    //绑定控件
    private void bangding() {
        username=findViewById(R.id.username);userphone=findViewById(R.id.userphone);
        userpwd=findViewById(R.id.userpwd);usersex=findViewById(R.id.usersex);
        yzm=findViewById(R.id.yzm);phonebt=findViewById(R.id.phonebt);
        ok=findViewById(R.id.ok);userpwd2=findViewById(R.id.userpwd2);
        phonebt.setOnClickListener(this);
        ok.setOnClickListener(this);
    }
    //点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.phonebt:
                DXyzm.start();
                Toast.makeText(this,"已发送，请注意接收",Toast.LENGTH_SHORT).show();
                phonebt.setVisibility(View.GONE);
                break;
            case R.id.ok:
                boolean pdmm=userpwd.getText().toString().equals(userpwd2.getText().toString());
                boolean pdyzm=dxyzm.equals(yzm.getText().toString());
                if (!pdyzm){
                    Toast.makeText(this,"验证码错误",Toast.LENGTH_SHORT).show();
                }
                if (!pdmm)Toast.makeText(this,"两次输入密码不一致",Toast.LENGTH_SHORT).show();
                if (pdmm&&pdyzm){
                    registering.start();
                }
                break;
        }
    }
    Thread registering= new Thread() {
        @Override
        public void run() {
            Log.e("结果","sssssss1");
            try {
                String   httpUrl = Fwqdizhi.dizhi+ "/register/"+username.getText().toString()+"/"+usersex.getText().toString()+"/"+userpwd.getText().toString();
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
    Thread DXyzm= new Thread() {
        @Override
        public void run() {
            Log.e("结果","sssssss1");
            try {
                String   httpUrl = Fwqdizhi.dizhi+ "/DXYZ/"+userphone.getText().toString();
                Log.e("ss2","ss2"+httpUrl);
                HttpClient client=new DefaultHttpClient();
                HttpGet httpGet=new HttpGet(httpUrl);
                HttpResponse httpResponse=client.execute(httpGet);

                if (httpResponse.getStatusLine().getStatusCode()==200){
                    HttpEntity httpEntity=httpResponse.getEntity();
                    String content= EntityUtils.toString(httpEntity,"UTF-8");
                    Message msg=new Message();
                    msg.what=2;
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
                    if (msg.obj.toString().equals("成功")){
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(RegisterActivity.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    Log.e("结果",""+msg.obj);
                    dxyzm=msg.obj.toString();
                    break;
                default:
                    break;
            }

        }
    };
}
